package com.airmiles.collectoreventsproducer.test.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class HandlerConfiguration
{
    @Value("${app.config.localstackEndpoint}")
    private String localstackEndpoint;

    @Value("${spring.profiles.active}")
    private String activeProfiles;


    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public String activeProfile()
    {
        System.out.println ("activeProfiles "+activeProfiles);
        return  activeProfiles;
    }

}
