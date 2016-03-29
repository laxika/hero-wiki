package com.morethanheroic.entry.service.config;

import org.pegdown.PegDownProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarkdownProcessorConfig {

    @Bean
    public PegDownProcessor pegDownProcessor() {
        return new PegDownProcessor();
    }
}
