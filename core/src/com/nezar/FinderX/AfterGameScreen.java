package com.Nezar.FinderX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class AfterGameScreen extends BaseScreen{
	
	class MyInputListener implements InputProcessor{

		@Override
		public boolean keyDown(int keycode) {
			if(keycode == Keys.BACK){
				game.setScreen(game.menuScreen);
				game.gs.save();
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
		
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			
			return false;
		}

		@Override
		public boolean touchDown(int ScreenX, int screenY, int pointer, int button) {
		
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {

			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {

			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			
			return false;
		}
		
	}
	
	Logo logo;
	
	FreeTypeFontGenerator gen;
	FreeTypeFontParameter param;
	BitmapFont font, font2;
	Sprite bg;
	int levelsComplete;
	int chapter;
	
	
	public AfterGameScreen(Main game, Scr num){
		super(game, num);
		bg = game.im.Get("GameBg");
		logo = new Logo(game);
		
		param = new FreeTypeFontParameter();
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(275));
		font = game.gen3.generateFont(param);
		font.setColor(Color.valueOf("501C40"));
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(200));
		font2 = game.gen3.generateFont(param);
		font2.setColor(Color.valueOf("501C40"));
		
		stage.addActor(new MenuButton(game));
		stage.addActor(new ReplayButton(game));
		
		inputMultiplexer.addProcessor(new MyInputListener());
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void show() {
		game.gs.save();
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.156f, 0.78f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		bg.draw(game.batch);
		logo.draw(game.batch);
		font.draw(game.batch, "Results:", game.getX(350), game.getY(1375));
		
		if(game.gs.currentMode != 1)
			font2.draw(game.batch, "Score:         " + Integer.toString(game.gs.tmpScore), game.getX(100), game.getY(1025));
		else
			font2.draw(game.batch, "Score:          " + Integer.toString((int)(game.gs.currentPointsInFirstArcadeMode/60)) + ":" + Integer.toString(game.gs.currentPointsInFirstArcadeMode - (int)(game.gs.currentPointsInFirstArcadeMode/60)*60), game.getX(100), game.getY(1025));
			
		if(!game.secret)
		switch(game.gs.currentMode){
		case 1:font2.draw(game.batch, "Best:         "+Integer.toString((int)(game.gs.bestFirstModePoints/60)) + ":" + Integer.toString(game.gs.bestFirstModePoints - (int)(game.gs.bestFirstModePoints/60)*60), game.getX(100), game.getY(825));
		break;
		case 2:
			font2.draw(game.batch, "Best:          "+Integer.toString(game.gs.bestSecondModePoints), game.getX(100), game.getY(825));
			break;
		case 3:
			font2.draw(game.batch, "Best:          "+Integer.toString(game.gs.bestThirdModePoints), game.getX(100), game.getY(825));
		}else
			font2.draw(game.batch, "Best  -  Nastya :)", game.getX(100), game.getY(825));
		
		game.batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		game.gs.tmpScore = 0;
	}

	@Override
	public void dispose() {
		font.dispose();
		font2.dispose();
	}

}