package raf.graffito.dsw.core;
import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.repository.GraffRepository;
import raf.graffito.dsw.repository.GraffRepositoryImpl;

@Getter
public class ApplicationFramework {
    // Buduća polja za model celog projekta
    protected GraffRepository graffRepository;

    public ApplicationFramework()
    {
        graffRepository = new GraffRepositoryImpl();
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
