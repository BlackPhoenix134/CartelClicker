package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Clickable;
import sf.cartel.core.Extensions.Collections;
import sf.cartel.core.Globals;
import sf.cartel.core.GlobalsPaths;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Physics.Rectangle;
import sf.cartel.core.StateMachines.ShipDestroyedState;
import sf.cartel.core.StateMachines.ShipTravelState;
import sf.cartel.core.StateMachines.StateMachineStates;
import sf.cartel.core.StateMachines.TravelStartState;
import sf.cartel.core.StateMachines.TravelStateMachine;
import sf.cartel.core.StateMachines.TravelStopState;
import sf.cartel.core.StateMachines.TravelTravelState;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.core.clickHandler.ObjectClickBinding;
import sf.cartel.core.clickHandler.ObjectClickHandler;
import sf.cartel.input.InputEvent;
import sf.cartel.rendering.RenderPipeline;

public class ShipSpawnerObject extends GameObject {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private float spawnTimer = 1;
    private float deltaTimer;
    private Random random = new Random();
    private ObjectClickHandler objectClickHandler;

    ShipSpawnerObject(String uuid) {
        super(uuid);
    }

    public void init(ObjectClickHandler objectClickHandler) {
        this.objectClickHandler = objectClickHandler;
    }

    private void spawn(int amount) {
        for(int i = 0; i < amount; i++)
            spawn();
    }

    private void spawn() {
        StateMachineObject shipObject = gameObjectManager.create(StateMachineObject.class);



        Texture texture = Assets.getAsset(AssetDescriptors.SHIP2_SHEET);
        TravelStateMachine stateMachine = new TravelStateMachine(shipObject, Collections.getRandomItem(GlobalsPaths.getShipNodes()), 0.01f, 0.025f);

        ObjectClickBinding clickBinding = objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                stateMachine.transition(StateMachineStates.ShipDestroyed.ordinal());
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(shipObject.getSprite().getX(), shipObject.getSprite().getY());

            }

            @Override
            public Area2D getArea2D() {
                Sprite sprite = shipObject.getSprite();
                float scaledWidth = sprite.getWidth() * sprite.getScaleX();
                float scaledHeight = sprite.getHeight() * sprite.getScaleY();
                return new Rectangle(sprite.getX() - scaledWidth/2, sprite.getY() - scaledHeight/2,
                        scaledWidth, scaledHeight);
            }
        }, Globals.CLICK_ORDER_WORLD + 10, false);

        stateMachine.addState(StateMachineStates.TravelShip.ordinal(),
                new ShipTravelState(stateMachine, new AnimationController(texture, 3, 3, 0.1f)));
        stateMachine.addState(StateMachineStates.ShipDestroyed.ordinal(),
                new ShipDestroyedState(stateMachine, clickBinding, new AnimationController(Assets.getAsset(AssetDescriptors.SHIP2_DESTROYED_SHEET), 3, 3, 0.2f)));
        stateMachine.transition(StateMachineStates.TravelShip.ordinal());
        shipObject.init(stateMachine);
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);

        deltaTimer += delta;
        if(deltaTimer >= spawnTimer) {
            deltaTimer = 0;
            spawnTimer = getNextSpawnTimer();
            spawn(random.nextInt(3));
        }
    }

    private float getNextSpawnTimer() {
        return GoodMath.randFloat(2, 4);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }
}
