package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State{
    //initialize images here

    private Texture hero;
    private Sprite heroSprite;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        //load images here
        hero = new Texture("hero.png");
        heroSprite = new Sprite(hero);
        heroSprite.setSize(150,150);
        heroSprite.setPosition((float) (TapCore.width/2) - (heroSprite.getWidth()/2), (float) ((TapCore.height/2) - 400));
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
//        sb.draw(hero,(TapCore.width/2) - (hero.getWidth() / 2), (TapCore.height/2) - 200);
        heroSprite.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
