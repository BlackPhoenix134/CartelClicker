package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Collections;
import sf.cartel.core.GlobalsPaths;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.StateMachines.TravelStateMachine;
import sf.cartel.core.StateMachines.TravelStartState;
import sf.cartel.core.StateMachines.StateMachineStates;
import sf.cartel.core.StateMachines.TravelStopState;
import sf.cartel.core.StateMachines.TravelTravelState;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class PlaneSpawnerObject extends GameObject {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private float spawnTimer = 1;
    private float deltaTimer;
    private Random random = new Random();

    PlaneSpawnerObject(String uuid) {
        super(uuid);
    }

    private void spawn(int amount) {
        for(int i = 0; i < amount; i++)
            spawn();
    }

    private void spawn() {
        StateMachineObject plane1Object = gameObjectManager.create(StateMachineObject.class);
        Texture planeTexture = Assets.getAsset(AssetDescriptors.PLANE1_SHEET);
        TravelStateMachine stateMachine = new TravelStateMachine(plane1Object, Collections.getRandomItem(GlobalsPaths.getPlaneNodesOffset()), 0.01f, 0.025f);
        stateMachine.addState(StateMachineStates.TravelStart.ordinal(),
                new TravelStartState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.addState(StateMachineStates.TravelTravel.ordinal(),
                new TravelTravelState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.addState(StateMachineStates.TravelStop.ordinal(),
                new TravelStopState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.transition(StateMachineStates.TravelStart.ordinal());
        plane1Object.init(stateMachine);
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
        return GoodMath.randFloat(2, 6);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }
}
