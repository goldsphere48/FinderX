package com.Nezar.FinderX;

import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class MenuScreen extends BaseScreen{
	
	Logo logo;
	Sprite menuBg;
	
	FSquare[] fallingSquares;
	RecordButton recordButton;
	ArcadeModeIcon[] arcadeModeIcon;
	
	SocButton[] socButton;
	
	Random random;
	
	public class FSquare{
		Sprite sprite;
		boolean dir;
		float angle;
		float angleStep;
		float X;
		float Y;
		
		public FSquare(String name){
			sprite = game.im.Get(name);
			sprite.setOriginCenter();
			dir = random.nextBoolean();
			
			if(dir)
				angleStep = 100f;
			else
				angleStep = -100f;
		}
		
		public void update(float delta){
			angle+=angleStep*delta;
			sprite.setRotation(angle);
			Y-=150.0f*delta;
			setPosition(X, Y);
			
			if(Y < - sprite.getHeight()){
				Y = game.getY(4832);
				dir = random.nextBoolean();
				
				if(dir)
					angleStep = 100f;
				else
					angleStep = -100f;
			}
		}
		
		public void draw(){
			sprite.draw(game.batch);
		}
		
		public void setPosition(float x, float y){
			sprite.setPosition(x, y);
			X = x;
			Y = y;
		}
	}
	
	public MenuScreen(Main game, Scr num){
		super(game, num);
		
		random = new Random(new Date().getTime());
		
		logo = new Logo(game);
		logo.setPosition(0, game.getY(200));
		menuBg = game.im.Get("MenuBg");
		
		recordButton = new RecordButton(game);
		
		socButton = new SocButton[3];
		
		String[] s;
		s = new String[3];
		s[0] = "https://vk.com/public107502294";
		s[1] = "https://www.facebook.com/Nezar-Studio-987706661289670/timeline";
		s[2] = "https://twitter.com/NezarStudio";
		
		for(int i=0;i<3;++i){
			socButton[i] = new SocButton(game, i, s[i]);
			socButton[i].SetX(game.getX(506) + i*game.getX(11) + i*socButton[i].getWidth());
			stage.addActor(socButton[i]);
		}
		
		arcadeModeIcon = new ArcadeModeIcon[3];
		
		for(int i=0;i<3;++i){
			arcadeModeIcon[i] = new ArcadeModeIcon(game, i);
			arcadeModeIcon[i].SetX(game.getX(164) + i*arcadeModeIcon[i].getWidth() + i*game.getX(29));
			stage.addActor(arcadeModeIcon[i]);
		}

		stage.addActor(recordButton);
		
		fallingSquares = new FSquare[15];
		for(int i=0;i<15;++i){
			fallingSquares[i] = new FSquare("FallingSquare_"+i);
		}
		
		int[][] mask = 
			{{1, 0, 1, 0}, 
			 {0, 0, 0, 0},
			 {0, 1, 0, 0}, 
			 {0, 0, 0, 0},
			 {1, 0, 0, 1},
			 {0, 0, 0, 0},
			 {0, 0, 1, 0},
			 {0, 0, 0, 0},
			 {0, 1, 0, 1},
			 {0, 0, 0, 0},
			 {1, 0, 1, 0},
			 {0, 0, 0, 0},
			 {0, 1, 0, 1},
			 {0, 0, 0, 0},
			 {1,0, 1, 0 },
			 {0, 0, 0, 0},
			 {0, 1, 0, 0}};
		
		int c = 0;
		for(int i=0;i<17;++i){
			for(int j=0;j<4;++j){
				if(mask[i][j] != 0){
					fallingSquares[c].setPosition(game.getX(200 + j*273), game.getY(2560 + i*284));
					c++;
				}
			}
		}
	}

	@Override
	public void show() {
		super.show();
		game.menuWasOpen = true;
		game.gs.tmpScore = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(140.0f/255.0f, 237.0f/255.0f, 234.0f/255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		for(int i=0;i<15;++i){
			fallingSquares[i].update(delta);
			fallingSquares[i].draw();
		}
		logo.draw(game.batch);	
		game.batch.end();	
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
