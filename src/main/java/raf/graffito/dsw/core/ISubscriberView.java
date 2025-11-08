package raf.graffito.dsw.core;

import raf.graffito.dsw.model.Project;

public interface ISubscriberView
{
    void update(String message, Project project);
}
