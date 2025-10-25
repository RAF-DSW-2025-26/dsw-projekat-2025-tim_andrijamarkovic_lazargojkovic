package raf.graffito.dsw.core;

import java.util.concurrent.Flow;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void notifySubscribers();
}
