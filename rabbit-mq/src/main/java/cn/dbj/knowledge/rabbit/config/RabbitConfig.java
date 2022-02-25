package cn.dbj.knowledge.rabbit.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Setter
@EnableRabbit
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        // 发送到交换机确认
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        // 交换机发送到 queue 确认
        factory.setPublisherReturns(true);
        // 5s 心跳
        factory.setRequestedHeartBeat(5);
        // 建立连接超时时间 1s
        factory.setConnectionTimeout(1000);
        // 缓存模式 -> 连接池模式 缓存channel
        factory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        // 设置每个Connection中缓存Channel的数量
        // 当Channel数量大于缓存数量时 多出来没法放进缓存的会被关闭
        factory.setChannelCacheSize(10);
        // 单位：毫秒；配合channelCacheSize不仅是缓存数量，而且是最大的数量。
        //     从缓存获取不到可用的Channel时，不会创建新的Channel，会等待这个值设置的毫秒数
        // 同时，在CONNECTION模式，这个值也会影响获取Connection的等待时间，
        //     超时获取不到Connection也会抛出AmqpTimeoutException异常。
        factory.setChannelCheckoutTimeout(1000);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // 生产者和消费者使用不同的Connection
        template.setUsePublisherConnection(true);
        // 将mandatory属性设为true（ReturnCallback需要，ConfirmCallback不需要）
        template.setMandatory(true);
        // 设置消息转换器
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        // 设置附加消息
        template.setCorrelationDataPostProcessor((message, correlationData) -> {
            // todo 取消该处理器，看confirm 是否有 CorrelationData 或者消费者是否能获取到 AmqpHeaders.CORRELATION_ID，
            //  若消费者获取不到说明 MessageProperties 即为 amqp 协议的 header（已经证实）
            message.getMessageProperties().setCorrelationId(correlationData.getId());
            return correlationData;
        });
        // 每一条发出的消息都会调用ConfirmCallback
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (null != correlationData) {
                MDC.put("trance_id", correlationData.getId());
            }
            if (ack) {
                log.info("消息发送至exchange成功");
            } else {
                log.error("消息发送至exchange失败, cause = {}", cause);
            }
            MDC.remove("trance_id");
        });
        // 只有在消息进入exchange但没有进入queue时才会调用
        template.setReturnsCallback(returned -> {
            log.error("exchange = {}, routingKey = {}", returned.getExchange(), returned.getRoutingKey());
            log.error("code = {}, msg = {}", returned.getReplyCode(), returned.getReplyText());
            log.error("data = {}", returned.getMessage());
        });
        // todo RetryTemplate -> spring-retry-template
        //template.setRetryTemplate(null);
        // todo retryTemplate.execute 的 RecoveryCallback 恢复回调（重试成功）
        // RecoveryCallback 应该产生与 execute(ChannelCallback)返回类型兼容的结果
        //template.setRecoveryCallback(null);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 设置并发数
        factory.setConcurrentConsumers(1);
        // 设置最大并发数
        factory.setMaxConcurrentConsumers(10);
        // 手动ack
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息转换器
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 设置每次请求发送给每个Consumer的消息数量
        factory.setPrefetchCount(1);
        // 设置监听异常回调处理器
        factory.setErrorHandler(t -> log.error("mq reject error cause = ", t));
        return factory;
    }
}
