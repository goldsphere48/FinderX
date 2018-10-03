package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Alarm extends Actor{
	
	int sec;
	int min;
	MyTimer timer;
	BitmapFont font;
	FreeTypeFontParameter param;
	
	int timerSec;
	
	Main game;
	
	
	public Alarm(Main game){
		timer = new MyTimer(1, game,  false);
		sec = min = 0;
		this.game = game;
		param = new FreeTypeFontParameter();
		param.size = (int) game.getFontSizeInPt((int) game.getX(200));
		setX(timer.getX());
		setY(timer.getY());
		setWidth(param.size);
		setHeight(param.size);
		font = game.gen.generateFont(param);
	}
	
	public void start(){
		sec = min = 0;
		timer.start();
	}
	
	public void setTimer(int sec){
		
	}
	
	public int getTimeInSec(){
		return min*60 + sec;
	}
	
	public void dispose(){
		font.dispose();
		timer.dispose();
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		font.draw(batch, Integer.toString(min) + ":" + Integer.toString(sec), getX(0), getY());
	}
	
	@Override
	public void act(float delta){
		if(timer.isDone()){
			sec++;
			timer.start();
		}
		
		/*if(game.recoil > 0){
			sec+=3;
			game.recoil = 0;
		}*/
		if(sec >= 60){
			min++;
			sec = 0;
		}
	}

}