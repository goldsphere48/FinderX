package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SecondArcadeMode extends BaseMode {
	
	
	Sprite vstavka;
	BitmapFont font2;
	
	MyTimer timer;

	
	public SecondArcadeMode(Main game, Scr num){
		super(game, num);
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(110));
		font2 = game.gen2.generateFont(param);
		font2.setColor(Color.WHITE);
		
		vstavka = game.im.Get("Vstavka");
		vstavka.setPosition( game.w/2 - vstavka.getWidth()/2, game.h - game.getY(278) - vstavka.getHeight());
		
		timer = new MyTimer(60, game, true);
		stage.addActor(timer);
		grid = new Grid(game, stage, 3, 3, false);
	}
	
	
	@Override
	public void show() {
		super.show();
		game.gs.currentMode = 2;
		timer.start();
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(timer.isDone()){
			game.gs.currentNumberInArcade = 1;
			game.gs.save();
			timer.stop();
			game.setScreen(game.afterGameScreen);
		}
		
		if(grid.isWin()){
			grid.reGenerate(3,  3);
			game.gs.currentNumberInArcade = 1;
		}
		if(win){
			timer.start();
			grid.reGenerate(3, 3);
			win = false;
			game.gs.currentNumberInArcade = 1;
			game.gs.tmpScore = 0;
		}
		
		numNumbers = 0;
		
	}
	
	@Override
	public void update(){
		vstavka.draw(game.batch);
		font2.draw(game.batch, "Your Score: " + Integer.toString(game.gs.tmpScore), game.w/2 - vstavka.getWidth()/2.3f, game.h - game.getY(318));
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
		super.hide();
		font2.dispose();
		timer.dispose();
		
	}
}