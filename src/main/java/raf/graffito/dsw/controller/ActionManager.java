package raf.graffito.dsw.controller;

import lombok.Getter;

@Getter
public class ActionManager
{
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
}
