package raf.graffito.dsw.model;

import raf.graffito.dsw.core.Logger;

import java.io.FileWriter;
import java.io.IOException;
//Logger koji ispisuje poruke u fajl log.txt
public class FileLogger extends Logger
{
    @Override
    public void update(String message)
    {
        try
        {
            FileWriter fw = new FileWriter("log.txt");
            fw.write(message);
            fw.close();
        }
        catch(IOException e)
        {
            System.err.println("File " + "log.txt" + " not found");
            e.printStackTrace();
        }
    }
}
