package com.Nezar.FinderX;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageManager {
	
	HashMap<String, Sprite> data;
	
	Main game;
	Texture finderXLogo;
	Texture fallingSquares; 
	Texture buttons;
	Texture gameBg;
	Texture arcadeModeIcon;
	Texture vstavka;
	Texture record;
	Texture socButtons;
	Texture alarm;
	Texture recordIcons;
	Texture brain;
	Texture circle;
	
	TextureRegion home, replay;
	
	public ImageManager(Main game){
		
		this.game = game;
		
		data = new HashMap<String, Sprite>();
		
		finderXLogo = new Texture("logo.png");
		fallingSquares = new Texture("fallingsquares.png");
		buttons = new Texture("buttons.png");
		gameBg = new Texture("gamebg.png");
		arcadeModeIcon = new Texture("arcademodeicon.png");
		vstavka = new Texture("vstavka.png");
		record = new Texture("record.png");
		socButtons = new Texture("socbuttons.png");
		alarm = new Texture("alarm.png");
		recordIcons = new Texture("recordicons.png");
		brain = new Texture("brain.png");
		circle = new Texture("circle.png");
		home = new TextureRegion(buttons, 320, 0, 160, 169);
		replay = new TextureRegion(buttons, 160, 0, 160, 169);
		
		
		finderXLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fallingSquares.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttons.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		gameBg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		arcadeModeIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		vstavka.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		record.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		socButtons.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		alarm.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		recordIcons.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		brain.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		circle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Load(new Sprite(finderXLogo, 0, 0, finderXLogo.getWidth(), finderXLogo.getHeight()), "FinderXLogo", false);
		for(int i=0;i<3;++i)
			Load(new Sprite(buttons, buttons.getWidth()/3*i, 0, buttons.getWidth()/3, buttons.getHeight()), "Button"+i, false);
		Load(new Sprite(gameBg, 0, 0, gameBg.getWidth(), gameBg.getHeight()), "GameBg", false);
		Load(new Sprite(vstavka, 0, 0, vstavka.getWidth(), vstavka.getHeight()), "Vstavka", true);
		Load(new Sprite(recordIcons, 0, 0, recordIcons.getWidth(), recordIcons.getHeight()), "RecordIcons", true);
		Load(new Sprite(brain, 0, 0, brain.getWidth(), brain.getWidth()), "Brain", true);
		Load(new Sprite(record, 0, 0, record.getWidth(), record.getHeight()), "Record", true);
		Load(new Sprite(alarm, 0, 0, alarm.getWidth(), alarm.getHeight()), "Alarm", false);
		Load(new Sprite(circle, 0, 0, circle.getWidth()/2, circle.getHeight()), "ReplayButton", true);
		Load(new Sprite(circle, circle.getWidth()/2, 0, circle.getWidth()/2, circle.getHeight()), "MenuButton", true);
		
		for(int i=0;i<3;++i)
			Load(new Sprite(socButtons, socButtons.getWidth()*i/3, 0, socButtons.getWidth()/3, socButtons.getHeight()), "SocButton"+i, true);
		
		for(int i=0;i<3;++i){
			Load(new Sprite(arcadeModeIcon, arcadeModeIcon.getWidth()*i/3, 0, arcadeModeIcon.getWidth()/3, arcadeModeIcon.getHeight()), "ArcadeModeIcon_"+i, true);
		}
		
		for(int i=0;i<5;++i)
			for(int j=0;j<3;++j){
				Load(new Sprite(fallingSquares, i*fallingSquares.getWidth()/5, j*fallingSquares.getHeight()/3, fallingSquares.getWidth()/5, fallingSquares.getHeight()/3), "FallingSquare_"+(i*3+j), true);
			}
	}
	
	public void Load(Sprite sprite, String name, boolean sq){
		if(!sq) sprite.setSize((int)(sprite.getWidth()*game.w/game.sourceW), (int)(sprite.getHeight()*game.h/game.sourceH));
		else    sprite.setSize((int)(sprite.getWidth()*game.w/game.sourceW), sprite.getHeight()*((int)(sprite.getWidth()*game.w/game.sourceW)/sprite.getWidth()));
		data.put(name, sprite);
	}
	
	public Sprite Get(String name){
		return data.get(name);
	}
}