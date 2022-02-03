package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Engkanto;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.drop.sprites.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class LoseState extends State{
    private Texture background;
    private Texture playBtn;
//    private Texture title;
    private Texture ded;
    private Sprite dedSprite;
    private Sprite playSprite;
    private Texture storeButton;
    private Sprite storeSprite;
    private Hero joseMain;
    OrthographicCamera camera;
    ExtendViewport viewport;

    public LoseState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
//        title = new Texture("title.png");
        storeButton = new Texture("storeBtn.png");
        ded = new Texture("ded_2.png");

        // - - > CAMERA
        cam.setToOrtho(false, TapCore.width/2, TapCore.height/2);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate((TapCore.width/2),  TapCore.height/2);
        viewport = new ExtendViewport(TapCore.width, TapCore.height, camera);

        // - - > PLAY SPRITE
        playSprite = new Sprite(playBtn);
        playSprite.setPosition((float) (cam.position.x - (playBtn.getWidth() / 2)), (float) (cam.position.y-20));;
        // - - > STORE SPRITE
        storeSprite = new Sprite(storeButton);
        storeSprite.setPosition((float) (cam.position.x - (playBtn.getWidth() / 2)), (float) (cam.position.y-80));
        // - - > DED SPRITE
        dedSprite = new Sprite(ded);
        joseMain = new Hero( 170,80);
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            Vector3 tmp = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(tmp);

            Rectangle playBounds=new Rectangle(playSprite.getRegionX()-(playSprite.getRegionWidth()/2),playSprite.getRegionY(),playSprite.getRegionWidth(), playSprite.getRegionHeight());
            Rectangle storeBounds=new Rectangle(storeSprite.getRegionX()-(storeSprite.getRegionWidth()/2),storeSprite.getRegionY()-(storeSprite.getRegionHeight()),storeSprite.getRegionWidth(), storeSprite.getRegionHeight());

            // texture x is the x position of the texture
            // texture y is the y position of the texture
            // texturewidth is the width of the texture (you can get it with texture.getWidth() or textureRegion.getRegionWidth() if you have a texture region
            // textureheight is the height of the texture (you can get it with texture.getHeight() or textureRegion.getRegionhHeight() if you have a texture region
            if(playBounds.contains(tmp.x,tmp.y)) {
                gsm.set(new PlayState(gsm,joseMain));
                System.out.println("button click");
                System.out.println("moving to playState");
            }
            if(storeBounds.contains(tmp.x,tmp.y)){
                gsm.set(new StoreState(gsm));
                System.out.println("BUTTON CLICK STORE");
            }
        }

    }

    @Override
    public void update(float deltaTime) {
        //always check if this state has anything going on
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //load the things needed to be rendered with .begin
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        //start drawing the things

        sb.draw(background, 0,0, TapCore.width, TapCore.height);
//      sb.draw(title, cam.position.x - (title.getWidth()/2), cam.position.y+40);

        //sb.draw(playBtn, (TapCore.width/2) - (playBtn.getWidth() / 2), (TapCore.height/2));
        playSprite.draw(sb);
        storeSprite.draw(sb);
        sb.draw(dedSprite, cam.position.x - (dedSprite.getWidth()/2), cam.position.y-120);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
//        title.dispose();
        storeButton.dispose();
    }
}
