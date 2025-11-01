package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
@Getter
public class Project extends GraffNodeComposite {
    private String title;
    private String author;
    private int number;
    @Getter
    private static int projectNumber = 1;

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
}
