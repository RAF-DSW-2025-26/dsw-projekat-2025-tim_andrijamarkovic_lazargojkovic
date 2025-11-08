package raf.graffito.dsw.core;

import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;

public interface RepositoryListener {
    //pomerio sam ovaj interfejs i graffrepository u core jer kao ima vise smisla ali ako mislis da je uglednije drugacije feel free
    // metode koje dodaju na stablo i removuju za ui, koriste se u view klasama, nisam znao u momentu da stavim tip u argument
    //proveri graffRepositoryImplementation tu se dodaje i removuje listener u listu pa se posle sve zove u mainframe i dodaje u repository
    //ostatak detalja ti saljem na diskord
    void nodeAdded(GraffNodeComposite node);
    void nodeRemoved(GraffNodeComposite node);
}
