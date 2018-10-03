package com.Nezar.FinderX;

import com.badlogic.gdx.ApplicationListener;

public class MainApplicationlistener implements ApplicationListener{
    Main game;
    boolean tmp;
    private AdsController adsController;
         
    public MainApplicationlistener(AdsController adsController){
        this.adsController = adsController;
        tmp = false;
    }
     
    @Override
    public void create () {
        game = new Main();
        adsController.showBannerAd();
        game.create();
    }
 
    @Override
    public void render () {
    	if(adsController.CheckInternetConnection()){
	    	if(game.timesToAd != 0)game.render();
	    	else
	        if (adsController.CheckInternetConnection()) {
	            adsController.showInterstitialAd(new Runnable() {
	                @Override
	                public void run() {
	                	game.timesToAd = 2;
	                }
	            });
	        } else {
	            System.out.println("Interstitial ad not (yet) loaded");
	        }
    	}else
    		game.render();
    }

	@Override
	public void resize(int width, int height) {
		game.resize(width, height);
		
	}

	@Override
	public void pause() {
		game.pause();
		
	}

	@Override
	public void resume() {
		game.resume();
		
	}

	@Override
	public void dispose() {
		game.dispose();
		
	}
}
