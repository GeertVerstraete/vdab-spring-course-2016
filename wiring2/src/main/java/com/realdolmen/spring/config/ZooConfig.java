package com.realdolmen.spring.config;

import com.realdolmen.spring.domain.*;
import com.realdolmen.spring.repository.FoodRepository;
import com.realdolmen.spring.repository.FoodRepositoryImpl;
import com.realdolmen.spring.service.PairiDaiza;
import com.realdolmen.spring.service.Zoo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * Created by cda5732 on 25/03/2015.
 */
@Configuration
@Profile("production")
@PropertySource("classpath:/com/realdolmen/spring/ZooImpl.properties")
// TODO Load properties for the zoo
// TODO Add a profile
public class ZooConfig {




    @Bean
    public Zoo zoo() {
        Zoo zoo = new PairiDaiza();
        zoo.addAnimal(new Tiger("Bengal Tiger"));
        zoo.addAnimal(new Elephant("Indian Elephant"));
        zoo.addAnimal(new Bear("Brown Bear"));
        return zoo;
    }

    @Bean

    // TODO this is the Non-Kibble repository
    public FoodRepository foodRepository() {
        FoodRepository foodRepository = new FoodRepositoryImpl();
        foodRepository.addFoodForAnimalType(Tiger.class, new MeatyFood("Red Antilope Meat"));
        foodRepository.addFoodForAnimalType(Bear.class, new MeatyFood("Pink Salmon"));
        foodRepository.addFoodForAnimalType(Elephant.class, new VegiFood("Cabbage"));
        return foodRepository;
    }

    // TODO Configure the properties loader
}
