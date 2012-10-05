
package com.invalidcodeexception.experiment.immutability;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date Sep 17, 2012
 * @author thibaultdelor
 */
public class VerySimpleDateFormat {
    
    private final DateFormat formatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
    
    public String format(Date d){
        
        return formatter.format(d);
    }
}
