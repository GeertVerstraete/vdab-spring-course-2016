package com.realdolmen.spring.domain;

import org.aspectj.lang.annotation.After;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PairiDaiza implements Zoo {
    private List<Animal> animals = new ArrayList<>();
    private String name;

    @Override
    public void addAnimal(Animal animal) {
        System.out.format("Adding a new %s with name '%s' to the zoo.%n", animal.type(), animal.getName());
        animals.add(animal);
    }

    @Override
    public boolean accept(Visitor visitor) {
        System.out.format("Welcome '%s' we hope you have fun at the zoo!%n", visitor.getName());
        return !visitor.isGrumpy();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Animal getAnimalByName(String name) {
        return animals.stream().filter(a -> a.getName().equals(name)).findFirst().get();
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void inventorize() {
        System.out.println("*************************************");
        System.out.format("Inventory of animals in '%s'%n", getName());
        System.out.println("-------------------------------------");

        animals.stream()
            .collect(Collectors.groupingBy(Animal::type, TreeMap::new, Collectors.toList()))
                .forEach((animalType, animals) -> {
                    System.out.println(animalType + "s: " + animals.size());
                    animals.forEach(animal -> System.out.println("\t- " + animal.getName()));
                });
        System.out.println("-------------------------------------");
        System.out.println("Total: " + countAnimals());
        System.out.println("*************************************");
    }

    @Override
    public void releaseAnimal(Animal animal) {
        animals.remove(animal);
        System.out.format("Oh my! %s '%s' has escaped from the zoo!%n", animal.type(), animal.getName());
    }

    @Override
    public int countAnimals() {
        return animals.size();
    }

}
