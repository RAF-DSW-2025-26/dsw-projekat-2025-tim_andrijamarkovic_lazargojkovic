package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.IPublisher;
import raf.graffito.dsw.core.ISubscriber;
import raf.graffito.dsw.core.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageGenerator implements IPublisher {

    private List<ISubscriber> subscribers = new ArrayList<>();

    private String content;
    private Type type;
    private LocalDateTime timestamp;

    public MessageGenerator(String content, Type type, LocalDateTime timestamp)
    {
        this.content = content;
        this.type = type;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(formatter);
        this.timestamp = LocalDateTime.parse(date, formatter);
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
    public void notifySubscribers()
    {

    }
}
