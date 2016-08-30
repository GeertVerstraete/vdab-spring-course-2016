package com.realdolmen.spring_course.domain;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SlayDragonQuest implements Quest {
    @Override
    public void embark() {
        System.out.println("Going to slay dragon");
    }
}
