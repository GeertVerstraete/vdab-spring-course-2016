package com.realdolmen.spring_course.domain;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

/**
 * Created by aryastark on 30/08/16.
 */
@Component
@Aspect
public class Minstrel {

    @Pointcut("execution(* *.embark())")
    public void fef() {

    }

    @AfterReturning(value = "execution(* *.embark(java.lang.String)) && args(who)", returning = "spoils")
    public void interceptQuest(String spoils, String who) {
        System.out.println(spoils);
        System.out.println("quest result: ");
        System.out.println(who);
    }

    @Pointcut("execution(* *.embarkOnQuest())")
    private void questing() {

    }

    @Before("questing()")
    public void interceptEmbarkonQuest() {
        System.out.println("minstrel sings: there once was a hero, called ragnar the red !");
    }

    @AfterReturning("questing()")
    public void succesQuest() {
        System.out.println("minstrel sings: ater his quest he shit his bed !");

    }

    @AfterThrowing(value = "execution(* *.embark(java.lang.String))", throwing = "error")
    public void failQuest(RuntimeException error) {
        System.out.println("minstrel sings: he went for a kill but the dragon ate his head !" + error.getMessage());

    }
}
