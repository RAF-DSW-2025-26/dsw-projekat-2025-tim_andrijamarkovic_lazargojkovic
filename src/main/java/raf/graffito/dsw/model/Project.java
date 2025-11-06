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
public class Project extends GraffNodeComposite implements IPublisher {
    private String title;
    private String author;
    private int number;
    @Getter
    private static int projectNumber = 1;
    private List<ISubscriber> subscribers = new ArrayList<>();

    public Project(String name,String title,String author,GraffNode parent) {
        super(name,parent);
        this.author = author;
        this.title = title;
        this.number = 0;
        projectNumber++;
    }

    public void incrementNumber() {
        this.number++;
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

    public void setAuthor(String author)
    {
        this.author = author;
        notifySubscribers("authorChanged");
    }
}
