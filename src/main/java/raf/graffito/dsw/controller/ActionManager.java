package raf.graffito.dsw.controller;

import lombok.Getter;

@Getter
public class ActionManager
{
    ExitAction exitAction = new ExitAction();
    AboutUs aboutUs = new AboutUs();
    NewNodeAction newNodeAction = new NewNodeAction();
    RemovalAction removalAction = new RemovalAction();
}
