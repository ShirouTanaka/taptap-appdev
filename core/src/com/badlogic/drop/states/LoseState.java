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
    private Texture title;
    private Texture ded;
    private Sprite dedSprite;
    private Hero josePass;
    OrthographicCamera camera;
    ExtendViewport viewport;

    private Label clickAnywhere;
    private BitmapFont font;

    public LoseState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("bg.png");
        title = new Texture("youLost.png");
        ded = new Texture("ded_2.png");

        // - - > CAMERA
        cam.setToOrtho(false, TapCore.width/2, TapCore.height/2);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate((TapCore.width/2),  TapCore.height/2);
        viewport = new ExtendViewport(TapCore.width, TapCore.height, camera);

        // - - > DED SPRITE
        dedSprite = new Sprite(ded);

        //for Click Anywhere
        font = new BitmapFont(Gdx.files.internal("barlow.fnt"),Gdx.files.internal("barlow.png"), false);
        clickAnywhere = new Label("Click Anywhere to Continue", new Label.LabelStyle(font, Color.WHITE));

        clickAnywhere.setFontScale((float) .55);
        clickAnywhere.setPosition(((cam.position.x/2)-(cam.position.x/5)), ((cam.position.y/2)+100));

    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            gsm.set(new MenuState(gsm));
            System.out.println("button click");
            System.out.println("moving to MenuState");

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
        sb.draw(title, cam.position.x - (title.getWidth()/2), cam.position.y+50);

        clickAnywhere.draw(sb, (float)(100));
        sb.draw(dedSprite, cam.position.x - (dedSprite.getWidth()/2), cam.position.y-120);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        title.dispose();

    }
}
