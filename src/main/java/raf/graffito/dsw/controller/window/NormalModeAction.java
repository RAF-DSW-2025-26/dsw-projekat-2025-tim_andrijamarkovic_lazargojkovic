package raf.graffito.dsw.controller.window;

import raf.graffito.dsw.controller.WindowModeController;
import raf.graffito.dsw.gui.swing.window.WindowModeStrategy;
import raf.graffito.dsw.gui.swing.window.WindowNormalMode;

public class NormalModeAction extends AbstractWindowModeAction {

    public NormalModeAction(WindowModeController controller) {
        super("Normal", controller);
    }

    @Override
    protected WindowModeStrategy getStrategy() {
        return new WindowNormalMode();
    }
}