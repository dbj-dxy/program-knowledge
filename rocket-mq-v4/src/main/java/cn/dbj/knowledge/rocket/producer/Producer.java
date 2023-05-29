package cn.dbj.knowledge.rocket.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * @author dbj
 */
@Slf4j
@Component
@AllArgsConstructor
public class Producer {

    private final RocketMQTemplate rocketMQTemplate;

    public void send() {
        Message<String> msg = new GenericMessage<>("test message");

        rocketMQTemplate.syncSend("test_topic:tag_a", msg);
    }
}
