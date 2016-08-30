package com.realdolmen.spring.config;

import com.realdolmen.spring.domain.*;
import com.realdolmen.spring.repository.FoodRepository;
import com.realdolmen.spring.repository.FoodRepositoryImpl;
import com.realdolmen.spring.service.PairiDaiza;
import com.realdolmen.spring.service.Zoo;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by cda5732 on 14/04/2015.
 */
@Configuration
@Profile("test")
@PropertySource("classpath:/ZooImplTest.properties")

// TODO load test properties
// TODO configure a profile
@ComponentScan("com.realdolmen.spring")
public class TestConfig {
    @Bean
    public Zoo zoo() {
        Zoo zoo = new PairiDaiza();
        zoo.addAnimal(new Tiger("Bengal Tiger"));
        zoo.addAnimal(new Elephant("Indian Elephant"));
        zoo.addAnimal(new Bear("Brown Bear"));
        return zoo;
    }

   // TODO configure the properties loader

}
