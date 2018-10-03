package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BackButton extends Actor{
	
	BaseScreen scr;
	
	class Click extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
			game.setScreen(scr);
			game.gs.save();
		}
	}

	Sprite sprite;
	
	Main game;
	
	public BackButton(Main game, BaseScreen scr){
		
		this.game = game;
		this.scr = scr;
		
		sprite = game.im.Get("Button0");
		
		setX(game.getX(120));
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
