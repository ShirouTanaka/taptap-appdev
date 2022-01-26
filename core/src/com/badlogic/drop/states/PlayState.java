package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Engkanto;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.drop.sprites.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State{
    //initialize images here

    //    private Texture hero;
//    private Sprite heroSprite;
    private Hero jose;
    private Texture background;
    private Engkanto chunkyBoi;

    private int chunkyBoiHP;
    private int joseDMG;

    //initiating timer test here
    private Timer timer;

    public PlayState(GameStateManager gsm, Hero xjose) {
        super(gsm);

        this.jose = xjose;
        chunkyBoi = new Engkanto(170, 400);
        background = new Texture("IMG-0365.png");

        //creating timer object
        timer = new Timer();

        //we can add multipliers here based on stage number
        chunkyBoiHP = chunkyBoi.getBaseHealth();

        //same we can add but based on shop upgrades
        joseDMG = jose.getCurrentDamage();
        System.out.println(jose.getCurrentDamage());
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            jose.jump();
            chunkyBoi.shake();
            chunkyBoiHP = chunkyBoiHP - joseDMG;
            System.out.println("chunkyBoiHP: "+ chunkyBoiHP);
        }

        //the currentTime var is showing errors if forcing == 0
        //there is a split second delay but barely noticeable
        //this area runs and continuously monitors the state in deltaTime
        //this is the winning statement
        if (timer.currentTime > 0 && chunkyBoiHP <= 0){
            System.out.println(chunkyBoiHP);
            System.out.println("Chunkyboi is dead :( Resetting time now...");
            timer.resetTime();
        }

        if (timer.currentTime < 0 && chunkyBoiHP > 0){
            System.out.println("You lost my gamer");
            gsm.set(new MenuState(gsm));

        }

        if(chunkyBoiHP <= 0){
            System.out.println("chunkyBoi is dead :(");
            System.out.println("Making a new and stronger chunkyBoi");

            chunkyBoi.setBaseHealth(chunkyBoi.getBaseHealth()*2);
            chunkyBoiHP = chunkyBoi.getBaseHealth();

            System.out.println("new health is " + chunkyBoiHP);
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
        sb.draw(chunkyBoi.getEngkantoSprite(), chunkyBoi.getPosition().x, chunkyBoi.getPosition().y);
        sb.draw(jose.getHeroSprite(), jose.getPosition().x, jose.getPosition().y);
        //this is a test for the game's timer
        timer.drawTime(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}