package cn.dbj.knowledge.rocket.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author dbj
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "test_topic", consumerGroup = "string_consumer", selectorExpression = "tag_a")
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("Consumer received {}", message);
    }
}