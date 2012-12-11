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
            int compute(List<Integer> col) {
                int sum = 0;

                final Iterator<Integer> it = col.iterator();
                while (it.hasNext()) {
                    sum += it.next();
                }
                return sum;
            }
        },
        FORITER {
            @Override
            int compute(List<Integer> col) {
                int sum = 0;
                for (Iterator<Integer> it = col.iterator(); it.hasNext();) {
                    sum += it.next();
                }
                return sum;
            }
        },
        FOREACH {
            @Override
            int compute(List<Integer> col) {
                int sum = 0;
                for (Integer i : col) {
                    sum += i;
                }
                return sum;
            }
        },
        WHILE {
            @Override
            int compute(List<Integer> col) {
                int i = 0;
                int sum = 0;
                while (i < col.size()) {
                    sum += col.get(i);
                    i++;
                }
                return sum;
            }
        },
        FOR {
            @Override
            int compute(List<Integer> col) {
                int sum = 0;
                for (int i = 0; i < col.size(); i++) {
                    sum += col.get(i);
                    
                }
                return sum;
            }
        };

        abstract int compute(List<Integer> col);
    }
    private static final int NB_INTEGERS = 4000;
    private static final List<Integer> integers = new ArrayList<>(NB_INTEGERS);
    @Param private LoopingStrategy loopingStrategy; 

    static {
        for (int i = 0; i < NB_INTEGERS; i++) {
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
//        final LoopTester loopTester = new LoopTester();
//        loopTester.loopingStrategy = LoopingStrategy.WHILE;
//        loopTester.timeLoop(100);
        Runner.main(LoopTester.class, args);
    }
}
