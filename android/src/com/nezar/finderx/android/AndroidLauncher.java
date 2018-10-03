package com.Nezar.FinderX.android;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.Nezar.FinderX.AdsController;
import com.Nezar.FinderX.MainApplicationlistener;

public class AndroidLauncher extends AndroidApplication implements AdsController {
	
	protected AdView adView;
	protected InterstitialAd interstitialAd;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
         
        // Create a gameView and a bannerAd AdView
        View gameView = initializeForView(new MainApplicationlistener(this), config);
        setupAds();
         
        // Define the layout
        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layout.addView(adView, params);
        setContentView(layout);
    }


	//C82B889178C0FC4F9469CB35C3E73FD3
	
     
    public void setupAds() {
    	adView = new AdView(this);
    	adView.setVisibility(View.INVISIBLE);
    	adView.setBackgroundColor(0x8CEDEA); 
    	if(!checkInternet())
    		setAlpha();
    	adView.setAdUnitId("ca-app-pub-3845171391938075/7885646748");
    	adView.setAdSize(AdSize.SMART_BANNER);
    	
    	interstitialAd = new InterstitialAd(this);
    	interstitialAd.setAdUnitId("ca-app-pub-3845171391938075/4068624347");
    	 
    	AdRequest.Builder builder = new AdRequest.Builder();
    	AdRequest ad = builder.build();
    	interstitialAd.loadAd(ad);
	}
    
    @Override
    public void showInterstitialAd(final Runnable then) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (then != null) {
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Gdx.app.postRunnable(then);
                            AdRequest.Builder builder = new AdRequest.Builder();
                        	AdRequest ad = builder.build();
                        	interstitialAd.loadAd(ad);
                        }
                    });
                }
                if(interstitialAd.isLoaded())interstitialAd.show();
            }
        });
    }
    
    @SuppressLint("NewApi")
	public void setAlpha(){
    	adView.setAlpha(0);
    }
	
	 @Override
	  public void onPause() {
	    adView.pause();
	    super.onPause();
	  }
	 
	  @Override
	  public void onResume() {
	    super.onResume();
	    adView.resume();
	  }
	  
	  @Override
	  public void onDestroy() {
	    adView.destroy();
	    super.onDestroy();
	  }

	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	        	adView.setVisibility(View.VISIBLE);
	        	AdRequest.Builder builder = new AdRequest.Builder();
                AdRequest ad = builder.build();
	            adView.loadAd(ad);
	        }
	    });
		
	}
	
	public boolean checkInternet() {
		 
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
 
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // проверка подключения
        if (activeNetwork != null && activeNetwork.isConnected()) {
           return true;
        }else
 
        return false;
    }

	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	        	adView.setVisibility(View.INVISIBLE);
	        }
	    });
		
	}

	@Override
	public boolean CheckInternetConnection() {
		// TODO Auto-generated method stub
		return checkInternet();
	}

}
