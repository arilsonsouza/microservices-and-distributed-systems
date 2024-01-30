package com.aos.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {
        "com.aos.notification",
        "com.aos.amqp",
})
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    // @Bean
    // CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
    // NotificationConfig notificationConfig) {
    // return arqgs -> {
    // producer.publish(new Person("Ali", 18),
    // notificationConfig.getInternalExchange(),
    // notificationConfig.getInternalNotificationRoutingKey());
    // };
    // }

    // record Person(String name, int age) {
    // }
}
