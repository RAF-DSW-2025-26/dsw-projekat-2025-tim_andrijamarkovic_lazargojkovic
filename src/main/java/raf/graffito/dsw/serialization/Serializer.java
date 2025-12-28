package raf.graffito.dsw.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;

import java.io.File;
import java.io.IOException;

public class Serializer {

    // serializer koji se bavi čuvanjem i učitavanjem projekata i šablona
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    // metoda za čuvanje
    public static void saveProject(Project project, File file) throws IOException {
        mapper.writeValue(file, project);
    }

    // metoda za učitavanje
    public static Project loadProjectFromJson(File file) throws IOException {
        Project project = mapper.readValue(file, Project.class);
        fixParents(project);
        return project;
    }

    // pomoćna metoda za korektno dodeljivanje roditelja u tree
    private static void fixParents(Project project) {
        for (GraffNode p : project.getChildren()) {
            if(p instanceof Presentation) {
                p.setParent(project);
                for (GraffNode s : ((Presentation) p).getChildren()) {
                    s.setParent(p);
                }
            }else if(p instanceof Slide) {
                p.setParent(project);
            }
        }
    }
}
