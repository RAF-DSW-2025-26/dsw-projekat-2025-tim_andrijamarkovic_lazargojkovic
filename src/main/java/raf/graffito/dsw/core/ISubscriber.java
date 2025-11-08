package raf.graffito.dsw.core;
//Subscriber za Observer sablon koji se koristi za generisanje loggera i poruka
public interface ISubscriber {
    void update(String message);
}
