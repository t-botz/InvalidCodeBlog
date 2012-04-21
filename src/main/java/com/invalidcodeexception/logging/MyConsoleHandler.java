package com.invalidcodeexception.logging;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * User: thibaultdelor
 * Date: 11/03/12
 */
public class MyConsoleHandler extends Handler {

    private static final Formatter DEFAULT_FORMATTER = new UltraSimpleFormatter();

    public MyConsoleHandler() {
        setFormatter(DEFAULT_FORMATTER);
    }

    @Override
    public void publish(LogRecord record) {
        System.out.println(getFormatter().format(record));
        flush();
    }

    @Override
    public void flush() {
        System.out.flush();
    }

    @Override
    public void close(){
    }
}
