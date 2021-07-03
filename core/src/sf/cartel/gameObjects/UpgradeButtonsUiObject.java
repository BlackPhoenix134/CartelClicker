package sf.cartel.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

import sf.cartel.assets.AssetDescriptors;
import sf.cartel.assets.Assets;
import sf.cartel.core.Extensions.Sprites;
import sf.cartel.core.PlayerData;
import sf.cartel.rendering.RenderPipeline;

public class UpgradeButtonsUiObject extends GameObject {
    private PlayerData playerData;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private int drawOrder;
    private ClickableSpriteDrawableObject[][] spriteDrawableObjects = new ClickableSpriteDrawableObject[5][3];

    UpgradeButtonsUiObject(String uuid) {
        super(uuid);
    }

    public void init(PlayerData playerData, Sprite backgroundSprite, int drawOrder) {
        this.playerData = playerData;
        this.drawOrder = drawOrder;

        ClickableSpriteDrawableObject btn = createUpgradeButton(0, 0);
        Sprite sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);
        btn = createUpgradeButton(1, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton(2, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton(3, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton(4, 0);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.32f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);

        btn = createUpgradeButton(0, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);
        btn = createUpgradeButton(1, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton(2, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton(3, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite) * 0.65f);
        btn = createUpgradeButton(4, 1);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.58f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);

        btn = createUpgradeButton(0, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.21f);
        btn = createUpgradeButton(1, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite) * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.34f);
        btn = createUpgradeButton(2, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.50f);
        btn = createUpgradeButton(3, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.65f);
        btn = createUpgradeButton(4, 2);
        sprite = btn.getSprite();
        sprite.setPosition(Sprites.getScaledWidth(backgroundSprite)  * 0.86f, Sprites.getScaledHeight(backgroundSprite)  * 0.80f);
    }

    private ClickableSpriteDrawableObject createUpgradeButton(int x, int y) {
        ClickableSpriteDrawableObject upgradeBtn = gameObjectManager.create(ClickableSpriteDrawableObject.class);
        spriteDrawableObjects[x][y] = upgradeBtn;
        upgradeBtn.setCenterTextSize(4);
        upgradeBtn.setDrawOrder(drawOrder + 1);
        upgradeBtn.setUiObject(true);
        Sprite sprite = new Sprite(Assets.getAsset(AssetDescriptors.SQUARE_40x40));
        sprite.setScale(2);
        upgradeBtn.setSprite(sprite);
        return upgradeBtn;
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
            //0
            //1
        spriteDrawableObjects[0][2].setCenterText("10");
        spriteDrawableObjects[1][2].setCenterText("10");
        spriteDrawableObjects[2][2].setCenterText("10");
        spriteDrawableObjects[3][2].setCenterText("10");
        spriteDrawableObjects[4][2].setCenterText("10");
    }

    @Override
    protected void onObjectDestroyed() {
        super.onObjectDestroyed();
        gameObjectManager.killAll();
    }
}
