package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuState extends State{
    //initialize images here
    private Texture background;
    private Texture playBtn;
    private TextureRegion playBtnRegion;
    private Texture title;
    private Sprite playSprite;
    OrthographicCamera camera;
    ExtendViewport viewport;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        //load images here
        background = new Texture("IMG-0365.png");
        playBtn = new Texture("playBtn.png");
        title = new Texture("tap_title.png");
        playBtnRegion = new TextureRegion(playBtn);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate((TapCore.width/2) - (playBtn.getWidth() / 2),  TapCore.height/2);
        viewport = new ExtendViewport(TapCore.width, TapCore.height, camera);
        playSprite = new Sprite(playBtn);
        playSprite.setPosition((float) ((TapCore.width/2) - (playBtn.getWidth() / 2)),(float) (TapCore.height/2));
    }

    @Override
    public void handleInput() {

            if (Gdx.input.isTouched())
            {
                Vector3 tmp = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                camera.unproject(tmp);

                Rectangle textureBounds=new Rectangle(playBtnRegion.getRegionX(),playBtnRegion.getRegionY(),playBtnRegion.getRegionWidth(), playBtnRegion.getRegionHeight());
                // texture x is the x position of the texture
                // texture y is the y position of the texture
                // texturewidth is the width of the texture (you can get it with texture.getWidth() or textureRegion.getRegionWidth() if you have a texture region
                // textureheight is the height of the texture (you can get it with texture.getHeight() or textureRegion.getRegionhHeight() if you have a texture region
                if(textureBounds.contains(tmp.x,tmp.y))
                {
                    gsm.set(new PlayState(gsm));
                    System.out.println("button click");
                    System.out.println("moving to playState");
                    dispose();
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
        //start drawing the things
        sb.draw(background, 0,0, TapCore.width, TapCore.height);
        sb.draw(title, (TapCore.width/2) - (title.getWidth() / 2), ((TapCore.height/2)+50));
        //sb.draw(playBtn, (TapCore.width/2) - (playBtn.getWidth() / 2), (TapCore.height/2));
        playSprite.draw(sb);
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        title.dispose();
    }
}