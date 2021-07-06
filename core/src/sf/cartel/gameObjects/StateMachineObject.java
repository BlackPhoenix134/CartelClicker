package sf.cartel.gameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.core.Globals;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.StateMachines.StateMachine;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;


public class StateMachineObject extends GameObject {
    private StateMachine stateMachine;
    private Sprite sprite;


    public StateMachineObject(String uuid){
        super(uuid);
    }

    public void init(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
         this.sprite = sprite;
    }

    @Override
    public void update(float delta) {
        stateMachine.update(delta);
    }


    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.draw(delta, pipeline);
    }
}
