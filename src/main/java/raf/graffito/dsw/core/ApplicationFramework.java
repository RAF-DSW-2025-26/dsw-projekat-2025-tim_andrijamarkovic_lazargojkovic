package raf.graffito.dsw.core;
import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.MessageGenerator;

@Getter
public class ApplicationFramework {
    // Buduća polja za model celog projekta
    protected GraffRepository graffRepository;
    protected MessageGenerator generator;
    protected LoggerFactory loggerFactory;

    public ApplicationFramework()
    {
        graffRepository = new GraffRepositoryImpl();
        generator = new MessageGenerator();
        loggerFactory = new LoggerFactory();
    }

    private static ApplicationFramework instance;

    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
}
