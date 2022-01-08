package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{
    //initialize images here
    private Texture background;
    private Texture playBtn;
    private Texture title;
    private Sprite playSprite;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        //load images here
        background = new Texture("IMG-0365.png");
        playBtn = new Texture("playBtn.png");
        title = new Texture("tap_title.png");

        playSprite = new Sprite(playBtn);
        playSprite.setPosition((float) ((TapCore.width/2) - (playBtn.getWidth() / 2)),(float) (TapCore.height/2));
    }

    @Override
    public void handleInput() {
        //implement how to check a specific sprite or texture is touched
        if (Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
            System.out.println("moving to playState");
            dispose();
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
