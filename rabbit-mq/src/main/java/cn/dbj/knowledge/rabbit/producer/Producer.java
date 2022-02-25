package cn.dbj.knowledge.rabbit.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    public void topicMsg() {
        // todo 此处的 CorrelationData 对应 发布回调的 CorrelationData ，其中的 id 不是 MessageProperties（AmqpHeaders） 中的 correlationId
        rabbitTemplate.convertAndSend("dbj.topic", "dbj.bind.key.t1", "hello", new CorrelationData("00201"));
    }
}
