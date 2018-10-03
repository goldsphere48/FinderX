package com.Nezar.FinderX;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Logo extends Sprite{
	int x;
	int y;
	public Logo(Main game){
		super(game.im.Get("FinderXLogo"));
		x = y = 0;
	}
}