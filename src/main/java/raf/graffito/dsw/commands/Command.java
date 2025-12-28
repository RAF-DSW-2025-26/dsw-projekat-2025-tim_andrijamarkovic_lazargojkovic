package raf.graffito.dsw.commands;

// interface za command pattern koji omogućava undo i redo
public interface Command {
    void execute();
    void undo();
}
