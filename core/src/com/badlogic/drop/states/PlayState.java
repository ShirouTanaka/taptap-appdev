package com.badlogic.drop.states;

import com.badlogic.drop.TapCore;
import com.badlogic.drop.sprites.Engkanto;
import com.badlogic.drop.sprites.Hero;
import com.badlogic.drop.sprites.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class PlayState extends State {
    //initialize images here

//    private Sprite heroSprite;
    private Hero jose;
    private Texture background;
    private Engkanto engkanto;

    private int engkantoHP;
    private int joseDMG;
    private int baseMoney = 10;

    private Timer timer;

    private Sprite backSprite;
    private Texture backButton;

    //camera
    OrthographicCamera camera;
    ExtendViewport viewport;

    //display
    private Label engkantoHealth;
    private Label heroAttack;
    private BitmapFont font;

    private Sound atkSound = Gdx.audio.newSound(Gdx.files.internal("attack.mp3"));
    private Prefs prefs;
    Texture blank;
    SpriteBatch sblank;
    double hp = 900.0;
    double totalhp = 900.0;
    int total = 900;
    public PlayState(GameStateManager gsm, Hero xjose) {
        super(gsm);

        this.jose = xjose;

        cam.setToOrtho(false, TapCore.width/2, TapCore.height/2);

        engkanto = new Engkanto(0, 0);

        engkanto.setPosition((TapCore.width/2)-(engkanto.getWidth()/2),330);

        background = new Texture("bg.png");

        //creating timer object
        timer = new Timer();

        //back to Menu
        backButton = new Texture("back.png");
        // BACK BUTTON SPRITE
        backSprite = new Sprite(backButton);
        backSprite.setPosition((float) (cam.position.x-110), (float) (cam.position.y+160));

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

        engkantoHealth.setFontScale((float) .70);
        engkantoHealth.setPosition(((cam.position.x/2)-(cam.position.x/24)+10), ((cam.position.y/2)+260));

        //display Hero Attack
        heroAttack = new Label(Hero.getAttack(joseDMG), new Label.LabelStyle(font, Color.WHITE));

        heroAttack.setFontScale((float) .50);
        heroAttack.setPosition(((cam.position.x/2)-(cam.position.x/24)+10), ((cam.position.y/2)-50));

        blank = new Texture("blank.png");
        sblank = new SpriteBatch();
        // INITIALIZE PREFS
        prefs = new Prefs();
    }

    @Override
    protected void handleInput() {

//        engkantoHealth.setText(String.valueOf(Engkanto.getEngkantoHealth(engkantoHP)));

        if(Gdx.input.isTouched()){
            Vector3 tmpPlay = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);

            Rectangle backBounds = new Rectangle((cam.position.x-60), (cam.position.y-100),backSprite.getRegionWidth()+100, backSprite.getRegionHeight()+100);

            jose.jump();
            //sound test
            atkSound.play();

            engkantoHP = engkantoHP - joseDMG;
            hp = engkantoHP;
            System.out.println("Engkanto HP: "+ hp);
            System.out.println("Engkanto totalHP: "+ totalhp);
            engkantoHealth.setText(String.valueOf(Engkanto.getEngkantoHealth(engkantoHP)));

            double tmp;
            tmp =  hp/totalhp*900.0;
            System.out.println(tmp);
            total = (int) tmp;
            System.out.println(total);
            System.out.println("play pos:" + engkanto.getPosition().x);

            if(backBounds.contains(tmpPlay.x, tmpPlay.y)){
                System.out.println("BACK BUTTON CLICKED");
                atkSound.stop();
                gsm.set(new MenuState(gsm));
                engkanto.setBaseHealth(900); // RESET FOR EASIER GRIND
                Hero.resetMoneyScaler();
            }
        }

        //the currentTime var is showing errors if forcing == 0
        //there is a split second delay but barely noticeable
        //this area runs and continuously monitors the state in deltaTime
        //this is the winning statement
        if (timer.currentTime > 0 && engkantoHP <= 0){
            System.out.println(engkantoHP);
            System.out.println("Chunkyboi is dead :( Resetting time now...");
            engkanto.changeSkin();
            timer.resetTime();

        }

        if (timer.currentTime < 0 && engkantoHP > 0){
            System.out.println("You lost my gamer");
            engkanto.setBaseHealth(900);
            Hero.resetMoneyScaler();
            totalhp = 900;
            hp = 900;
            gsm.set(new LoseState(gsm));
        }

        if(engkantoHP <= 0){
            System.out.println("chunkyBoi is dead :(");
            System.out.println("Making a new and stronger chunkyBoi");

            engkanto.setBaseHealth(engkanto.getBaseHealth()*2);
            engkantoHP = engkanto.getBaseHealth();
            totalhp = engkantoHP;
            hp = engkantoHP;
            System.out.println("new health is " + engkantoHP);

            // GIVE MONEY TO JOSE UPON DEFEATING ENEMY
            jose.addMoney((int)(baseMoney * Hero.getMoneyScale()));
            Hero.increaseMoneyScaler();

            // SAVE MONEY
            prefs.increaseMoney((int)(baseMoney * Hero.getMoneyScale()));
        }

    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        jose.update(deltaTime);
        engkanto.update(deltaTime);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        // DRAW
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0,0, TapCore.width, TapCore.height);

        boolean attacking = false;
        if(Gdx.input.justTouched()){
            sb.draw(engkanto.getEngkantoSprite(), (cam.position.x - (engkanto.getWidth()/2)+10), cam.position.y-40);
            attacking = true;
        }else{
            sb.draw(engkanto.getEngkantoSprite(), cam.position.x - (engkanto.getWidth()/2), cam.position.y-40);
//            sb.draw(jose.getHeroSprite(), (cam.position.x - (jose.getWidth()/2)), cam.position.y-120);
            attacking = false;
        }

        heroAttack.draw(sb, (float)(100));

        if(attacking){
            sb.draw(engkanto.getTexture(), cam.position.x-40, cam.position.y-20);
            sb.draw(jose.getTexture(), jose.getPosition().x -10,jose.getPosition().y+11);
        }else{
            sb.draw(jose.getHeroSprite(), jose.getPosition().x,jose.getPosition().y);
        }

        backSprite.draw(sb);
        timer.drawTime(sb);
        sb.end();

        //System.out.println("tmp: "+tmp+"total: "+total);
        sblank.begin();

        System.out.println(total);
        if (total <= 900 && total > 500)
            sblank.setColor(Color.GREEN);
        else if (total <= 500 && total > 300)
            sblank.setColor(Color.ORANGE);
        else
            sblank.setColor(Color.RED);
        sblank.draw(blank,cam.position.x-50, cam.position.y+100,total,50);
        sblank.end();

        //System.out.println("hp: " + hp + " totalhp: "+ totalhp + " width: "+ total) ;
    }


    @Override
    public void dispose() {

    }
}