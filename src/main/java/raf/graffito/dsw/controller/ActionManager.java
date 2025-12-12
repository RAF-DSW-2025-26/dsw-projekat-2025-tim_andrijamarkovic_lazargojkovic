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
}
