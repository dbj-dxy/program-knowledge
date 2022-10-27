package cn.dbj.knowledge.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDeclare {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("biz.topic");
    }

    @Bean
    public Queue queue() {
        return new Queue("biz.queue");
    }

    @Bean
    public Binding asyncBinding(@Qualifier("queue") Queue queue,
                                @Qualifier("exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("biz.bind.key.*");
    }
}
