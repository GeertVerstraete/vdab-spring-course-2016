package com.realdolmen.spring_course.domain;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Primary
public class SlayDragonQuest implements Quest {
    @Override
    public String embark(String s) {
        System.out.println("*quest accepted* Going to slay a dragon");
        if (new Random().nextBoolean()) {
            throw new RuntimeException("game over ! you got eaten :(");
        } else {

            System.out.println("*quest complete* dragon dieded");
        }
        return "dragon head";
    }
}
