package raf.graffito.dsw.state;

import lombok.Getter;
import raf.graffito.dsw.state.concrete.*;
@Getter
public class StateManager
{
    private State currentState;
    private AddState addState;
    private SelectState selectState;
    private MoveState moveState;
    private ResizeState resizeState;
    private ZoomState zoomState;

    public StateManager()
    {
        addState = new AddState();
        selectState = new SelectState();
        moveState = new MoveState();
        resizeState = new ResizeState();
        zoomState = new ZoomState();
        currentState = selectState;
    }

    public void setAddState(AddState addState)
    {
        currentState = addState;
    }

    public void setSelectState(SelectState selectState)
    {
        currentState = selectState;
    }

    public void setMoveState(MoveState moveState)
    {
        currentState = moveState;
    }

    public void setResizeState(ResizeState resizeState)
    {
        currentState = resizeState;
    }

    public void setZoomState(ZoomState zoomState)
    {
        currentState = zoomState;
    }
}
