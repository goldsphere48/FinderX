package com.Nezar.FinderX;

public class FirstArcadeMode extends BaseMode {
	
	Alarm alarm;
	
	public FirstArcadeMode(Main game, Scr num){
		super(game, num);
		
		alarm = new Alarm(game);
		stage.addActor(alarm);
		grid = new Grid(game, stage, 8, 8, false);
	}
	
	
	@Override
	public void show() {
		super.show();
		game.gs.currentMode = 1;
		alarm.start();
	}
	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		if(grid.isWin()){
			game.gs.currentNumberInArcade = 1;
			game.gs.currentPointsInFirstArcadeMode = alarm.getTimeInSec();
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