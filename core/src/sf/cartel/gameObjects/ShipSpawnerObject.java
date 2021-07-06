package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Collections;
import sf.cartel.core.GlobalsPaths;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.StateMachines.ShipTravelState;
import sf.cartel.core.StateMachines.StateMachineStates;
import sf.cartel.core.StateMachines.TravelStartState;
import sf.cartel.core.StateMachines.TravelStateMachine;
import sf.cartel.core.StateMachines.TravelStopState;
import sf.cartel.core.StateMachines.TravelTravelState;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class ShipSpawnerObject extends GameObject {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private float spawnTimer = 1;
    private float deltaTimer;
    private Random random = new Random();

    ShipSpawnerObject(String uuid) {
        super(uuid);
    }

    private void spawn(int amount) {
        for(int i = 0; i < amount; i++)
            spawn();
    }

    private void spawn() {
        StateMachineObject shipObject = gameObjectManager.create(StateMachineObject.class);
        Texture texture = Assets.getAsset(AssetDescriptors.SHIP2_SHEET);
        TravelStateMachine stateMachine = new TravelStateMachine(shipObject, Collections.getRandomItem(GlobalsPaths.getShipNodes()), 0.01f, 0.035f);
        stateMachine.addState(StateMachineStates.TravelShip.ordinal(),
                new ShipTravelState(stateMachine, new AnimationController(texture, 2, 2, 0.2f)));
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
            spawn(random.nextInt(2));
        }
    }

    private float getNextSpawnTimer() {
        return GoodMath.randFloat(4, 6);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }
}
