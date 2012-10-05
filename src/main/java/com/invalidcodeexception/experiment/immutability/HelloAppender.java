package com.invalidcodeexception.experiment.immutability;

import java.io.IOException;

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
