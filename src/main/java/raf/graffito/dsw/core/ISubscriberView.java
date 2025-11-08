package raf.graffito.dsw.core;

import raf.graffito.dsw.model.Project;
//Subscriber za Observer sablon koji se koristi za upravljanje pogledima i azuriranjem tabova i desnog panela
public interface ISubscriberView
{
    void update(String message, Project project);
}
