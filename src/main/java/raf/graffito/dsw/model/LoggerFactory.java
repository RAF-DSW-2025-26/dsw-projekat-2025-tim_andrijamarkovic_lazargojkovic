package raf.graffito.dsw.model;

import raf.graffito.dsw.core.Logger;

import java.io.IOException;
//Klasa koja koristi Simple Factory Method za kreiranje loggera
public class LoggerFactory {
    public Logger createLogger(String type) {
        Logger logger = null;
        if (type.equals("console")) {
            logger = new ConsoleLogger();
        } else if (type.equals("file")) {
            logger = new FileLogger();
        }
        return logger;
    }
}
