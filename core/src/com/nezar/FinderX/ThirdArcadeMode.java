package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class ThirdArcadeMode extends BaseMode {
	
	
	BitmapFont font2;
	Sprite vstavka;
	MyTimer alarm;

	
	public ThirdArcadeMode(Main game, Scr num){
		super(game, num);
		
		param.size = (int) game.getFontSizeInPt((int) game.getX(120));
		font2 = game.gen2.generateFont(param);
		font2.setColor(Color.BLACK);
		
		vstavka = game.im.Get("Vstavka");
		vstavka.setPosition( game.w/2 - vstavka.getWidth()/2, game.h - game.getY(278) - vstavka.getHeight());
		
		alarm = new MyTimer(300, game, true);
		stage.addActor(alarm);

		grid = new Grid(game, stage, 8, 8, false);
	}
	
	
	@Override
	public void show() {
		super.show();
		game.gs.currentMode = 3;
		alarm.start();
	}
	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		if(game.shuffle){
			game.shuffle = false;
			grid.shuffle();
		}
		if(grid.isWin()){
			game.gs.currentNumberInArcade = 1;
			grid.reGenerate(8, 8);
		}
		if(alarm.isDone()){
			game.gs.currentNumberInArcade = 1;
			game.gs.save();
			game.setScreen(game.afterGameScreen);
		}
		if(win){
			alarm.start();
			grid.reGenerate(8, 8);
			win = false;
			game.gs.currentNumberInArcade = 1;
			game.gs.tmpScore = 0;
		}
		
		numNumbers = 0;
		
	}
	
	@Override
	public void update(){
		vstavka.draw(game.batch);
		font2.draw(game.batch, "Your Score: " + Integer.toString(game.gs.tmpScore), game.w/2 - vstavka.getWidth()/2.4f, game.h - game.getY(318));
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
		alarm.dispose();
		
	}
}
