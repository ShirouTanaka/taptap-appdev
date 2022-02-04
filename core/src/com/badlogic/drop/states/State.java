package com.badlogic.drop.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 pointer;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        pointer = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime); //delta time is the difference between 1 frame rendered and another
    public abstract void render(SpriteBatch sb); //renders instantaneously
    public abstract void dispose();
}