package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Aswang;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State{
    //initialize images here

    //    private Texture hero;
//    private Sprite heroSprite;
    private Hero jose;
    private Texture background;
    private Aswang chunkyBoi;

    private int chunkyBoiHP;
    private int joseDMG;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        jose = new Hero( 170,80);
        chunkyBoi = new Aswang(170, 400);
        background = new Texture("IMG-0365.png");

        //we can add multipliers here based on stage number
        chunkyBoiHP = chunkyBoi.getBaseHealth();

        //same we can add but based on shop upgrades
        joseDMG = jose.getBaseDamage();
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched()){
            jose.jump();
            chunkyBoi.shake();
            chunkyBoiHP = chunkyBoiHP - joseDMG;
            System.out.println("chunkyBoiHP: "+ chunkyBoiHP);

            if(chunkyBoiHP <= 0){
                System.out.println("chunkyBoi is dead :(");
                System.out.println("Making a new and stronger chunkyBoi");

                chunkyBoi.setBaseHealth(chunkyBoi.getBaseHealth()*2);
                chunkyBoiHP = chunkyBoi.getBaseHealth();

                System.out.println("new health is " + chunkyBoiHP);
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        jose.update(deltaTime);
        chunkyBoi.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
//        sb.draw(hero,(TapCore.width/2) - (hero.getWidth() / 2), (TapCore.height/2) - 200);
//        heroSprite.draw(sb);
        sb.draw(background, 0,0, TapCore.width, TapCore.height);
        sb.draw(chunkyBoi.getAswangSprite(), chunkyBoi.getPosition().x, chunkyBoi.getPosition().y);
        sb.draw(jose.getHeroSprite(), jose.getPosition().x, jose.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}