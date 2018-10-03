package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

public class MyTimer extends Actor{
    private long start;
    private long secsToWait;
    
    Main game;
	BitmapFont font;
	FreeTypeFontParameter param;
	boolean drawing;
	boolean ss;

    public MyTimer(long secsToWait, Main game, boolean drawing)
    {
    	this.game = game;
        this.secsToWait = secsToWait;
        
        ss = false;

		setX(game.getX(1200));
		setY(game.h - game.getY(95));
		if(drawing){
	        param = new FreeTypeFontParameter();
			param.size = (int) game.getFontSizeInPt((int) game.getX(200));
			font = game.gen.generateFont(param);
		}
		this.drawing = drawing;
    }
    
    public void stop(){
    	ss = true;
    }
    
    public void dispose(){
    	if(drawing)font.dispose();
    }

    public void start()
    {
        start = TimeUtils.millis() / 1000;
    }

    public boolean isDone()
    {
        return TimeUtils.millis() / 1000 - start >= secsToWait;
    }
    
    
    @Override
	public void draw(Batch batch, float alpha){
    	
	    int m = (int)(secsToWait - (TimeUtils.millis() / 1000 - start))/60;
	    int s = (int)(secsToWait - (TimeUtils.millis() / 1000 - start))  - (int)((secsToWait - (TimeUtils.millis() / 1000 - start))/60)*60;
	    
	    if(ss)
	    	m = s = 0;
    	
    	if(drawing){
	    	
			font.draw(batch, Integer.toString(m) + ":" + Integer.toString(s), getX(0), getY());
    	}
	}
	
}