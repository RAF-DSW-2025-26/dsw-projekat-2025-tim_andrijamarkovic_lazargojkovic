package raf.graffito.dsw;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.MessageGenerator;

import java.time.LocalDateTime;

import static raf.graffito.dsw.core.Type.GRESKA;
import static raf.graffito.dsw.core.Type.OBAVESTENJE;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();

        LoggerFactory loggerFactory = new LoggerFactory();
        Logger cLogger = loggerFactory.createLogger("console");
        Logger fLogger = loggerFactory.createLogger("file");
        MessageGenerator msg = new MessageGenerator();
        msg.addSubscriber(cLogger);
        msg.addSubscriber(fLogger);
        msg.notifySubscribers(msg.createMessage("tructrucbla",GRESKA,LocalDateTime.now()));
    }
}