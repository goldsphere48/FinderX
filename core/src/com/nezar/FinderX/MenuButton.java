package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuButton extends Actor{

	
	class Click extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
			game.timesToAd--;
			game.setScreen(game.menuScreen);
		}
	}

	Sprite sprite;
	
	Main game;
	
	public MenuButton(Main game){
		
		this.game = game;
		
		sprite = game.im.Get("MenuButton");
		setX(game.w/2 + sprite.getWidth()/4);
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
