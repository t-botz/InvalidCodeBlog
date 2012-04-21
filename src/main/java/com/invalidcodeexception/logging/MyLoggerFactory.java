package com.invalidcodeexception.logging;

import java.util.logging.Logger;

/**
 * User: thibaultdelor
 * Date: 11/03/12
 */
public class MyLoggerFactory {

    public static Logger getSimplestLogger(String name){
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        logger.addHandler(new MyConsoleHandler());
        return logger;
    }

    public static Logger getSimplestLogger() {
        return getSimplestLogger("noname");
    }
}
