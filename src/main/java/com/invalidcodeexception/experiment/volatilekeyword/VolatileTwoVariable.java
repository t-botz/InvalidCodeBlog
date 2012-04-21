package com.invalidcodeexception.experiment.volatilekeyword;

import com.invalidcodeexception.logging.MyLoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: thibaultdelor
 * Date: 19/03/12
 */
public class VolatileTwoVariable {
    private static final Logger LOGGER = MyLoggerFactory.getSimplestLogger();

    private int i = 0;
    private volatile int j = 0;

    public static void main(String[] args) {
        VolatileTwoVariable volatileTest = new VolatileTwoVariable();
        volatileTest.new ChangeListener().start();
        volatileTest.new ChangeMaker().start();
    }

    class ChangeListener extends Thread {
        @Override
        public void run() {
            int locali = i;
            int localj = j;
            while (true){
                if(locali!= i){
                    LOGGER.log(Level.INFO,"Got Change for i : {0}",i);
                    locali=i;
                }
                if(localj!= j){
                    LOGGER.log(Level.INFO, "Got Change for j : {0}", j);
                    localj=j;
                }
            }
        }
    }

    class ChangeMaker extends Thread{

        @Override
        public void run() {

            int local = i;
            while (true){
                LOGGER.log(Level.INFO, "Incrementing i and j to {0}", local+1);
                i = ++local;
                j = local;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
