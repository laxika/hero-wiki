package com.morethanheroic.entry.service.config;

import org.pegdown.PegDownProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MarkdownProcessorConfig {

    @Bean
    @Scope("prototype")
    public PegDownProcessor pegDownProcessor() {
        return new PegDownProcessor();
    }
}
