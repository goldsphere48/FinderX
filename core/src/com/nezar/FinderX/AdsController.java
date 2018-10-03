package com.Nezar.FinderX;

public interface AdsController {
	public void showBannerAd();
	public void hideBannerAd();
	public void showInterstitialAd (final Runnable then);
	public boolean CheckInternetConnection();
}
