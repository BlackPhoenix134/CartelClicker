package sf.cartel.gameObjects;

import sf.cartel.core.StateMachines.StateMachine;
import sf.cartel.core.StateMachines.StateMachineStates;

public class SkyscraperObject extends StateMachineObject {
    private SkyscraperSpawnerObject skyscraperSpawnerObject;
    private boolean isTargeted;

    public SkyscraperObject(String uuid) {
        super(uuid);
    }

    public void init(StateMachine stateMachine, SkyscraperSpawnerObject skyscraperSpawnerObject) {
        super.init(stateMachine);
        this.skyscraperSpawnerObject = skyscraperSpawnerObject;
    }

    public void destroy() {
        getStateMachine().transition(StateMachineStates.BuildingDestroyed.ordinal());
    }

    public SkyscraperSpawnerObject getSkyscraperSpawnerObject() {
        return skyscraperSpawnerObject;
    }

    public void setSkyscraperSpawnerObject(SkyscraperSpawnerObject skyscraperSpawnerObject) {
        this.skyscraperSpawnerObject = skyscraperSpawnerObject;
    }

    public boolean isTargeted() {
        return isTargeted;
    }

    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    @Override
    protected void onObjectDestroyed() {
        super.onObjectDestroyed();
        skyscraperSpawnerObject.returnSkyscraper(this);
    }
}
