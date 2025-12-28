package raf.graffito.dsw.controller;

import lombok.Getter;
import raf.graffito.dsw.controller.window.AbstractWindowModeAction;
import raf.graffito.dsw.controller.window.FullscreenModeAction;
import raf.graffito.dsw.controller.window.NormalModeAction;
import raf.graffito.dsw.controller.window.SmallModeAction;
import raf.graffito.dsw.gui.swing.MainFrame;

@Getter
public class ActionManager
{
    WindowModeController windowController = MainFrame.getInstance().getWindowModeController();
    ExitAction exitAction = new ExitAction();
    AboutUs aboutUs = new AboutUs();
    NewNodeAction newNodeAction = new NewNodeAction();
    RemovalAction removalAction = new RemovalAction();
    RenameAction renameAction = new RenameAction();
    AddTextAction addTextAction = new AddTextAction();
    AddLogoAction addLogoAction = new AddLogoAction();
    SelectAction selectAction = new SelectAction();
    MoveAction moveAction = new MoveAction();
    DeleteAction deleteAction = new DeleteAction();
    RotateLeftAction rotateLeftAction = new RotateLeftAction();
    RotateRightAction rotateRightAction = new RotateRightAction();
    OpenAction openAction = new OpenAction();
    SaveAsAction saveAsAction = new SaveAsAction();
    SaveAction saveAction = new SaveAction();
    AddImageAction addImageAction = new AddImageAction();
    OpenTemplateAction openTemplateAction = new OpenTemplateAction();
    SaveTemplateAction saveTemplateAction = new SaveTemplateAction();
    NormalModeAction normalModeAction = new NormalModeAction(windowController);
    SmallModeAction smallModeAction = new SmallModeAction(windowController);
    FullscreenModeAction fullscreenModeAction = new FullscreenModeAction(windowController);
}
