package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Collections;
import sf.cartel.core.GlobalsPaths;
import sf.cartel.core.Math.GoodMath;
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
        PlanePathFollowerObject plane1Object = gameObjectManager.create(PlanePathFollowerObject.class);
        Texture planeTexture = Assets.getAsset(AssetDescriptors.PLANE1_SHEET);
        plane1Object.init(planeTexture, new AnimationController(planeTexture, 2, 2, 0.2f), Collections.getRandomItem(GlobalsPaths.getPlaneNodesOffset()));
        plane1Object.getSprite().setScale(0.01f);
        plane1Object.setScaleMin(0.01f);
        plane1Object.setScaleMax(0.035f);
        plane1Object.setTravelSpeed(100);
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);

        deltaTimer += delta;
        if(deltaTimer >= spawnTimer) {
            deltaTimer = 0;
            spawnTimer = getNextSpawnTimer();
            spawn(random.nextInt(5));
        }
    }

    private float getNextSpawnTimer() {
        return GoodMath.randFloat(4, 8);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }
}
