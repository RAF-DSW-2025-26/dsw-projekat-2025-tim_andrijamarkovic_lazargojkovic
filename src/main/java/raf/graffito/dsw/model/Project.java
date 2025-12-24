package raf.graffito.dsw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends GraffNodeComposite implements IPublisherView {
    private String title;
    private String author;

    @JsonIgnore
    private int number;

    @Getter
    @JsonIgnore
    private static int projectNumber = 1;

    @JsonIgnore
    private List<ISubscriberView> subscribers = new ArrayList<>();

    @JsonIgnore
    @Setter
    @Getter
    private boolean changed = false;

    @JsonIgnore
    private File filePath;

    public Project(){
        super(null,null);
    }


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
            subscriber.update(message, this);
        }
    }

    public void setAuthor(String author)
    {
        this.author = author;
        notifySubscribers("authorChanged");
    }
}
