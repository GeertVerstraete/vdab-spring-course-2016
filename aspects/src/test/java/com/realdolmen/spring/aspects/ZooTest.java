package com.realdolmen.spring.aspects;

import com.realdolmen.spring.ApplicationConfiguration;
import com.realdolmen.spring.domain.*;
import com.realdolmen.spring.domain.Animal;
import com.realdolmen.spring.domain.Elephant;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
public class ZooTest {
    @Autowired ApplicationContext context;

    @Test
    public void zooLaunchesMarketingCampaignWhenNewAnimalArrives() throws Exception {
        Zoo zoo = context.getBean(Zoo.class);
        assertFalse("Zoo should be wrapped with an AOP proxy. Have you enabled AOP?", zoo.getClass().equals(PairiDaiza.class));
        Animal animal = new Elephant("Kai-Mook");
        zoo.addAnimal(animal);
        assertTrue("Campaign does not seem to be launched. Is your advice correctly configured?", context.getBean(Marketing.class).isCampaignLaunched());
    }

    @Test
    @Ignore
    public void zooAddsRestaurantFeaturesNextToCoreBusiness() throws Exception {
        Object zoo = context.getBean("zoo");
        assertTrue("Zoo must be an instance of Zoo", zoo instanceof Zoo);
        assertTrue("Zoo must be an instance of Restaurant", zoo instanceof Restaurant);
        new Visitor("Jimi Hendrix").orderMeal((Restaurant) zoo);
    }

    @Test
    public void zooKeepsCountOfAllVisitors() throws Exception {
        Zoo zoo = context.getBean(Zoo.class);
        Stream.of(
            new Visitor("Janis Joplin"),
            new Visitor("Kurt Cobain"),
            new Visitor("Jim Morrison", true),
            new Visitor("Jimi Hendrix")
        ).forEach(visitor -> visitor.visit(zoo));
        BookKeeping bookKeeping = context.getBean(BookKeeping.class);
        assertEquals("Number of happy visitors must be registered correctly in the books", 3, bookKeeping.getHappyVisitorCount());
        assertEquals("Number of unhappy visitors must be registered correctly in the books", 1, bookKeeping.getUnhappyVisitorCount());
    }

    @Test(expected = EscapedAnimalException.class)
    public void zooIsAlertedWhenAnimalsEscape() throws Exception {
        Zoo zoo = context.getBean(Zoo.class);
        Animal ceasar = zoo.getAnimalByName("Ceasar");
        ceasar.escapeFrom(zoo);
        int count = zoo.countAnimals();
        assertEquals(count - 1, zoo.countAnimals());
    }

    @Test
    public void zooDoesNotAllowTigersToEscape() throws Exception {
        Zoo zoo = context.getBean(Zoo.class);
        Animal shereKhan = zoo.getAnimalByName("Shere Khan");
        int count = zoo.countAnimals();
        shereKhan.escapeFrom(zoo);
        assertEquals(count, zoo.countAnimals());
    }
}
