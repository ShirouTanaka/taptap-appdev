package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class StoreState extends State{
    private Texture storeBackground;
    private Texture storeTitle;
    private Texture upgrade1;
    private Texture upgrade2;
    private Texture upgrade3;
    private Texture chocnut;
    // TEXTURE & SPRITES
    private Sprite backSprite;
    private Texture backButton;
    private Sprite buySprite1;
    private Texture buyButton1;
    private Sprite buySprite2;
    private Texture buyButton2;
    private Sprite buySprite3;
    private Texture buyButton3;
    OrthographicCamera camera;
    ExtendViewport viewport;
    private Label moneyCount;
    private BitmapFont font;

    private Texture witchtxt;
    private Sprite witchsprite;
    // PREFS VARIABLE
    private Prefs prefs;

    protected StoreState(GameStateManager gsm) {
        super(gsm);

        // LOAD IMAGES
        storeBackground = new Texture("IMG-0365.png");
        storeTitle = new Texture("storeTitle.png");
        backButton = new Texture("back.png");
        buyButton1 = new Texture("buybutton.png");
        buyButton2 = new Texture("buybutton.png");
        buyButton3 = new Texture("buybutton.png");
        chocnut = new Texture("choc.png");

        // UPGRADES
        upgrade1 = new Texture("upgrade1.png");
        upgrade2 = new Texture("upgrade2.png");
        upgrade3 = new Texture("upgrade3.png");
        witchtxt = new Texture("dummy.png");

        // - - > CAMERA
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate((TapCore.width/2),  TapCore.height/2);
        viewport = new ExtendViewport(TapCore.width, TapCore.height, camera);

        // BACK BUTTON SPRITE
        backSprite = new Sprite(backButton);
        backSprite.setPosition((float) (cam.position.x - (backButton.getWidth() / 2)+30), (float) (cam.position.y+350));

        // BUY BUTTON 1 SPRITE
        buySprite1 = new Sprite(buyButton1);
        buySprite1.setPosition((float) (cam.position.x - (backButton.getWidth() / 2)+44), (float) (cam.position.y+270));

        // BUY BUTTON 2 SPRITE
        buySprite2 = new Sprite(buyButton2);
        buySprite2.setPosition((float) (cam.position.x - (backButton.getWidth() / 2)+44), (float) (cam.position.y+176));

        // BUY BUTTON 3 SPRITE
        buySprite3 = new Sprite(buyButton3);
        buySprite3.setPosition((float) (cam.position.x - (backButton.getWidth() / 2)+44), (float) (cam.position.y+82));

        // MONEY COUNT LABEL
        font = new BitmapFont(Gdx.files.internal("barlow.fnt"),Gdx.files.internal("barlow.png"), false);
        moneyCount = new Label(Hero.getHeroMoney(), new Label.LabelStyle(font, Color.WHITE));
        moneyCount.setFontScale(0.5f,0.5f);
        moneyCount.setPosition((float) (cam.position.x - (moneyCount.getWidth() / 2)+115), (float) (cam.position.y+15));

        // INITIALIZE PREFS
        prefs = new Prefs();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 tmpStore = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(tmpStore);

            Rectangle upgrade1Bounds = new Rectangle((cam.position.x-450), (cam.position.y+350),buySprite1.getRegionWidth()+240, buySprite1.getRegionHeight()+110);
            Rectangle upgrade2Bounds = new Rectangle((cam.position.x-450), (cam.position.y-100),buySprite2.getRegionWidth()+240, buySprite2.getRegionHeight()+110);
            Rectangle upgrade3Bounds = new Rectangle((cam.position.x-450), (cam.position.y-620),buySprite3.getRegionWidth()+240, buySprite3.getRegionHeight()+110);
            Rectangle backBounds = new Rectangle((cam.position.x-550), (cam.position.y+700),backSprite.getRegionWidth()+240, backSprite.getRegionHeight()+240);

            if(upgrade1Bounds.contains(tmpStore.x, tmpStore.y)) {
                System.out.println("UPGRADE 1 CLICKED");
                // SANTELMO'S CURSE
                if(Hero.getMoneyInt() >= 100){ // CAN PURCHASE
                    Hero.upgrade1(10);
                    Hero.setHeroMoney(Hero.getMoneyInt()-100);

                    moneyCount.setText(String.valueOf(Hero.getHeroMoney()));
                    prefs.decreaseMoney(100);
                    prefs.increaseDamage(10, "up1");
                }else{
                    System.out.println("MONEY IS INSUFFICIENT");
                }

            } if(upgrade2Bounds.contains(tmpStore.x, tmpStore.y)){
                System.out.println("UPGRADE 2 CLICKED");
                // MAKILING'S AID
                if(Hero.getMoneyInt() >= 1000){
                    Hero.upgrade2();
                    Hero.setHeroMoney(Hero.getMoneyInt()-1000);

                    moneyCount.setText(String.valueOf(Hero.getHeroMoney()));
                    prefs.decreaseMoney(1000);
                    prefs.increaseMoneyScaler(2.0);
                }else{
                    System.out.println("INSUFFICIENT MONEY2");
                }

            } if(upgrade3Bounds.contains(tmpStore.x,tmpStore.y)){
                System.out.println("UPGRADE 3 CLICKED");
                // MALAKAS' COURAGE
                if(Hero.getMoneyInt() >= 700){ // CAN PURCHASE
                    Hero.upgrade3();
                    Hero.setHeroMoney(Hero.getMoneyInt()-700);

                    moneyCount.setText(String.valueOf(Hero.getHeroMoney()));
                    prefs.decreaseMoney(700);
                    prefs.increaseDamage(20, "up3");
                }else{
                    System.out.println("INSUFFICIENT MONEY3");
                }

            } if(backBounds.contains(tmpStore.x, tmpStore.y)){
                System.out.println("BACK BUTTON CLICKED");
                gsm.set(new MenuState(gsm));
            }

        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        // draw textures and sprites
        sb.draw(storeBackground, 0,0, TapCore.width, TapCore.height);
        sb.draw(storeTitle, (float) (cam.position.x - (backButton.getWidth() / 2)+85), (float) (cam.position.y+345));
        sb.draw(chocnut, (float) (cam.position.x - (chocnut.getWidth() / 2)+55), (float) (cam.position.y+15));

        // UPGRADES RENDER
        sb.draw(upgrade1, (float) (cam.position.x - (backButton.getWidth() / 2)+50), (float) (cam.position.y+275));
        sb.draw(upgrade2, (float) (cam.position.x - (backButton.getWidth() / 2)+48), (float) (cam.position.y+180));
        sb.draw(upgrade3, (float) (cam.position.x - (backButton.getWidth() / 2)+50), (float) (cam.position.y+75));
        sb.draw(witchtxt, (float) (cam.position.x - (backButton.getWidth() / 2)+170), (float) (cam.position.y));
        moneyCount.draw(sb, (float)(100));

        backSprite.draw(sb); // DRAW BACK BUTTON
        buySprite1.draw(sb);
        buySprite2.draw(sb);
        buySprite3.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        storeBackground.dispose();
        storeTitle.dispose();
        backButton.dispose();
        upgrade1.dispose();
        upgrade2.dispose();
        upgrade3.dispose();
        buyButton1.dispose();
        buyButton2.dispose();
        buyButton3.dispose();
        chocnut.dispose();
    }

}
