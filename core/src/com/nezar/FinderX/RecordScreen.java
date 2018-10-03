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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;



public class RecordScreen extends BaseScreen{
	
	class MyInputListener implements InputProcessor{

		@Override
		public boolean keyDown(int keycode) {
			if(keycode == Keys.BACK){
				game.setScreen(game.menuScreen);
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
			game.setScreen(game.menuScreen);	
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
	

	FreeTypeFontGenerator gen;
	FreeTypeFontParameter param;
	BitmapFont font, font2;
	Sprite bg;
	Sprite icons;
	Sprite brain;
	int levelsComplete;
	int chapter;
	
	public RecordScreen(Main game, Scr num){
		super(game, num);
		bg = game.im.Get("GameBg");
		icons = game.im.Get("RecordIcons");
		
		icons.setPosition(game.getX(200), game.h/2 - icons.getHeight()/2);
		
		brain = game.im.Get("Brain");
		brain.setPosition(game.getX(50), game.getY(2210));
		inputMultiplexer.addProcessor(new MyInputListener());
		
		param = new FreeTypeFontParameter();
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(350));
		font = game.gen.generateFont(param);
		font.setColor(Color.valueOf("5A2048"));
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(300));
		font2 = game.gen.generateFont(param);
		font2.setColor(Color.WHITE);
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		game.menuWasOpen = false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.156f, 0.78f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		bg.draw(game.batch);
		game.batch.end();
		game.sr.begin(ShapeType.Filled);
		game.sr.setColor(245.0f/255.0f, 105.0f/255.0f, 44.0f/255.0f, 1);
		game.sr.rect(0, game.h - game.getY(400), game.w, game.getY(400));
		game.sr.end();
		game.batch.begin();
		brain.draw(game.batch);
		icons.draw(game.batch);
		font2.draw(game.batch, "YOUR RESULTS", game.getX(490), game.getY(2450));
		font.draw(game.batch, Integer.toString(game.gs.bestNumbers), game.getX(800), game.getY(1900));
		if (game.gs.bestFirstModePoints > 0)font.draw(game.batch, Integer.toString((int)(game.gs.bestFirstModePoints/60)) + ":" + Integer.toString(game.gs.bestFirstModePoints - (int)(game.gs.bestFirstModePoints/60)*60), game.getX(800), game.getY(1550));
		else font.draw(game.batch, "none", game.getX(800), game.getY(1550));
		font.draw(game.batch, Integer.toString(game.gs.bestSecondModePoints), game.getX(800), game.getY(1200));
		font.draw(game.batch, Integer.toString(game.gs.bestThirdModePoints), game.getX(800), game.getY(825));
		game.batch.end();
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
		
	}

	@Override
	public void dispose() {
		font.dispose();
	}

}