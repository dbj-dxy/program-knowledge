package cn.dbj.knowledge.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDeclare {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("dbj.topic");
    }

    @Bean
    public Queue queue() {
        return new Queue("dbj.queue");
    }

    @Bean
    public Binding asyncBinding(@Qualifier("queue") Queue queue,
                                @Qualifier("exchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dbj.bind.key.*");
    }
}
