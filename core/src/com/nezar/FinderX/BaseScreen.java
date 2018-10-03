package com.Nezar.FinderX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BaseScreen implements Screen{
	Main game;
	Stage stage;
	InputMultiplexer inputMultiplexer;
	boolean loaded;
	
	BaseScreen(Main game, Scr num){
		this.game = game;
		loaded = false;
		game.screenLoaded[num.ordinal()] = true;
		stage = new Stage(game.viewport);
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	public void refreshIcon(){
		
	}
	
	BaseScreen(){
		
	}

	@Override
	public void show() {
		loaded = true;
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		game.gs.save();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
