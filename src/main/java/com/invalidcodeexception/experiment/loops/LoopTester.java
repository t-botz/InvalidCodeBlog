/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invalidcodeexception.experiment.loops;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Compare the different ways to loop over the collection
 *
 * @author Tibo
 */
public class LoopTester extends SimpleBenchmark {

    public enum LoopingStrategy {

        WHILEITER {
            @Override
            int compute(List<Integer> list) {
                int sum = 0;

                final Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    sum += it.next();
                }
                return sum;
            }
        },
        FORITER {
            @Override
            int compute(List<Integer> list) {
                int sum = 0;
                for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
                    sum += it.next();
                }
                return sum;
            }
        },
        FOREACH {
            @Override
            int compute(List<Integer> list) {
                int sum = 0;
                for (Integer i : list) {
                    sum += i;
                }
                return sum;
            }
        },
        WHILE {
            @Override
            int compute(List<Integer> list) {
                int sum = 0;
                int i = 0;
                while (i < list.size()) {
                    sum += list.get(i);
                    i++;
                }
                return sum;
            }
        },
        FOR {
            @Override
            int compute(List<Integer> list) {
                int sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    sum += list.get(i);
                    
                }
                return sum;
            }
        };

        abstract int compute(List<Integer> list);
    }
    @Param({"100","1000","10000"}) private int nbIntegers;
    @Param private LoopingStrategy loopingStrategy; 
    private List<Integer> integers;

    @Override
    protected void setUp() throws Exception {
        integers = new LinkedList<>();
        for (int i = 0; i < nbIntegers; i++) {
            integers.add(i);
        }
    }
    

    public void timeLoop(int reps){
        int result = 0;
        for (int i = 0; i < reps; i++) {
            result = loopingStrategy.compute(integers);
        }
        System.out.println("result = " + result);
        
    }
    
    

    public static void main(String[] args) {
        Runner.main(LoopTester.class, args);
    }
}
