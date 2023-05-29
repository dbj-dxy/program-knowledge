package cn.dbj.knowledge.rocket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.*;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Frameless {

    @SneakyThrows
    public void pushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group_name");
        consumer.setNamesrvAddr("nameServer");
        // CLUSTERING || BROADCASTING
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 仅支持或操作
        // tag过滤
        consumer.subscribe("test_topic", "tag_a || tag_b");
        // sql92过滤
        // Tag属于一种特殊的消息属性，在SQL语法中，Tag的属性值为TAGS
        // 开启属性过滤首先要在Broker端设置配置enablePropertyFilter=true，该值默认为false
        // Message message = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));
        // message.putUserProperty("customProperties_a", "1");
        consumer.subscribe("test_topic", MessageSelector.bySql("(TAGS is not null and TAGS in ('tag_a', 'tag_b')) and (customProperties_a is not null and customProperties_a between 0 and 3)"));

        int msgsSize = 10;
        consumer.setConsumeMessageBatchMaxSize(msgsSize);

        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                log.info("msgsSize {}", msgs.size());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                log.info("msgsSize {}", msgs.size());
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        // 消息重试
        // 顺序消费和并发消费的重试机制并不相同
        // 顺序消费: 消费失败后会先在客户端本地重试直到最大重试次数，这样可以避免消费失败的消息被跳过，消费下一条消息而打乱顺序消费的顺序
        // 并发消费: 消费失败后会将消费失败的消息重新投递回服务端，再等待服务端重新投递回来，在这期间会正常消费队列后面的消息
        // 并发消费失败后并不是投递回原Topic，而是投递到一个特殊Topic，其命名为%RETRY%ConsumerGroupName，
        // 集群模式下并发消费每一个ConsumerGroup会对应一个特殊Topic，并会订阅该Topic

        // 最大重试次数：消息消费失败后，可被重复投递的最大次数
        // 顺序消费: 最大重试次数可通过自定义参数MaxReconsumeTimes取值进行配置。该参数取值无最大限制。若未设置参数值，默认最大重试次数为Integer.MAX
        // 并发消费: 最大重试次数可通过自定义参数MaxReconsumeTimes取值进行配置。默认值为16次，该参数取值无最大限制，建议使用默认值
        consumer.setMaxReconsumeTimes(10);
        // 重试间隔：消息消费失败后再次被投递给Consumer消费的间隔时间，只在顺序消费中起作用
        // 顺序消费: 间隔时间可通过自定义设置，SuspendCurrentQueueTimeMillis
        // 并发消费: 间隔时间根据重试次数阶梯变化，取值范围：1秒～2小时。不支持自定义配置
        consumer.setSuspendCurrentQueueTimeMillis(5000);

        // 死信队列
        // 当一条消息初次消费失败，RocketMQ会自动进行消息重试，达到最大重试次数后，若消费依然失败
        // 该消息不会立刻被丢弃，而是将其发送到该消费者对应的特殊队列中，这类消息称为死信消息（Dead-Letter Message）
        // 存储死信消息的特殊队列称为死信队列（Dead-Letter Queue），死信队列是死信Topic下分区数唯一的单独队列
        // 如果产生了死信消息，那对应的ConsumerGroup的死信Topic名称为%DLQ%ConsumerGroupName，死信队列的消息将不会再被消费。
        // 可以利用RocketMQ Admin工具或者RocketMQ Dashboard上查询到对应死信消息的信息。


        consumer.start();
    }

    @SneakyThrows
    public void pullConsumer() {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumer_group_name");
        consumer.setNamesrvAddr("nameServer");
        consumer.start();
        MessageQueue mq = new MessageQueue();
        mq.setQueueId(0);
        mq.setTopic("test_topic");
        mq.setBrokerName("broker_a");
        Set<MessageQueue> queueSet = consumer.fetchSubscribeMessageQueues("test_topic");
        long offset = 26;
        PullResult pullResult = consumer.pull(mq, "tag_a", offset, 32);
        if (pullResult.getPullStatus().equals(PullStatus.FOUND)) {
            log.info("msgs {}", pullResult.getMsgFoundList());
            // 更新消费位点
            consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
        }
        consumer.shutdown();
    }

    @SneakyThrows
    public void subscribeLitePullConsumer() {
        // LitePullConsumer拉取消息调用的是轮询poll接口，如果能拉取到消息则返回对应的消息列表，否则返回null
        // 通过setPullBatchSize可以设置每一次拉取的最大消息数量
        // 此外如果不额外设置，LitePullConsumer默认是自动提交位点
        // subscribe模式下，同一个消费组下的多个LitePullConsumer会负载均衡消费，与PushConsumer一致
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("consumer_group_name");
        consumer.setAutoCommit(false);
        consumer.subscribe("test_topic", "tag_a");
        consumer.setPullBatchSize(20);
        consumer.start();
        while (System.currentTimeMillis() % 2 == 0) {
            List<MessageExt> msgs = consumer.poll();
            log.info("msgs {}", msgs);
        }
        consumer.shutdown();
    }

    @SneakyThrows
    public void assignLitePullConsumer() {
        // Assign模式下没有自动的负载均衡机制，需要用户自行指定需要拉取的队列
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("consumer_group_name");
        consumer.setAutoCommit(false);
        consumer.start();
        // fetchMessageQueues获取了Topic下的队列，再取前面的一半队列进行拉取
        Collection<MessageQueue> mqSet = consumer.fetchMessageQueues("test_topic");
        List<MessageQueue> list = new ArrayList<>(mqSet);
        List<MessageQueue> assignList = new ArrayList<>();
        for (int i = 0; i < list.size() / 2; i++) {
            assignList.add(list.get(i));
        }
        consumer.assign(assignList);
        // 调用了seek方法，将第一个队列拉取的位点设置从10开始
        consumer.seek(assignList.get(0), 10);
        while (System.currentTimeMillis() % 2 == 0) {
            List<MessageExt> msgs = consumer.poll();
            log.info("msgs {}", msgs);
            // 调用commitSync方法手动提交位点
            consumer.commitSync();
        }
        consumer.shutdown();
    }

    @SneakyThrows
    public void simpleProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.start();

        Message message = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult sendResult = producer.send(message);
        log.info("SendResult {}", sendResult);
        producer.shutdown();
    }

    @SneakyThrows
    public void asyncProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.start();

        Message message = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));

        Thread main = Thread.currentThread();
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send success, sendResult {}", sendResult);
                LockSupport.unpark(main);
            }

            @Override
            public void onException(Throwable e) {
                log.warn("send error, msg {}", e.getMessage(), e);
                LockSupport.unpark(main);
            }
        });

        LockSupport.park();
        producer.shutdown();
    }

    @SneakyThrows
    public void onewayProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.start();

        Message message = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));

        producer.sendOneway(message);
        producer.shutdown();
    }

    @SneakyThrows
    public void orderlyProducer() {
        // 顺序消息的一致性
        // 如果要保证严格顺序而不是可用性
        // 1、创建 Topic 是要指定 -o 参数（--order）为true，表示顺序消息
        // 2、其次要保证NameServer中的配置 orderMessageEnable 和 returnOrderTopicConfigToBroker 必须是 true
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.start();

        Message message = new Message("test_topic", "tag_a", "key_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
            Integer id = (Integer) arg;
            int index = id % mqs.size();
            return mqs.get(index);
        }, 10);
        log.info("SendResult {}", sendResult);
        producer.shutdown();
    }

    @SneakyThrows
    public void delayProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.start();

        Message message = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));
        message.setDelayTimeLevel(1);

        SendResult sendResult = producer.send(message);
        log.info("SendResult {}", sendResult);
        producer.shutdown();
    }

    @SneakyThrows
    public void batchProducer() {
        // 批量消息的大小不能超过 1MiB（否则需要自行分割），其次同一批 batch 中 topic 必须相同。
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.start();

        Message message1 = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message message2 = new Message("test_topic", "tag_b", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message message3 = new Message("test_topic", "tag_c", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));


        SendResult sendResult = producer.send(Arrays.asList(message1, message2, message3));
        log.info("SendResult {}", sendResult);
        producer.shutdown();
    }

    @SneakyThrows
    public void transactionProducer() {
        // 事务消息的生产组名称 ProducerGroupName不能随意设置。
        // 事务消息有回查机制，回查时Broker端如果发现原始生产者已经崩溃，则会联系同一生产者组的其他生产者实例回查本地事务执行情况以Commit或Rollback半事务消息
        TransactionMQProducer producer = new TransactionMQProducer("producer_group_name");
        producer.setNamesrvAddr("nameServer");
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        producer.start();

        Message message = new Message("test_topic", "tag_a", "test message".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult sendResult = producer.sendMessageInTransaction(message, null);
        log.info("SendResult {}", sendResult);
        producer.shutdown();
    }

}
