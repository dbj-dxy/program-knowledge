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

    /**
     * 交换机
     **/
    protected static final String COMMON_EXCHANGE = "xpsb";
    /**
     * 异步 队列
     **/
    protected static final String ASYNC_QUEUE = "qpsb_async";
    /**
     * 异步 key
     **/
    protected static final String ASYNC_KEY = "key.psb.async";


    @Bean
    public TopicExchange commonExchange() {
        return new TopicExchange(COMMON_EXCHANGE);
    }

    @Bean
    public Queue asyncQueue() {
        return new Queue(ASYNC_QUEUE);
    }

    @Bean
    public Binding asyncBinding(@Qualifier("asyncQueue") Queue queue,
                                @Qualifier("commonExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ASYNC_KEY);
    }
}
