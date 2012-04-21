package com.invalidcodeexception.experiment.aspectj;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * User: thibaultdelor
 * Date: 21/03/12
 */
public aspect ToString {
    private interface ReflectiveToString {}
    declare parents : com.invalidcodeexception.experiment.aspectj.MyEntity implements ReflectiveToString;

    public String ReflectiveToString.toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
