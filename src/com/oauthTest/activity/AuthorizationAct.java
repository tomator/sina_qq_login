package com.oauthTest.activity;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oauthTest.R;
import com.oauthTest.service.ServiceShell;
import com.oauthTest.service.ServiceShellListener;
import com.oauthTest.servicemodel.QQTokenSM;
import com.oauthTest.servicemodel.SinaTokenSM;
import com.oauthTest.sns.QQ;
import com.oauthTest.sns.Sina;
import com.oauthTest.utils.ConfigUtil;

/**
 * 用户授权页面 1.初始化OAuth对象 2.获取用户授权页面并填充至webView 3.根据载入的url判断匹配规则的结果执行跳转
 * 
 * @author bywyu
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class AuthorizationAct extends Activity {
	private final String LOGTAG = "AuthorizationAct";
	private WebView authorizationView;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authorization_ui);
		initView(ConfigUtil.oauthInter.getAuthorizeURL());
	}

	private void initView(String url) {
		sp = getSharedPreferences(ConfigUtil.sharePreferencesName, MODE_PRIVATE);
		authorizationView = (WebView) findViewById(R.id.authorizationView);
		authorizationView.clearCache(true);
		authorizationView.getSettings().setJavaScriptEnabled(true);
		authorizationView.getSettings().setSupportZoom(true);
		authorizationView.getSettings().setBuiltInZoomControls(true);
		authorizationView.setWebViewClient(new WebViewC());
		authorizationView.loadUrl(url);
	}

	class WebViewC extends WebViewClient {
		private int index = 0;

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			view.loadUrl(url);
			return true;
		}

		/**
		 * 由于腾讯授权页面采用https协议 执行此方法接受所有证书
		 */
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			handler.proceed();
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			/**
			 * url.contains(ConfigUtil.callBackUrl) 如果授权成功url中包含之前设置的callbackurl
			 * 包含：授权成功 index == 0 由于该方法onPageStarted可能被多次调用造成重复跳转 则添加此标示
			 */
			if (url.startsWith(ConfigUtil.oauthInter.getRedirectURI())
					&& index == 0) {
				++index;
				if (ConfigUtil.oauthInter instanceof QQ) {
					String[] params = url.split("#")[1].split("&");
					QQTokenSM qqTokenSM=new QQTokenSM();
					int flagCount=0;
					for (String str : params) {
						String[] strArr = str.split("=");
						if ("access_token".equals(strArr[0])) {
							qqTokenSM.access_token=strArr[1];
							flagCount++;
						}
						if ("expires_in".equals(strArr[0])) {
							qqTokenSM.expires_in=strArr[1];
							flagCount++;
						}
						if ("openid".equals(strArr[0])) {
							qqTokenSM.openid=strArr[1];
							flagCount++;
						}
						if (flagCount==3) {
							ConfigUtil.tokenInfo = qqTokenSM;
							showAcivity(ShowAccessTokenAct.class);
							return;
						}
					}
				} else if (ConfigUtil.oauthInter instanceof Sina) {
					Uri uri = Uri.parse(url);
					((Sina) ConfigUtil.oauthInter).setCode(uri
							.getQueryParameter("code"));
					((Sina) ConfigUtil.oauthInter).setState(uri
							.getQueryParameter("state"));
					ServiceShell
							.getSinaTokenInfo(new ServiceShellListener<SinaTokenSM>() {

								@Override
								public void completed(SinaTokenSM data) {
									ConfigUtil.tokenInfo = data;
									Editor edit = sp.edit();
									edit.putString("access_token",
											data.access_token);
									edit.putLong("expires_in",
											data.expires_in);
									edit.putString("uid", data.uid);
									/*
									 * 存储当前时间，单位：秒
									 * */
									edit.putLong("saveTime",System.currentTimeMillis()/1000);
									edit.commit();
									showAcivity(ShowAccessTokenAct.class);
								}

								@Override
								public boolean failed(String message) {
									return false;
								}
							});
				}
			}
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
		}

	}

	private void showAcivity(Class clazz) {
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
		finish();
	}
}
