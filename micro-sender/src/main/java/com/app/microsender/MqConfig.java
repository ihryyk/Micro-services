
package com.app.microsender;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("micro-services", false);
    }

}
