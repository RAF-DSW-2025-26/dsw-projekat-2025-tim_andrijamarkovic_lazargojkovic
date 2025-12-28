package raf.graffito.dsw.controller.window;

import raf.graffito.dsw.controller.WindowModeController;
import raf.graffito.dsw.gui.swing.window.WindowModeStrategy;
import raf.graffito.dsw.gui.swing.window.WindowSmallMode;

public class SmallModeAction extends AbstractWindowModeAction {

    public SmallModeAction(WindowModeController controller) {
        super("Small", controller);
    }

    @Override
    protected WindowModeStrategy getStrategy() {
        return new WindowSmallMode();
    }
}
