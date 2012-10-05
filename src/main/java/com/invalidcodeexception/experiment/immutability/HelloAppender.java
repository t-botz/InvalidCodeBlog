package com.invalidcodeexception.experiment.immutability;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * @date Sep 25, 2012
 * @author thibaultdelor
 */
public class HelloAppender {

    private final String greeting;

    public HelloAppender(String name) {
        this.greeting = "hello " + name + "!\n";
    }

    public void appendTo(Appendable app) throws IOException {
        app.append(greeting);
    }
}
