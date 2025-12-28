package raf.graffito.dsw.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter
@Setter
public class CommandManager
{
    // dva steka koja u sebi sadrže akcije koje su sledeće za undo i redo
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    // izvršavanje nove komande, dodaje se na undo stack
    public void executeCommand(Command command)
    {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    // opozivanje komande, njeno uklanjanje sa steka i dodavanje na redo stack
    public void undo()
    {
        if(undoStack.isEmpty()) return;
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    }

    // vraćanje opozvane komande, njeno uklanjanje sa steka i dodavanje na undo stack
    public void redo()
    {
        if(redoStack.isEmpty()) return;
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }
}
