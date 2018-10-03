package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ArcadeModeIcon extends Actor{
	
	Main game;
	
	Sprite sprite;
	
	int n;
	
	class Click extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
				switch(n){
				case 0:
					game.chapter = new FirstArcadeMode(game, Scr.ArcadeMode);
					game.setScreen(game.chapter);
					break;
				case 1:
					game.chapter = new SecondArcadeMode(game, Scr.ArcadeMode);
					game.setScreen(game.chapter);
					break;
				case 2:
					game.chapter = new ThirdArcadeMode(game, Scr.ArcadeMode);
					game.setScreen(game.chapter);
					break;
				}
		}
	}
	
	
	public ArcadeModeIcon(Main game, int n){
		this.game = game;
		this.n = n;

		sprite = game.im.Get("ArcadeModeIcon_" + n);
		
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		
		setX(game.w/2 - sprite.getWidth()/2);
		setY(game.h - game.getY(1520));
		
		sprite.setPosition(getX(), getY());
		addListener(new Click());
	}
	
	
	public void SetX(float x){
		setX(x);
		sprite.setPosition(getX(), getY());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		sprite.draw(batch);
	}
	
	@Override
	public void act(float delta){
		
	}
}