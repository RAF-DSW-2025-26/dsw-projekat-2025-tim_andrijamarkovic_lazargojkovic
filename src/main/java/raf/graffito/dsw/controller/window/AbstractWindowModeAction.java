package raf.graffito.dsw.controller.window;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.controller.WindowModeController;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.gui.swing.window.WindowModeStrategy;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
@Getter
@Setter
public abstract class AbstractWindowModeAction extends AbstractAction {

    protected final WindowModeController controller;

    protected AbstractWindowModeAction(String name, WindowModeController controller) {
        super(name);
        this.controller = controller;
    }

    protected abstract WindowModeStrategy getStrategy();

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.setMode(getStrategy());
    }
}