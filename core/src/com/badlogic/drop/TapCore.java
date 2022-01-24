package com.badlogic.drop;

import java.util.Iterator;

import com.badlogic.drop.states.GameStateManager;
import com.badlogic.drop.states.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

//the TapCore is the... well core... everything that is logic is here. Core... yea. U get it (- _ -   )
public class TapCore extends ApplicationAdapter {

	// FOR THE WEAK AND THE BROKEEEEN - Degs commit test ghub 2022

	//these are just basic params for the game
	//remember all tests should be on the desktop first before the android device
	public static final int width = 480;
	public static final int height = 800;

	public static final String title = "Tap-A-Pon";

	private GameStateManager gsm;
	private SpriteBatch batch; //these are heavy files so just pass it around each game state


	//create is where we load the assets that we need but that's it. It just loads
	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1,0,0,1);
		gsm.push(new MenuState(gsm));//the menu starts first cuz... well its the menu screen
	}

	//render is a loop that goes on and this is where the loaded assets get drawn
	//reminder that all images should be on the assets folder; android > assets > paste (png pls)
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());//get the difference between render times first
		gsm.render(batch);//now we draw it


	}

	@Override
	public void dispose() {
		// dispose of all the native resources
	}

	//all functions for the game is here

}