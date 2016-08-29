package com.realdolmen.spring_course.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    public Quest BlaQuest() {
      //  return new RescueDamselQuest();

    return  new SlayDragonQuest();
    }
    @Bean
    public Knight fooKnight() {
        return new BraveKnight(BlaQuest());
    }
}
