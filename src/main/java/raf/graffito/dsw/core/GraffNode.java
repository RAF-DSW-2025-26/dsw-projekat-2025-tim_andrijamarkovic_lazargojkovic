package raf.graffito.dsw.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;

@Getter
@Setter
//Baza Composite sablona
public abstract class GraffNode {
    protected String name;
    @JsonIgnore
    protected GraffNode parent;

    public GraffNode(String name, GraffNode parent)
    {
        this.name = name;
        this.parent = parent;
    }

    public void dodaj(GraffNode node) {

    }

    public GraffNode findByName(String name) {
       return null;
    }
}
