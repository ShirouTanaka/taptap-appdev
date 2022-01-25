package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StoreState extends State{
    private Texture storeBackground;
    private Texture storeTitle;

    protected StoreState(GameStateManager gsm) {
        super(gsm);

        // LOAD IMAGES
        storeBackground = new Texture("IMG-0365.png");
        storeTitle = new Texture("storeTitle.png");

        // for test purposes
        Hero.strengthen(50); // this works so far
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        // draw textures and sprites
        sb.draw(storeBackground, 0,0, TapCore.width, TapCore.height);
        sb.draw(storeTitle, (TapCore.width/2) - (storeTitle.getWidth() / 2), ((TapCore.height/2)+220));
        sb.end();
    }

    @Override
    public void dispose() {
        storeBackground.dispose();
        storeTitle.dispose();
    }
}
