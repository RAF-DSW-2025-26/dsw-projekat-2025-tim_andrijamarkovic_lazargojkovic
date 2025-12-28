package raf.graffito.dsw.controller.window;

import raf.graffito.dsw.controller.WindowModeController;
import raf.graffito.dsw.gui.swing.window.WindowFullscreenMode;
import raf.graffito.dsw.gui.swing.window.WindowModeStrategy;

public class FullscreenModeAction extends AbstractWindowModeAction {

    public FullscreenModeAction(WindowModeController controller) {
        super("Fullscreen", controller);
    }

    @Override
    protected WindowModeStrategy getStrategy() {
        return new WindowFullscreenMode();
    }
}