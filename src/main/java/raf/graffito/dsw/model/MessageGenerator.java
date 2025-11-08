package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.IPublisher;
import raf.graffito.dsw.core.ISubscriber;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//Observer klasa koja kreira poruke i salje ih loggerima za ispis
public class MessageGenerator implements IPublisher {

    private List<ISubscriber> subscribers = new ArrayList<>();

    private String content;
    private Type type;
    private LocalDateTime timestamp;

    public MessageGenerator()
    {
    }
    //Kreiranje poruke koja ce se ispisati
    public String createMessage(String content, Type type, LocalDateTime timestamp){
        this.content = content;
        this.type = type;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");//Formatiranje timestamp-a koji se ispisuje u poruci
        String date = timestamp.format(formatter);
        this.timestamp = LocalDateTime.parse(date, formatter);

        return "["+type.toString()+"]["+ date +"]["+content+"]";
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
        for(ISubscriber subscriber : subscribers){
            subscriber.update(message);
        }
        //Za svaki tip poruke otvara dijalog koji prikazuje poruku
        switch(type){
            case UPOZORENJE:
                JOptionPane.showMessageDialog(MainFrame.getInstance(), message,type.toString(), JOptionPane.WARNING_MESSAGE);
                break;
            case GRESKA:
                JOptionPane.showMessageDialog(MainFrame.getInstance(), message,type.toString(), JOptionPane.ERROR_MESSAGE);
                break;
            case OBAVESTENJE:
                JOptionPane.showMessageDialog(MainFrame.getInstance(), message,type.toString(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
