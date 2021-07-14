package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayDeque;
import java.util.Queue;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Physics.Area2D;
import sf.cartel.core.Visuals.FourDirSheet;
import sf.cartel.rendering.RenderPipeline;

public class TrainStickmanEventObject extends GameObject {
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private GameObjectManager gameObjectManagerStickmans = new GameObjectManager();
    private TrainObject trainObject1;
    private TrainObject trainObject2;
    private Queue<Vector2[]> stickmanPoints = new ArrayDeque<>();
    private StickmanObject stickmanObjectJoe;
    private StickmanObject stickmanObjectDon;

    TrainStickmanEventObject(String uuid) {
        super(uuid);
    }

    public void init() {
        trainObject1 = gameObjectManager.create(TrainObject.class);
        trainObject1.init(new FourDirSheet(Assets.getAsset(AssetDescriptors.TRAIN_4DIR_SHEET)),
                new Vector2[]{
                        new Vector2(-20.43332f, -39.199936f),
                        new Vector2(-80.84998f, 5.3834f),
                        new Vector2(-127.1f, 55.3834f),
                        new Vector2(-97.93331f, 106.63342f),
                        new Vector2(-34.18332f, 98.71675f),
                        new Vector2(12.483353f, 52.883392f),
                        new Vector2(32.066696f, -4.1999197f),
                        new Vector2(32.066696f, -53.3666f),
                        new Vector2(8.316696f, -67.949936f),
                });
        trainObject1.getSprite().setScale(0.02f);

        trainObject2 = gameObjectManager.create(TrainObject.class);
        trainObject2.init(new FourDirSheet(Assets.getAsset(AssetDescriptors.TRAIN_4DIR_SHEET)),
                new Vector2[]{
                        new Vector2(-192.84775f, 134.68732f),
                        new Vector2(-141.4897f, 124.14362f),
                        new Vector2(-60.881393f, 110.87896f),
                        new Vector2(14.965278f, 75.50653f),
                        new Vector2(29.5904f, 17.005976f),
                        new Vector2(30.950857f, -49.657448f),
                        new Vector2(37.753265f, -106.45741f),
                        new Vector2(50.337704f, -134.3472f),
                        new Vector2(-39.453857f, -103.73645f),
                        new Vector2(-87.41072f, -43.87541f),
                        new Vector2(-113.599915f, 17.005976f),
                        new Vector2(-161.55675f, 63.60235f),
                        new Vector2(-235.3627f, 77.54725f)

                });
        trainObject2.getSprite().setScale(0.02f);
        initStickmanPoints();
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
        gameObjectManagerStickmans.update(delta);

        if(gameObjectManagerStickmans.getGameObjects().size() < 10) {
            spawnStickman(Globals.getRandomInt(3, 6));
        }

        for(GameObject gameObject : gameObjectManagerStickmans.getGameObjects()) {
            StickmanObject stickmanObject = (StickmanObject)gameObject;
            Area2D stickmanArea = stickmanObject.getArea2D();

            if(stickmanArea.intersects(Sprites.position(trainObject1.getSprite()))
            || stickmanArea.intersects(Sprites.position(trainObject2.getSprite()))) {
                stickmanObject.kill();
            }
        }
    }

    private void spawnStickman(int value) {
        for (int i = 0; i < value && stickmanPoints.peek() != null; i++) {
            if(stickmanObjectDon == null) {
                stickmanObjectDon = spawnStickman(Assets.getAsset(AssetDescriptors.STICKMAN_DON));
                stickmanObjectDon.getSprite().setScale(stickmanObjectDon.getSprite().getScaleX() * 1.5f);
            }
            else if(stickmanObjectJoe == null) {
                stickmanObjectJoe = spawnStickman(Assets.getAsset(AssetDescriptors.STICKMAN_JOE));
                stickmanObjectJoe.getSprite().setScale(stickmanObjectJoe.getSprite().getScaleX() * 1.5f);
            }
            else
                spawnStickman(Assets.getAsset(AssetDescriptors.STICKMAN));
        }
    }

    private StickmanObject spawnStickman(Texture texture) {
        StickmanObject stickmanObject = gameObjectManagerStickmans.create(StickmanObject.class);
        stickmanObject.init(new Sprite(texture), stickmanPoints.poll(), this, gameObjectManager);
        stickmanObject.getSprite().setScale(0.03f);
        return stickmanObject;
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
        gameObjectManagerStickmans.draw(delta, pipeline);
    }


    private void initStickmanPoints() {

        stickmanPoints.add(new Vector2[]{
                new Vector2(46.910015f, 31.055416f),
                new Vector2(45.888474f, -1.8898888f),
                new Vector2(10.90003f, -14.914776f),
                new Vector2(-23.577595f, 7.55954f),
                new Vector2(-10.552707f, 46.6342f),
                new Vector2(48.953136f, 71.662415f)
        });

        stickmanPoints.add(new Vector2[]{
                new Vector2(-134.16142f, 110.48168f),
                new Vector2(-78.74181f, 93.62594f),
                new Vector2(-62.65226f, 57.61597f),
                new Vector2(-83.59422f, 42.037186f),
                new Vector2(-139.01382f, 45.612637f),
                new Vector2(-169.66061f, 53.52972f),
                new Vector2(-170.1714f, 94.13672f)
        });


        stickmanPoints.add(new Vector2[]{

                new Vector2(-12.35546f, 132.14769f),
                new Vector2(-67.167305f, 128.69798f),
                new Vector2(-75.9832f, 96.11752f),
                new Vector2(-77.516396f, 35.556232f),
                new Vector2(-46.85244f, -13.8894005f),
                new Vector2(30.574074f, -15.422588f),
                new Vector2(55.871822f, 49.73831f),
                new Vector2(33.64047f, 107.616516f)
        });


        stickmanPoints.add(new Vector2[]{
                new Vector2(-21.780361f, 127.19885f),
                new Vector2(-91.38601f, 115.11164f),
                new Vector2(-123.896454f, 80.51721f),
                new Vector2(-87.21802f, 42.171585f),
                new Vector2(-38.03556f, 38.420387f),
                new Vector2(-13.0275345f, 88.019615f),
                new Vector2(-5.525116f, 115.11164f)
        });


        stickmanPoints.add(new Vector2[]{
                new Vector2(12.397282f, -14.930066f),
                new Vector2(37.82212f, 48.00678f),
                new Vector2(-25.114765f, 97.60603f),
                new Vector2(-101.80604f, 99.27324f),
                new Vector2(-75.54761f, 44.672367f),
                new Vector2(-35.117973f, 16.329948f)
        });


        stickmanPoints.add(new Vector2[]{
                new Vector2(46.910015f, 31.055416f),
                new Vector2(45.888474f, -1.8898888f),
                new Vector2(10.90003f, -14.914776f),
                new Vector2(-23.577595f, 7.55954f),
                new Vector2(-10.552707f, 46.6342f),
                new Vector2(48.953136f, 71.662415f)
        });


        stickmanPoints.add(new Vector2[]{

                new Vector2(-12.35546f, 132.14769f),
                new Vector2(-67.167305f, 128.69798f),
                new Vector2(-75.9832f, 96.11752f),
                new Vector2(-77.516396f, 35.556232f),
                new Vector2(-46.85244f, -13.8894005f),
                new Vector2(30.574074f, -15.422588f),
                new Vector2(55.871822f, 49.73831f),
                new Vector2(33.64047f, 107.616516f)
        });






        stickmanPoints.add(new Vector2[]{
                new Vector2(-134.16142f, 110.48168f),
                new Vector2(-78.74181f, 93.62594f),
                new Vector2(-62.65226f, 57.61597f),
                new Vector2(-83.59422f, 42.037186f),
                new Vector2(-139.01382f, 45.612637f),
                new Vector2(-169.66061f, 53.52972f),
                new Vector2(-170.1714f, 94.13672f)
        });


        stickmanPoints.add(new Vector2[]{
                new Vector2(-14.6947365f, 126.36525f),
                new Vector2(-34.284363f, 79.68361f),
                new Vector2(-62.626785f, 49.673985f),
                new Vector2(-37.20197f, 14.662744f),
                new Vector2(14.898096f, 43.83879f),
                new Vector2(36.988487f, 93.02123f)
        });

        stickmanPoints.add(new Vector2[]{
                new Vector2(-21.780361f, 127.19885f),
                new Vector2(-91.38601f, 115.11164f),
                new Vector2(-123.896454f, 80.51721f),
                new Vector2(-87.21802f, 42.171585f),
                new Vector2(-38.03556f, 38.420387f),
                new Vector2(-13.0275345f, 88.019615f),
                new Vector2(-5.525116f, 115.11164f)
        });
        stickmanPoints.add(new Vector2[]{
                new Vector2(55.744522f, 65.095604f),
                new Vector2(17.398912f, 56.342785f),
                new Vector2(-50.53958f, 52.174793f),
                new Vector2(-78.04839f, 82.18442f),
                new Vector2(-90.55242f, 52.174793f),
                new Vector2(-83.88361f, 25.082766f),
                new Vector2(-3.8579395f, 4.6595354f),
                new Vector2(52.41012f, 43.005188f)
        });

        stickmanPoints.add(new Vector2[]{
                new Vector2(-14.6947365f, 126.36525f),
                new Vector2(-34.284363f, 79.68361f),
                new Vector2(-62.626785f, 49.673985f),
                new Vector2(-37.20197f, 14.662744f),
                new Vector2(14.898096f, 43.83879f),
                new Vector2(36.988487f, 93.02123f)
        });
        stickmanPoints.add(new Vector2[]{
                new Vector2(-14.6947365f, 126.36525f),
                new Vector2(-34.284363f, 79.68361f),
                new Vector2(-62.626785f, 49.673985f),
                new Vector2(-37.20197f, 14.662744f),
                new Vector2(14.898096f, 43.83879f),
                new Vector2(36.988487f, 93.02123f)
        });



        stickmanPoints.add(new Vector2[]{
                new Vector2(-21.780361f, 127.19885f),
                new Vector2(-91.38601f, 115.11164f),
                new Vector2(-123.896454f, 80.51721f),
                new Vector2(-87.21802f, 42.171585f),
                new Vector2(-38.03556f, 38.420387f),
                new Vector2(-13.0275345f, 88.019615f),
                new Vector2(-5.525116f, 115.11164f)
        });


        stickmanPoints.add(new Vector2[]{
                new Vector2(12.397282f, -14.930066f),
                new Vector2(37.82212f, 48.00678f),
                new Vector2(-25.114765f, 97.60603f),
                new Vector2(-101.80604f, 99.27324f),
                new Vector2(-75.54761f, 44.672367f),
                new Vector2(-35.117973f, 16.329948f)
        });
        stickmanPoints.add(new Vector2[]{
                new Vector2(55.744522f, 65.095604f),
                new Vector2(17.398912f, 56.342785f),
                new Vector2(-50.53958f, 52.174793f),
                new Vector2(-78.04839f, 82.18442f),
                new Vector2(-90.55242f, 52.174793f),
                new Vector2(-83.88361f, 25.082766f),
                new Vector2(-3.8579395f, 4.6595354f),
                new Vector2(52.41012f, 43.005188f)
        });


        stickmanPoints.add(new Vector2[]{
                new Vector2(55.744522f, 65.095604f),
                new Vector2(17.398912f, 56.342785f),
                new Vector2(-50.53958f, 52.174793f),
                new Vector2(-78.04839f, 82.18442f),
                new Vector2(-90.55242f, 52.174793f),
                new Vector2(-83.88361f, 25.082766f),
                new Vector2(-3.8579395f, 4.6595354f),
                new Vector2(52.41012f, 43.005188f)
        });

        stickmanPoints.add(new Vector2[]{
                new Vector2(46.910015f, 31.055416f),
                new Vector2(45.888474f, -1.8898888f),
                new Vector2(10.90003f, -14.914776f),
                new Vector2(-23.577595f, 7.55954f),
                new Vector2(-10.552707f, 46.6342f),
                new Vector2(48.953136f, 71.662415f)
        });

        stickmanPoints.add(new Vector2[]{
                new Vector2(-134.16142f, 110.48168f),
                new Vector2(-78.74181f, 93.62594f),
                new Vector2(-62.65226f, 57.61597f),
                new Vector2(-83.59422f, 42.037186f),
                new Vector2(-139.01382f, 45.612637f),
                new Vector2(-169.66061f, 53.52972f),
                new Vector2(-170.1714f, 94.13672f)
        });
        stickmanPoints.add(new Vector2[]{
                new Vector2(12.397282f, -14.930066f),
                new Vector2(37.82212f, 48.00678f),
                new Vector2(-25.114765f, 97.60603f),
                new Vector2(-101.80604f, 99.27324f),
                new Vector2(-75.54761f, 44.672367f),
                new Vector2(-35.117973f, 16.329948f)
        });

        stickmanPoints.add(new Vector2[]{

                new Vector2(-12.35546f, 132.14769f),
                new Vector2(-67.167305f, 128.69798f),
                new Vector2(-75.9832f, 96.11752f),
                new Vector2(-77.516396f, 35.556232f),
                new Vector2(-46.85244f, -13.8894005f),
                new Vector2(30.574074f, -15.422588f),
                new Vector2(55.871822f, 49.73831f),
                new Vector2(33.64047f, 107.616516f)
        });

    }

    public void returnSpline(StickmanObject stickmanObject, Vector2[] trackPoints) {
        if(stickmanObject == stickmanObjectJoe)
            stickmanObjectJoe = null;
        if(stickmanObject == stickmanObjectDon)
            stickmanObjectDon = null;
        stickmanPoints.add(trackPoints);
    }
}
