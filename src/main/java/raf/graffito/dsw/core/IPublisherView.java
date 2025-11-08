package raf.graffito.dsw.core;

public interface IPublisherView
{
    void addSubscriber(ISubscriberView subscriber);
    void removeSubscriber(ISubscriberView subscriber);
    void notifySubscribers(String message);
}
