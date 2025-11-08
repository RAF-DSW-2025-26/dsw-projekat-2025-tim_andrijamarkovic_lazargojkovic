package raf.graffito.dsw.core;

import java.util.concurrent.Flow;
//Publisher za Observer sablon koji se koristi za generisanje loggera i poruka
public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void notifySubscribers(String message);
}
