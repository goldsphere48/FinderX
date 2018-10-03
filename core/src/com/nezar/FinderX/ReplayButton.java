package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ReplayButton extends Actor{

	
	class Click extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
			game.timesToAd--;
			switch(game.gs.currentMode){
			case 1:
				game.chapter = new FirstArcadeMode(game, Scr.ArcadeMode);
				game.setScreen(game.chapter);
				break;
			case 2:
				game.chapter = new SecondArcadeMode(game, Scr.ArcadeMode);
				game.setScreen(game.chapter);
				break;
			case 3:
				game.chapter = new ThirdArcadeMode(game, Scr.ArcadeMode);
				game.setScreen(game.chapter);
				break;
			}
		}
	}

	Sprite sprite;
	
	Main game;
	
	public ReplayButton(Main game){
		
		this.game = game;
		
		sprite = game.im.Get("ReplayButton");
		
		setX(game.w/2 - sprite.getWidth() - sprite.getWidth()/4);
		setY(game.getY(340));
		
		sprite.setPosition(getX(), getY());
		
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		
		addListener(new Click());
	}
	
	public void setPos(int x, int y){
		
	}
	

	@Override
	public void draw(Batch batch, float alpha){
		sprite.draw(batch);
	}
		
}
