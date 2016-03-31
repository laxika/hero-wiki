package com.morethanheroic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan
@EnableAutoConfiguration
public class HeroicWikiApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(HeroicWikiApplication.class, args);
    }
}
