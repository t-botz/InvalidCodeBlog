package com.invalidcodeexception.logging;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * User: thibaultdelor
 * Date: 11/03/12
 */
public class UltraSimpleFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {
        return formatMessage(record);
    }
}
