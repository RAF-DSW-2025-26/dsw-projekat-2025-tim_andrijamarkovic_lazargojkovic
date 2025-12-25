package raf.graffito.dsw.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.model.*;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Workspace.class, name = "workspace"),
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = Presentation.class, name = "presentation"),
        @JsonSubTypes.Type(value = Slide.class, name = "slide"),
        @JsonSubTypes.Type(value = SlideLabel.class, name = "label"),
        @JsonSubTypes.Type(value = SlideLogo.class, name = "logo"),
        @JsonSubTypes.Type(value = SlidePicture.class, name = "picture")
})

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

    public GraffNode() {
    }

    public void dodaj(GraffNode node) {

    }

    public GraffNode findByName(String name) {
       return null;
    }
}
