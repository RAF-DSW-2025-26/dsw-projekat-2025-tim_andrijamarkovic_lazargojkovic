package raf.graffito.dsw.core;
//Publisher za Observer sablon koji se koristi za upravljanje pogledima i azuriranjem tabova i desnog panela
public interface IPublisherView
{
    void addSubscriber(ISubscriberView subscriber);
    void removeSubscriber(ISubscriberView subscriber);
    void notifySubscribers(String message);
}
