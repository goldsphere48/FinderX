package com.Nezar.FinderX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SocButton extends Actor{
	
	BaseScreen scr;
	
	class Click extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
			Gdx.net.openURI(url);
		}
	}

	Sprite sprite;
	String url;
	
	Main game;
	
	public SocButton(Main game, int n, String url){
		
		this.game = game;
		this.url = url;
		
		sprite = game.im.Get("SocButton"+n);
		
		setY(game.getY(350));
		
		
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		
		addListener(new Click());
	}
	
	public void SetX(float x){
		setX(x);
		sprite.setPosition(getX(), getY());
	}
	
	@Override
	public void draw(Batch batch, float alpha){
		sprite.draw(batch);
	}
		
}
