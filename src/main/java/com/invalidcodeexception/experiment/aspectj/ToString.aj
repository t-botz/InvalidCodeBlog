package com.invalidcodeexception.experiment.aspectj;

/**
 * User: thibaultdelor
 * Date: 21/03/12
 */
public aspect ToString {
    private interface ReflectiveToString {}
    declare parents : com.invalidcodeexception.experiment.aspectj.MyEntity implements ReflectiveToString;

    public String ReflectiveToString.toString(){
        return "Aspect ToString";
    }
}
