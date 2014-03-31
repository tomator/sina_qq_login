package com.oauthTest.activity;

import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.oauthTest.R;
import com.oauthTest.utils.ConfigUtil;

/**
 * 展示AccessToken
 * 
 */
public class ShowAccessTokenAct extends Activity {
	private TextView tv_show_token;
	private Object tokenInfo;//token信息
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showaccesstoken_ui);
		receiveParams();
		initView();
		initData();
	}
	/**
	 * 接收传递过来的参数
	 * */
	private void receiveParams() {
		this.tokenInfo=ConfigUtil.tokenInfo;
		ConfigUtil.tokenInfo=null;
	}
	/**
	 * 初始化View
	 * */
	private void initView(){
		tv_show_token=(TextView) findViewById(R.id.tv_show_token);
	}
	/**
	 * 初始化Data
	 * */
	private void initData(){
		tv_show_token.setText(tokenInfo.toString());
	}
	
}
