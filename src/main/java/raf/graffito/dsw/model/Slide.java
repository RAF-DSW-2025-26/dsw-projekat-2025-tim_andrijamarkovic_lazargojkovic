package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Slide extends GraffNodeComposite implements IPublisherView {

    private List<ISubscriberView> subscribers = new ArrayList<>();

    @Getter
    private static int slideNumber = 1; //Integer koji prati broj kreiranih slajdova
    public Slide(String name, GraffNode parent) {
        super(name,parent);
        slideNumber++;
    }

    public void addElement(GraffSlideElement element) {this.getChildren().add(element);}

    public void removeElement(GraffSlideElement element) {this.getChildren().remove(element);}

    @Override
    public void addSubscriber(ISubscriberView subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriberView subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String message) {

    }
}
