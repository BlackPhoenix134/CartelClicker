package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Collections;
import sf.cartel.core.GlobalsPaths;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.PathNode;
import sf.cartel.core.StateMachines.RoguePlaneDestroyedState;
import sf.cartel.core.StateMachines.RoguePlaneStartState;
import sf.cartel.core.StateMachines.RoguePlaneStateMachine;
import sf.cartel.core.StateMachines.RoguePlaneTravelState;
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
    private SkyscraperSpawnerObject skyscraperSpawnerObject;

    PlaneSpawnerObject(String uuid) {
        super(uuid);
    }

    public void init(SkyscraperSpawnerObject skyscraperSpawnerObject) {
        this.skyscraperSpawnerObject = skyscraperSpawnerObject;
    }

    private void spawn(int amount) {
        for(int i = 0; i < amount; i++)
            spawn();
    }

    private void spawn() {
        int rogueChance = random.nextInt(100);
        PathNode startNode = Collections.getRandomItem(GlobalsPaths.getPlaneNodesOffset());
        SkyscraperObject skyscraperObj = skyscraperSpawnerObject.getRandomAvailableSkyscraper();
        if(skyscraperObj != null && rogueChance >= 40)  {
            float distanceToTarget = Vector2.dst(startNode.getPosition().x, startNode.getPosition().y, skyscraperObj.getSprite().getX(), skyscraperObj.getSprite().getY());
            if(distanceToTarget >= 200)
                spawnRoguePlane(startNode, skyscraperObj);
            else
                spawnNormalPlane(startNode);
        }
        else
            spawnNormalPlane(startNode);

    }

    private void spawnNormalPlane(PathNode startNode) {
        StateMachineObject plane1Object = gameObjectManager.create(StateMachineObject.class);
        Texture planeTexture = Assets.getAsset(AssetDescriptors.PLANE1_SHEET);
        TravelStateMachine stateMachine = new TravelStateMachine(plane1Object, startNode, 0.01f, 0.025f);
        stateMachine.addState(StateMachineStates.TravelStart.ordinal(),
                new TravelStartState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.addState(StateMachineStates.TravelTravel.ordinal(),
                new TravelTravelState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.addState(StateMachineStates.TravelStop.ordinal(),
                new TravelStopState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.transition(StateMachineStates.TravelStart.ordinal());
        plane1Object.init(stateMachine);
    }

    private void spawnRoguePlane(PathNode startNode, SkyscraperObject skyscraperObj) {
        StateMachineObject plane1Object = gameObjectManager.create(StateMachineObject.class);
        Texture planeTexture = Assets.getAsset(AssetDescriptors.PLANE1_SHEET);
        skyscraperObj.setTargeted(true);

        RoguePlaneStateMachine stateMachine = new RoguePlaneStateMachine(plane1Object, gameObjectManager, startNode, skyscraperObj, 0.01f, 0.025f);
        stateMachine.addState(StateMachineStates.RoguePlaneStart.ordinal(),
                new RoguePlaneStartState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.addState(StateMachineStates.RoguePlaneTravel.ordinal(),
                new RoguePlaneTravelState(stateMachine, new AnimationController(planeTexture, 2, 2, 0.2f)));
        stateMachine.addState(StateMachineStates.RoguePlaneDestroyed.ordinal(),
                new RoguePlaneDestroyedState(stateMachine, new AnimationController(Assets.getAsset(AssetDescriptors.PLANE1_DESTROYED_SHEET), 2, 2, 0.2f)));
        stateMachine.transition(StateMachineStates.RoguePlaneStart.ordinal());
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
