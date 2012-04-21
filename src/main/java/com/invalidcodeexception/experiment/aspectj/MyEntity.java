package com.invalidcodeexception.experiment.aspectj;

/**
 * User: thibaultdelor
 * Date: 21/03/12
 */
public class MyEntity /* extends BaseClass -> cause a compile Exception since Base class define toString */
{

    private String name;

    public MyEntity(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(new MyEntity("hello"));
    }


}
