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

        // - - > CAMERA
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate((TapCore.width/2),  TapCore.height/2);
        viewport = new ExtendViewport(TapCore.width, TapCore.height, camera);

        // BACK BUTTON SPRITE
        backSprite = new Sprite(backButton);
        backSprite.setPosition((float) ((TapCore.width/6) - (backButton.getWidth() / 2)),(float) (TapCore.height)-(135));

        // BUY BUTTON 1 SPRITE
        buySprite1 = new Sprite(buyButton1);
        buySprite1.setPosition((float) (TapCore.width/3.2) - (95), (float)((TapCore.height/2)-70));

        // BUY BUTTON 2 SPRITE
        buySprite2 = new Sprite(buyButton2);
        buySprite2.setPosition((float) (TapCore.width/3.2) + (153), (float)((TapCore.height/2)-70));

        // BUY BUTTON 3 SPRITE
        buySprite3 = new Sprite(buyButton3);
        buySprite3.setPosition((float) (TapCore.width/2) - (64), (float) ((TapCore.height/2)-375));

        // MONEY COUNT LABEL
        font = new BitmapFont(Gdx.files.internal("barlow.fnt"),Gdx.files.internal("barlow.png"), false);
        moneyCount = new Label(Hero.getHeroMoney(), new Label.LabelStyle(font, Color.WHITE));
        moneyCount.setPosition((TapCore.width/2) - (29), ((TapCore.height/2)+222));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 tmpStore = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(tmpStore);

            Rectangle upgrade1Bounds = new Rectangle(buySprite1.getRegionX()-(180),buySprite1.getRegionY()-(70),buySprite1.getRegionWidth(), buySprite1.getRegionHeight());
            Rectangle upgrade2Bounds = new Rectangle(buySprite2.getRegionX()+(60),buySprite2.getRegionY()-(70),buySprite2.getRegionWidth(), buySprite2.getRegionHeight());
            Rectangle upgrade3Bounds = new Rectangle(buySprite3.getRegionX()-(buySprite3.getRegionWidth()/2),buySprite3.getRegionY()-(375),buySprite3.getRegionWidth(), buySprite3.getRegionHeight());
            Rectangle backBounds = new Rectangle(backSprite.getRegionX()-(190),backSprite.getRegionY()+(280),backSprite.getRegionWidth(), backSprite.getRegionHeight());

            if(upgrade1Bounds.contains(tmpStore.x, tmpStore.y)) {
                System.out.println("UPGRADE 1 CLICKED");
                // SANTELMO'S CURSE
                if(Hero.getMoneyInt() >= 100){ // CAN PURCHASE
                    Hero.upgrade1(100);
                    Hero.setHeroMoney(Hero.getMoneyInt()-100);
                    moneyCount.setText(String.valueOf(Hero.getHeroMoney()));
                }else{
                    System.out.println("MONEY IS INSUFFICIENT");
                }

            } if(upgrade2Bounds.contains(tmpStore.x, tmpStore.y)){
                System.out.println("UPGRADE 2 CLICKED");
                // MAKILING'S AID
                if(Hero.getMoneyInt() >= 500){
                    Hero.upgrade2();
                    Hero.setHeroMoney(Hero.getMoneyInt()-500);
                    moneyCount.setText(String.valueOf(Hero.getHeroMoney()));
                }else{
                    System.out.println("INSUFFICIENT MONEY");
                }

            } if(upgrade3Bounds.contains(tmpStore.x,tmpStore.y)){
                System.out.println("UPGRADE 3 CLICKED");
                // MALAKAS' COURAGE
                if(Hero.getMoneyInt() >= 1000){ // CAN PURCHASE
                    Hero.upgrade3();
                    Hero.setHeroMoney(Hero.getMoneyInt()-1000);
                    moneyCount.setText(String.valueOf(Hero.getHeroMoney()));
                }else{
                    System.out.println("INSUFFICIENT MONEY");
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
        sb.draw(storeTitle, (TapCore.width/2) - (storeTitle.getWidth() / 2), ((TapCore.height/2)+260));
        sb.draw(chocnut, (TapCore.width/2) - (72), ((TapCore.height/2)+220));

        // UPGRADES RENDER
        sb.draw(upgrade1, (TapCore.width/4) - (upgrade1.getWidth() / 2), ((TapCore.height/2)-20));
        sb.draw(upgrade2, (TapCore.width/2) + (upgrade2.getWidth() / 3), ((TapCore.height/2)-20));
        sb.draw(upgrade3, (TapCore.width/2) - (upgrade3.getWidth() / 2), ((TapCore.height/2)-320));
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
