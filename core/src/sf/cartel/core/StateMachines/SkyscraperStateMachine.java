package sf.cartel.core.StateMachines;


import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.gameObjects.GameObject;
import sf.cartel.gameObjects.SkyscraperObject;
import sf.cartel.gameObjects.StateMachineObject;

public class SkyscraperStateMachine extends StateMachine {
    protected SkyscraperObject gameObject;

    public SkyscraperStateMachine(SkyscraperObject gameObject) {
        this.gameObject = gameObject;
    }

    public SkyscraperObject getGameObject() {
        return gameObject;
    }
    public void setGameObject(SkyscraperObject gameObject) {
        this.gameObject = gameObject;
    }
}
