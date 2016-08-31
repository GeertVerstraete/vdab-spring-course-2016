package com.realdolmen.spring;

import com.realdolmen.spring.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan
// TODO: Enable the Spring AOP Engine
public class ApplicationConfiguration {
    @Bean
    public Zoo zoo() {
        PairiDaiza zoo = new PairiDaiza();
        zoo.setName("Pairi Daiza");
        zoo.addAnimal(new Tiger("Shere Khan"));
        zoo.addAnimal(new Elephant("Dumbo"));
        zoo.addAnimal(new Hippo("Bertha"));
        zoo.addAnimal(new Panda("Hao Hao"));
        zoo.addAnimal(new Panda("Xing Hui"));
        zoo.addAnimal(new Chimp("Ceasar"));
        return zoo;
    }
}
