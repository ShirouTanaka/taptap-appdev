package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Engkanto;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.drop.sprites.Timer;
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

import javax.swing.JDialog;

public class PlayState extends State{
    //initialize images here

    //    private Texture hero;
//    private Sprite heroSprite;
    private Hero jose;
    private Texture background;
    private Engkanto engkanto;

    private int engkantoHP;
    private int joseDMG;

    //initiating timer test here
    private Timer timer;

    //back to menu
    private Sprite backSprite;
    private Texture backButton;

    //camera
    OrthographicCamera camera;
    ExtendViewport viewport;

    //display
    private Label engkantoHealth;
    private BitmapFont font;

    public PlayState(GameStateManager gsm, Hero xjose) {
        super(gsm);

        this.jose = xjose;
        engkanto = new Engkanto(170, 400);
        background = new Texture("IMG-0365.png");

        //creating timer object
        timer = new Timer();

        //back to Menu
        backButton = new Texture("back.png");
        // BACK BUTTON SPRITE
        backSprite = new Sprite(backButton);
        backSprite.setPosition((float) ((TapCore.width/6) - (backButton.getWidth()+10)),(float) (TapCore.height)-(70));

        // - - > CAMERA
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate((TapCore.width/2),  TapCore.height/2);
        viewport = new ExtendViewport(TapCore.width, TapCore.height, camera);

        //we can add multipliers here based on stage number
        engkantoHP = engkanto.getBaseHealth();


        //same we can add but based on shop upgrades
        joseDMG = jose.getCurrentDamage();
        System.out.println(jose.getCurrentDamage());

        //display engkanto health
        font = new BitmapFont(Gdx.files.internal("barlow.fnt"),Gdx.files.internal("barlow.png"), false);
        engkantoHealth = new Label(Engkanto.getEngkantoHealth(engkantoHP), new Label.LabelStyle(font, Color.WHITE));
        engkantoHealth.setPosition((TapCore.width/2) - (100), ((TapCore.height/2)+350));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 tmpPlay = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(tmpPlay);

            Rectangle backBounds = new Rectangle(backSprite.getRegionX()-(215),backSprite.getRegionY()+(335),backSprite.getRegionWidth(), backSprite.getRegionHeight());

            jose.jump();
            engkanto.shake();
            engkantoHP = engkantoHP - joseDMG;
            System.out.println("Engkanto HP: "+ engkantoHP);
            engkantoHealth.setText(String.valueOf(Engkanto.getEngkantoHealth(engkantoHP)));

            if(backBounds.contains(tmpPlay.x, tmpPlay.y)){
            System.out.println("BACK BUTTON CLICKED");
            gsm.set(new MenuState(gsm));
            }
        }

        //the currentTime var is showing errors if forcing == 0
        //there is a split second delay but barely noticeable
        //this area runs and continuously monitors the state in deltaTime
        //this is the winning statement
        if (timer.currentTime > 0 && engkantoHP <= 0){
            System.out.println(engkantoHP);
            System.out.println("Chunkyboi is dead :( Resetting time now...");
            timer.resetTime();
        }

        if (timer.currentTime < 0 && engkantoHP > 0){
            System.out.println("You lost my gamer");
            gsm.set(new MenuState(gsm));
        }

        if(engkantoHP <= 0){
            System.out.println("chunkyBoi is dead :(");
            System.out.println("Making a new and stronger chunkyBoi");

            engkanto.setBaseHealth(engkanto.getBaseHealth()*2);
            engkantoHP = engkanto.getBaseHealth();

            System.out.println("new health is " + engkantoHP);
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        jose.update(deltaTime);
        engkanto.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
//        sb.draw(hero,(TapCore.width/2) - (hero.getWidth() / 2), (TapCore.height/2) - 200);
//        heroSprite.draw(sb);
        sb.draw(background, 0,0, TapCore.width, TapCore.height);
        sb.draw(engkanto.getEngkantoSprite(), engkanto.getPosition().x, engkanto.getPosition().y);
        sb.draw(jose.getHeroSprite(), jose.getPosition().x, jose.getPosition().y);
        //this is a test for the game's timer
        engkantoHealth.draw(sb, (float)(100));
        backSprite.draw(sb);
        timer.drawTime(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}