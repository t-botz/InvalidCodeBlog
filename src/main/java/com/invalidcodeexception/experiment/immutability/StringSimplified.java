
package com.invalidcodeexception.experiment.immutability;

import java.util.Arrays;

/**
 * @date Sep 16, 2012
 * @author thibaultdelor
 */
public class StringSimplified {
    private final char value[];

    /** Cache the hash code for the string */
    private int hash; // Default to 0

    public StringSimplified(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }
    
//    public int hashCode() {
//        int h = hash;
//        if (h == 0 && value.length > 0) {
//            char val[] = value;
//
//            for (int i = 0; i < value.length; i++) {
//                h = 31 * h + val[i];
//            }
//            hash = h;
//        }
//        return h;
//    }
    
    public int hashCode() {
        if (hash == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                hash = 31 * hash + val[i];
            }
        }
        return hash;
    }
}
