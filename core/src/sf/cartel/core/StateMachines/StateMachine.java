package sf.cartel.core.StateMachines;

import java.util.HashMap;
import java.util.Map;

import sf.cartel.rendering.RenderPipeline;

public class StateMachine {
    private State currentState;
    private Map<Integer, State> states = new HashMap<>();

    public State getCurrentState() {
        return currentState;
    }

    public StateMachine() {
    }

    public void addState(int id, State state) {
        states.put(id, state);
    }

    public void transition(int id) {
        if(currentState != null)
            currentState.transitionOut();
        currentState = states.get(id);
        currentState.transitionIn();
    }

    public void update(float delta) {
        if(currentState != null)
            currentState.update(delta);
    }

    public void draw(float delta, RenderPipeline renderPipeline) {
        if(currentState != null)
            currentState.draw(delta, renderPipeline);
    }

    public <T extends StateMachine> State<T> getState(int idx) {
        return states.get(idx);
    }
}
