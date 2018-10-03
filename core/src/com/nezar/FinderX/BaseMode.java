package com.Nezar.FinderX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BaseMode extends BaseScreen{
	
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
	
	class Replay extends Actor{
		class Click extends ClickListener {
			@Override
			public void clicked(InputEvent event, float x, float y){
				win = true;
			}
		}

		Sprite sprite;
		
		public Replay(){
			sprite = game.im.Get("Button1");
			
			setX(game.getX(300));
			setY(game.getY(2330));
			
			sprite.setPosition(getX(), getY());
			
			setWidth(sprite.getWidth());
			setHeight(sprite.getHeight());
			
			addListener(new Click());
		}
		

		@Override
		public void draw(Batch batch, float alpha){
			sprite.draw(batch);
		}
			
	}

	Sprite bg;
	Sprite alarm;
	FreeTypeFontParameter param;
	BitmapFont font;
	
	Grid grid;
	
	private int tmp;
	int numNumbers;
	boolean win;
	
	public BaseMode(Main game, Scr num){
		super(game, num);
		
		param = new FreeTypeFontParameter();
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(200));
		font = game.gen.generateFont(param);
		font.setColor(Color.valueOf("5A2048"));
		
		bg = game.im.Get("GameBg");
		alarm = game.im.Get("Alarm");
		alarm.setPosition(game.getX(1004), game.getY(2313));
		
		stage.addActor(new BackButton(game, game.menuScreen));
		stage.addActor(new Replay());
		
		inputMultiplexer.addProcessor(new MyInputListener());
		
	}
	
	@Override
	public void show() {
		super.show();
		game.gs.currentNumberInArcade = 1;
		game.menuWasOpen = false;
	}
	
	public void update(){
		
	}
	
	@Override
	public void render(float delta) {
		
		if(game.rollback > 0){
			if(!grid.checkSqure(game.rollback)){
				if(game.gs.currentNumberInArcade > 2){
					game.gs.currentNumberInArcade-=2;
					if(game.gs.currentMode == 2)
					game.gs.tmpScore-=2;
				}
				else{
					game.gs.currentNumberInArcade = 1;
					if(game.gs.currentMode == 2)
					if(game.gs.tmpScore > 0)
						game.gs.tmpScore--;
					}
				grid.reActiveArcade();
				game.rollback = 0;
			}
		}
		
		tmp = game.gs.currentNumberInArcade;
		while(tmp != 0){
			tmp = tmp/10;
			numNumbers++;
		}
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		bg.draw(game.batch);
		game.batch.end();
		game.sr.begin(ShapeType.Filled);
		game.sr.setColor(245.0f/255.0f, 105.0f/255.0f, 44.0f/255.0f, 1);
		game.sr.rect(0, game.h - game.getY(278), game.w, game.getY(278));
		grid.draw();
		game.sr.end();
		game.batch.begin();
		font.draw(game.batch, Integer.toString(game.gs.currentNumberInArcade), game.w/2 -game.w/30*(numNumbers-1), game.h - game.getY(95));
		update();
		alarm.draw(game.batch);
		game.batch.end();	
		stage.act();
		stage.draw();
		
	}
	@Override
	public void resize(int width, int height) {	
		
	}
	@Override
	public void pause() {
		super.pause();
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		font.dispose();
		grid.dispose();
		
	}

}
