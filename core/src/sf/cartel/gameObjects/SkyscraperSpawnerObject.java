package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Collections;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Math.GoodMath;
import sf.cartel.core.StateMachines.BuildingBuildState;
import sf.cartel.core.StateMachines.BuildingDestroyedState;
import sf.cartel.core.StateMachines.SkyscraperStateMachine;
import sf.cartel.core.StateMachines.StateMachineStates;
import sf.cartel.core.Visuals.AnimationController;
import sf.cartel.rendering.RenderPipeline;

public class SkyscraperSpawnerObject extends GameObject {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private float spawnTimer = 1;
    private float deltaTimer;
    private Random random = new Random();

    private List<SkyscraperObject> skyscraperObjects = new ArrayList<>();
    private Queue<Vector2> availableSpawnPoints = new ArrayDeque<>();

    SkyscraperSpawnerObject(String uuid) {
        super(uuid);
        availableSpawnPoints.add(new Vector2(-225.82741f, 285.22128f));
        availableSpawnPoints.add(new Vector2(-359.3962f, 222.9438f));
        availableSpawnPoints.add(new Vector2(-295.47983f, 116.416534f));
        availableSpawnPoints.add(new Vector2(-233.20235f, 174.59679f));
        availableSpawnPoints.add(new Vector2(10.990968f, 122.15262f));
        availableSpawnPoints.add(new Vector2(-103.73071f, 99.20827f));
        availableSpawnPoints.add(new Vector2(-66.03646f, 9.069765f));
        availableSpawnPoints.add(new Vector2(14.26874f, 11.528118f));
        availableSpawnPoints.add(new Vector2(34.754734f, -149.9017f));
        availableSpawnPoints.add(new Vector2(169.96242f, -236.76244f));
        availableSpawnPoints.add(new Vector2(350.23938f, 253.26309f));
    }


    private void spawn(int amount) {
        for(int i = 0; i < amount; i++)
            if(!availableSpawnPoints.isEmpty()) {
                spawn(availableSpawnPoints.poll());
            }
    }

    private void spawn(Vector2 position) {
        SkyscraperObject skyscraperObj = gameObjectManager.create(SkyscraperObject.class);
        skyscraperObjects.add(skyscraperObj);

        skyscraperObj.setSprite(new Sprite());
        skyscraperObj.getSprite().setPosition(position.x, position.y);
        skyscraperObj.getSprite().setScale(0.04f);
        SkyscraperStateMachine stateMachine = new SkyscraperStateMachine(skyscraperObj);
        stateMachine.addState(StateMachineStates.BuildingBuild.ordinal(),
                new BuildingBuildState(stateMachine, new AnimationController(Assets.getAsset(AssetDescriptors.BUILDING1_BUILD_SHEET), 3,3, 0.2f)));
        stateMachine.addState(StateMachineStates.BuildingDestroyed.ordinal(),
                new BuildingDestroyedState(stateMachine, new AnimationController(Assets.getAsset(AssetDescriptors.BUILDING1_DESTROYED_SHEET), 3,3, 0.2f)));
        stateMachine.transition(StateMachineStates.BuildingBuild.ordinal());

        skyscraperObj.init(stateMachine, this);
    }

    public SkyscraperObject getRandomAvailableSkyscraper() {
        if(skyscraperObjects.size() > 0) {
            SkyscraperObject skyscraperObject = Collections.getRandomItem(skyscraperObjects);
            if(!skyscraperObject.isTargeted())
                return skyscraperObject;

        }
        return null;
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);

        deltaTimer += delta;
        if(deltaTimer >= spawnTimer) {
            deltaTimer = 0;
            if(skyscraperObjects.size() < 3) {
                spawnTimer = getNextSpawnTimer();
                spawn(random.nextInt(6));
            }
        }
    }

    private float getNextSpawnTimer() {
        return GoodMath.randFloat(2, 6);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }

    public void returnSkyscraper(SkyscraperObject skyscraperObject) {
        skyscraperObjects.remove(skyscraperObject);
        availableSpawnPoints.add(Sprites.position(skyscraperObject.getSprite()));
    }
}
