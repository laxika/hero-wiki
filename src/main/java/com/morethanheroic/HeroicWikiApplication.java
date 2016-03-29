package com.morethanheroic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class HeroicWikiApplication extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        SpringApplication.run(HeroicWikiApplication.class, args);
    }
}
