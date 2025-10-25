package raf.graffito.dsw.model;

import raf.graffito.dsw.core.Logger;

public class ConsoleLogger extends Logger
{
    @Override
    public void update(String message)
    {
        System.out.println(message);
    }
}
