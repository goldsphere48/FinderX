package com.Nezar.FinderX;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

enum Scr{MenuScreen, ChapterSelectionScreen, ArcadeModeSelectionScreen, Chapter, ArcadeMode, RecordScreen, AfterGame}

public class Main extends Game {
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;
	
	Music music;
	Music click;
	
	BaseScreen menuScreen;
	BaseScreen arcadeModeSelectionScreen;
	BaseScreen recordScreen;
	BaseScreen afterGameScreen;
	
	BitmapFont numberFont;
	FreeTypeFontGenerator gen;
	FreeTypeFontGenerator gen2;
	FreeTypeFontGenerator gen3;
	
	BaseScreen chapter;
	
	ImageManager im;
	GameStats gs;
	ShapeRenderer sr;
	
	int w;
	int h;
	int sourceW;
	int sourceH;
	int timesToAd;
	int secretNumber;
	
	boolean screenLoaded[];
	boolean dragged;
	boolean shuffle;
	boolean arcadeOrCareerOpen;
	boolean menuWasOpen;
	boolean secret;
	
	int rollback;
	
	public boolean playPressed;
	
	@Override
	public void create () {
		
		timesToAd = 2;
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		Gdx.input.setCatchBackKey(true);
		
		sourceW = 1536;
		sourceH = 2560;
		
		batch = new SpriteBatch();
		viewport = new ScreenViewport();
		
		sr = new ShapeRenderer();
		
		im = new ImageManager(this);
		gs = new GameStats();
		gs.initSaveSystem();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
		music.setLooping(true);
		music.setVolume(0.25f);
		
		click = Gdx.audio.newMusic(Gdx.files.internal("click.mp3"));
		click.setLooping(false);
		click.setVolume(0.25f);
		
		music.play();
		
		screenLoaded = new boolean[7];
		for(int i=0;i<7;++i)
			screenLoaded[i] = false;
		dragged = false;
		rollback = 0;
		arcadeOrCareerOpen = false;
		menuWasOpen = true;
		secret = false;
		
		menuScreen = new MenuScreen(this, Scr.MenuScreen);
		
		gen = new FreeTypeFontGenerator(Gdx.files.internal("Bebas Neue.ttf"));
		gen2 = new FreeTypeFontGenerator(Gdx.files.internal("Archive.otf"));
		gen3 = new FreeTypeFontGenerator(Gdx.files.internal("Malina.otf"));
		afterGameScreen = new AfterGameScreen(this, Scr.AfterGame);
		
		recordScreen = new RecordScreen(this, Scr.RecordScreen);
		secretNumber = 0;
		
		playPressed = false;
		shuffle = false;
		
		this.setScreen(menuScreen);
	}
	
	public float getFontSizeInPt(int px){
		float result = (float)px*3f/4f;
		float d = result - (int)(result);
		if(d == 0.75f)
			result = (int)(result)+1;
		else if(d == 0.25f)
			result = (int)(result);
		return result;
	}
	
	public float getX(float value){
		return w*value/sourceW;
	}
	
	public float getY(int value){
		return h*value/sourceH;
	}

	@Override
	public void render () {
		super.render();
	}
}