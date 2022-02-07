package cn.dbj.knowledge.rabbit.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = "dbj.queue")
    public void checkin(@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        @Header(AmqpHeaders.CORRELATION_ID) String tranceId,
                        @Payload String msg, Channel channel) throws IOException {
        try {
            log.info("tranceId: {}, msg: {}", tranceId, msg);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("checkin 消费异常, cause = ", e);
            channel.basicReject(deliveryTag, true);
        } finally {
            log.info("mq finish");
        }
    }

}
