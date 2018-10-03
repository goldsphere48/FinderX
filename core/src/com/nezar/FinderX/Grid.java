package com.Nezar.FinderX;

import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Grid {
	
	Main game;

	public Square[][] grid;
	
	Random r;
	
	int width;
	int height;
	
	int lastIndexX;
	int lastIndexY;
	
	FreeTypeFontParameter param;
	
	public Square get(int i, int j){
		return grid[i][j];
	}
	
	public Grid(Main game, Stage stage, int w, int h, boolean reactive){
		this.game = game;
		
		init(w, h, reactive);
		
		for(int i = 0; i< w; ++i){
			for(int j = 0; j< h; ++j){
				stage.addActor(grid[i][j]);
			}
		}
				
	}
	
	
	boolean checkSqure(int n){
		for(int i=0;i<width;++i)
			for(int j=0;j<height;++j)
				if((grid[i][j].getN() == n) && (grid[i][j].isActive()))
					return true;
		return false;
	}
	
	public void shuffle(){
		Integer[] ints = new Integer[width*height];
	    for (int i = 0; i < ints.length; i++) {
	        ints[i] = i+1;
	    }
	    int rn=0;
	    int tmp;
	    for(int i=0;i<width*height;i++){
	    	rn = r.nextInt(width*height);
	    	tmp = ints[i];
	    	ints[i] = ints[rn];
	    	ints[rn] = tmp;
	    }
	    for(int i = 0; i< width; ++i)
			for(int j = 0; j< height; ++j){
				grid[i][j].setN(ints[i*width+j]);
				grid[i][j].setActivity(false);
				if(ints[i*width+j] == width*height){
					lastIndexX = i;
					lastIndexY = j;
				}
				if(grid[i][j].getN() < game.gs.currentNumberInArcade)
					grid[i][j].setActivity(true);
			}
	}
	
	public void reActiveArcade(){
		for(int i=0;i<width;++i)
			for(int j=0;j<height;++j)
				if(grid[i][j].getN() >= game.gs.currentNumberInArcade)
					grid[i][j].setActivity(false);
	}
	
	
	public void init(int w, int h, boolean reactive){
		width = w;
		height = h;
		grid = new Square[w][h];
		param = new FreeTypeFontParameter();
		r = new Random(new Date().getTime());
		Integer[] ints = new Integer[w*h];
	    for (int i = 0; i < ints.length; i++) {
	        ints[i] = i+1;
	    }
	    int rn=0;
	    int tmp;
	    for(int i=0;i<w*h;i++){
	    	rn = r.nextInt(w*h);
	    	tmp = ints[i];
	    	ints[i] = ints[rn];
	    	ints[rn] = tmp;
	    }
	    
	    for(int i = 0; i< w; ++i){
			for(int j = 0; j< h; ++j){
				grid[i][j] = new Square(game, (int) (game.w/width - game.getX(226)/width));

				grid[i][j].setN(ints[i*w+j]);
				
				if(ints[i*w+j] == width*height){
					lastIndexX = i;
					lastIndexY = j;
				}
				
				grid[i][j].setWidth(game.w/width - game.getX(226)/width);
				grid[i][j].setHeight(game.w/width - game.getX(226)/width);
				grid[i][j].SetPos(game.getX(113) + i*(int)grid[i][j].getWidth() + i*(int)game.getX(7), game.getY(600) + j*(int)grid[i][j].getHeight() + j*(int)game.getX(7));
			}
		}
	    
	    param.size = (int) game.getFontSizeInPt((int) (game.w/width - game.getX(226)/width));
		game.numberFont = game.gen.generateFont(param);
		game.numberFont.setColor(Color.WHITE);
	}
	
	public void draw(){
	    for(int i = 0; i< width; ++i){
			for(int j = 0; j< height; ++j){
				grid[i][j].drawSquares();
			}
	    }
	}
	
	
	public void reGenerate(int w, int h){
		if(this.width == w && this.height == h){
			Integer[] ints = new Integer[w*h];
		    for (int i = 0; i < ints.length; i++) {
		        ints[i] = i+1;
		    }
		    int rn=0;
		    int tmp;
		    for(int i=0;i<w*h;i++){
		    	rn = r.nextInt(w*h);
		    	tmp = ints[i];
		    	ints[i] = ints[rn];
		    	ints[rn] = tmp;
		    }
		    for(int i = 0; i< w; ++i){
				for(int j = 0; j< h; ++j){

					grid[i][j].setN(ints[i*w+j]);
					
					if(ints[i*w+j] == width*height){
						lastIndexX = i;
						lastIndexY = j;
					}
					grid[i][j].setActivity(false);
				}
		    }
		    
		}
	}
	
	public boolean isWin(){
		return grid[lastIndexX][lastIndexY].active;
	}
	
	public int getW(){
		return width;
	}
	
	public int getH(){
		return height;
	}
	
	public void dispose(){
		game.numberFont.dispose();
	}

}
