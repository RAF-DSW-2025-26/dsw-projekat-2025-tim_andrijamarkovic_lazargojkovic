package raf.graffito.dsw.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter
@Setter
public class CommandManager
{
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command)
    {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo()
    {
        if(undoStack.isEmpty()) return;
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    }

    public void redo()
    {
        if(redoStack.isEmpty()) return;
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }
}
