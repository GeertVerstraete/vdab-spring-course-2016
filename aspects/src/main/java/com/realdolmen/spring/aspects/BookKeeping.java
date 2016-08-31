package com.realdolmen.spring.aspects;

import com.realdolmen.spring.domain.Zoo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// TODO: Turn this class into a Spring Bean and an Aspect

@Aspect
@Component
public class BookKeeping {
    // TODO: Add an advice that advises the Zoo.accept(Visitor) method
    // TODO: Make sure the advice uses the return value of the the adviced method to keep track of happy and unhappy visitors
    int counterHappy = 0;
    int counterGrumpy = 0;

    public int getHappyVisitorCount() {
        return counterHappy;
    }


    public int getUnhappyVisitorCount() {
        return counterGrumpy;
    }



    @AfterReturning(value = "execution( boolean *..Zoo.accept(com.realdolmen.spring.domain.Visitor))", returning = "hapiness")
    public void interceptAccept(boolean hapiness) {


        if (hapiness) {
            counterHappy+=1;

        }
        else {
            counterGrumpy+=1;
        }

    }

}
