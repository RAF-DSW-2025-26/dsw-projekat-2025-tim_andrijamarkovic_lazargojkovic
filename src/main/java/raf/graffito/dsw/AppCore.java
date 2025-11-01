package raf.graffito.dsw;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.MessageGenerator;
import raf.graffito.dsw.repository.GraffRepository;
import raf.graffito.dsw.repository.GraffRepositoryImpl;

import java.time.LocalDateTime;

import static raf.graffito.dsw.core.Type.GRESKA;
import static raf.graffito.dsw.core.Type.OBAVESTENJE;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.initialize();
    }
}