package raf.graffito.dsw.state;

import lombok.Getter;
import raf.graffito.dsw.state.concrete.*;
@Getter
public class StateManager
{
    private State currentState;
    private AddImageState addImageState;
    private AddLogoState addLogoState;
    private AddTextState addTextState;
    private SelectState selectState;
    private MoveState moveState;
    private ResizeState resizeState;
    private ZoomState zoomState;

    public StateManager()
    {
        addImageState = new AddImageState();
        addLogoState = new AddLogoState();
        addTextState = new AddTextState();
        selectState = new SelectState();
        moveState = new MoveState();
        resizeState = new ResizeState();
        zoomState = new ZoomState();
        currentState = selectState;
    }

    public void setAddImageState()
    {
        currentState = addImageState;
    }

    public void setAddLogoState()
    {
        currentState = addLogoState;
    }

    public void setAddTextState()
    {
        currentState = addTextState;
    }

    public void setSelectState()
    {
        currentState = selectState;
    }

    public void setMoveState()
    {
        currentState = moveState;
    }

    public void setResizeState()
    {
        currentState = resizeState;
    }

    public void setZoomState()
    {
        currentState = zoomState;
    }
}
