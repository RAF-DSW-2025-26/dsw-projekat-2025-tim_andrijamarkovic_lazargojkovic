package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Presentation extends GraffNodeComposite implements IPublisherView {
    private List<ISubscriberView> subscribers = new ArrayList<>();

    @Getter
    private static int presentationNumber = 1;
    public Presentation(String name,GraffNode parent) {
        super(name, parent);
        presentationNumber++;
    }

    @Override
    public void addSubscriber(ISubscriberView subscriber)
    {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriberView subscriber)
    {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String message)
    {
        for(ISubscriberView subscriber : subscribers)
        {
            subscriber.update(message, (Project) this.parent);
        }
    }
}
