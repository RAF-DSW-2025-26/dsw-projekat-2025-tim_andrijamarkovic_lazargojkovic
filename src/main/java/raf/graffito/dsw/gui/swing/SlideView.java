package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.ISubscriberView;
import raf.graffito.dsw.model.Project;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SlideView extends JPanel implements ISubscriberView {


    public SlideView() {
    }

    @Override
    public void update(String message, Project project) {

    }
}
