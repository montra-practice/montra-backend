package com.epam;


import org.mockito.Mockito;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;

@Configuration
public class MockConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        return Mockito.mock(RedissonClient.class);
    }

    @Bean
    public JavaMailSender javaMailSender() throws IOException {
        return Mockito.mock(JavaMailSender.class);
    }
}
