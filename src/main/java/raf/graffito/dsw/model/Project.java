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

    public Project(String name,String title,String author) {
        super(name);
        this.author = author;
        this.title = title;
        this.number = 0;
    }

    public void incrementNumber() {
        this.number++;
    }

    @Override
    public void dodaj(GraffNode node) {
        super.dodaj(node);
        if(node instanceof Slide) incrementNumber();
    }

    @Override
    public void remove(GraffNode node) {
        super.remove(node);
    }

    @Override
    public void ispisi(String indent) {
        System.out.println(indent + "Project: " + name + " (" + author + ")");
        for (GraffNode child : children) {
            child.ispisi(indent + "  ");
        }
    }
}
