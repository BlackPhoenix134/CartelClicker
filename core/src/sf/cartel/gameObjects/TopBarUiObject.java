package sf.cartel.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.DrugType;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.Globals;
import sf.cartel.core.Math.Formatter;
import sf.cartel.core.PlayerData;
import sf.cartel.rendering.RenderPipeline;

public class TopBarUiObject extends GameObject {
    private PlayerData playerData;
    private Sprite sprite;
    private GameObjectManager gameObjectManager = new GameObjectManager();

    private int DRAW_ORDER_UI = Globals.DRAW_ORDER_UI;

    TopBarUiObject(String uuid) {
        super(uuid);
    }

    public void init(Sprite sprite) {
        this.sprite = sprite;
        float spriteWidth = Sprites.scaledWidth(sprite);
        float height = Gdx.graphics.getHeight() - ( Sprites.scaledHeight(sprite) * 0.04f);
        float x = spriteWidth * 0.27f;
        float inc = 194 * sprite.getScaleX();

        AnimatedSpriteDrawableObject icon = createTopBarIcon();
        icon.init(Assets.getAsset(AssetDescriptors.ICON_WEED_SHEET), 4, 4, 0.1f);
        icon.getSprite().setScale(0.06f);
        icon.getSprite().setPosition(x, height);
        x += inc;
         icon = createTopBarIcon();
        icon.init(Assets.getAsset(AssetDescriptors.ICON_PILLS_SHEET), 3, 3, 0.2f);
        icon.getSprite().setPosition(x, height);
        icon.getSprite().setScale(0.2f);

        x += inc;
        icon = createTopBarIcon();
        icon.init(Assets.getAsset(AssetDescriptors.ICON_COKE_SHEET), 3, 3, 0.4f);
        icon.getSprite().setPosition(x, height + 15);
        icon.getSprite().setScale(0.06f);

        x += inc;
        icon = createTopBarIcon();
        icon.init(Assets.getAsset(AssetDescriptors.ICON_PILLS2_SHEET), 3, 3, 0.2f);
        icon.getSprite().setPosition(x, height);
        icon.getSprite().setScale(0.2f);

        x += inc;
        icon = createTopBarIcon();
        icon.init(Assets.getAsset(AssetDescriptors.ICON_HEROIN_SHEET), 3, 2, 0.2f);
        icon.getSprite().setPosition(x, height);
        icon.getSprite().setScale(0.2f);
    }

    private AnimatedSpriteDrawableObject createTopBarIcon() {
        AnimatedSpriteDrawableObject animatedSpriteDrawableObject = gameObjectManager.create(AnimatedSpriteDrawableObject.class);
        animatedSpriteDrawableObject.setUiObject(true);
        animatedSpriteDrawableObject.setDrawOrder(DRAW_ORDER_UI + 1);
        return  animatedSpriteDrawableObject;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
        pipeline.addUi(sprite, DRAW_ORDER_UI);
        float spriteWidth = sprite.getWidth() * sprite.getScaleX();
        float height = Gdx.graphics.getHeight() - (sprite.getHeight() * sprite.getScaleY() * 0.04f);
        float initalX = spriteWidth * 0.3475f;
        float inc = 194 * sprite.getScaleX();
        pipeline.addUi(Formatter.toScientificNumber(playerData.money), new Vector2(spriteWidth * 0.16f, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(Formatter.toScientificNumber(playerData.getDrug(DrugType.Weed)), new Vector2(initalX, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(Formatter.toScientificNumber(playerData.getDrug(DrugType.Pills)), new Vector2(initalX + inc * 1, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(Formatter.toScientificNumber(playerData.getDrug(DrugType.Coke)), new Vector2(initalX + inc * 2, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(Formatter.toScientificNumber(playerData.getDrug(DrugType.Oxy)), new Vector2(initalX + inc * 3, height), 3.5f,DRAW_ORDER_UI+1);
        pipeline.addUi(Formatter.toScientificNumber(playerData.getDrug(DrugType.Heroin)), new Vector2(initalX + inc * 4, height), 3.5f,DRAW_ORDER_UI+1);

    }

    @Override
    protected void onObjectDestroyed() {
        super.onObjectDestroyed();
        gameObjectManager.killAll();
    }
}
