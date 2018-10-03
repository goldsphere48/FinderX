package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Square extends Actor{
	
	Main game;
	Rectangle rect;
	
	int n;
	boolean active;
	int tmp;
	int numNumbers;
	int size;
	private float x;
	private float y;
	
	String color;
	
	class Active extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y){
			
			game.click.stop();
			game.click.play();
			
			game.secretNumber+=n;
			game.secretNumber*=10;
			if(game.secretNumber == 8570)
				game.secret = true;
			
				switch(game.gs.currentMode){
				case 1:
					if(n == game.gs.currentNumberInArcade){
						game.gs.currentNumberInArcade++;
						
						setActivity(true);
					}else
						game.rollback = n;
					break;
				case 2:
					if(n == game.gs.currentNumberInArcade){
						game.gs.currentNumberInArcade++;
						game.gs.tmpScore++;
						game.gs.bestNumbers++;
						if(game.gs.tmpScore > game.gs.bestSecondModePoints)
							game.gs.bestSecondModePoints = game.gs.tmpScore;
						setActivity(true);
					}else
						game.rollback = n;
					break;
				case 3:
					if(n == game.gs.currentNumberInArcade){
						game.gs.currentNumberInArcade++;
						game.gs.tmpScore++;
						game.gs.bestNumbers++;
						if(game.gs.tmpScore > game.gs.bestThirdModePoints)
							game.gs.bestThirdModePoints = game.gs.tmpScore;
						game.shuffle = true;
						setActivity(true);
					}else
						game.rollback = n;
					break;
				}	
			}
		}
	
	public Square(Main game, int size){
		this.game = game;
		tmp = numNumbers = 0;
		rect = new Rectangle(0, 0, size, size);
		addListener(new Active());
		color = "7B9997";
	}
	
	public int getN(){
		return n;
	}
	
	public void setN(int m){
		n = m;
		tmp = n;
		numNumbers = 0;
		while(tmp != 0){
			tmp = tmp/10;
			numNumbers++;
		}
	}
	
	public void setActivity(boolean a){
		active = a;
		if(a){
			int tmp = (int) (Math.random()*5);
			switch(tmp){
				case 0:
					color = "3DC847";
				break;
				case 1:
					color = "3392C0";
				break;
				case 2:
					color = "5A2048";
				break;
				case 3:
					color = "D72D48";
				break;
				case 4:
					color = "FFCC3B";
				break;
	
			}
		} else
			color = "7B9997";
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void SetPos(float x, float y){
		setX(x);
		setY(y);
		rect.setPosition(x, y);
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		x =  getX() + rect.getWidth()/7*3 - (numNumbers-1)*rect.getWidth()/18*3;
		y = getY() + rect.getHeight()/4*3;
		game.numberFont.draw(batch, Integer.toString(n), x, y);
	}
	
	public void drawSquares(){
		
		game.sr.setColor(Color.valueOf(color));
		game.sr.rect(rect.x, rect.y, rect.width, rect.height);
			
	}
}
