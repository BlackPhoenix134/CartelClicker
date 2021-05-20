package sf.cartel.core.StateMachines;


import sf.cartel.gameObjects.GameObject;

public class StateMachineGameObj extends StateMachine {
    private GameObject gameObject;

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public StateMachineGameObj(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
