package sf.cartel.core.StateMachines;


import sf.cartel.rendering.RenderPipeline;

public abstract class State<T extends StateMachine> {
    protected T stateMachine;

    public State(T stateMachine) {
        this.stateMachine = stateMachine;
    }

    public abstract void transitionIn();
    public abstract void transitionOut();
    public abstract void update(float delta);
    public abstract void draw(float delta, RenderPipeline pipeline);
}
