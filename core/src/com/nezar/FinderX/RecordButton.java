package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class RecordButton extends Actor{
	
	class Click extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
			game.setScreen(game.recordScreen);
		}
	}

	Sprite sprite;
	Main game;
	
	public RecordButton(Main game){
		
		this.game = game;
		
		sprite = game.im.Get("Record");
		
		setX(game.getX(577));
		setY(game.getY(600));
		
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