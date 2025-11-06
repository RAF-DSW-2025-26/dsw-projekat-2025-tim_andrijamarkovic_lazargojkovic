package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.core.IPublisher;
import raf.graffito.dsw.core.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Presentation extends GraffNodeComposite implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();

    @Getter
    private static int presentationNumber = 1;
    public Presentation(String name,GraffNode parent) {
        super(name, parent);
        presentationNumber++;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber)
    {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber)
    {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String message)
    {
        for(ISubscriber subscriber : subscribers)
        {
            subscriber.update(message);
        }
    }
}
