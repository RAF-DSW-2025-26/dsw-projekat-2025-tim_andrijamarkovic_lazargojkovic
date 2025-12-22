package raf.graffito.dsw.core;

import lombok.Getter;
import raf.graffito.dsw.model.ImageResource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ImageRepository implements IPublisher
{
    private List<ISubscriber> subscribers = new ArrayList<>();
    private List<ImageResource> images = new ArrayList<>();

    public void addImage(ImageResource image)
    {
        images.add(image);
        notifySubscribers("imageAdded");
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String message) {
        for(ISubscriber subscriber : subscribers){
            subscriber.update(message);
        }
    }
}
