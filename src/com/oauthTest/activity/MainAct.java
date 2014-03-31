package com.oauthTest.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.oauthTest.R;
import com.oauthTest.service.ServiceShell;
import com.oauthTest.service.ServiceShellListener;
import com.oauthTest.servicemodel.SinaTokenSM;
import com.oauthTest.servicemodel.SinaWeiBoSM;
import com.oauthTest.sns.QQ;
import com.oauthTest.sns.Sina;
import com.oauthTest.utils.ConfigUtil;

/**
 * 登录选择界面 1.根据不同的按钮点击初始化数据
 * 
 * @author bywyu
 */
public class MainAct extends Activity implements OnClickListener {
	private Button btn_sina_login;
	private Button btn_qq_login;
	private Button btn_sina_share;
	private SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences(ConfigUtil.sharePreferencesName, MODE_PRIVATE);
		setContentView(R.layout.main);
		btn_sina_login = (Button) findViewById(R.id.btn_sina_login);
		btn_qq_login = (Button) findViewById(R.id.btn_qq_login);
		btn_sina_share = (Button) findViewById(R.id.btn_sina_share);

		btn_sina_login.setOnClickListener(this);
		btn_qq_login.setOnClickListener(this);
		btn_sina_share.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sina_login:
			ConfigUtil.oauthInter = new Sina();
			break;
		case R.id.btn_qq_login:
			ConfigUtil.oauthInter = new QQ();
			break;
		case R.id.btn_sina_share:
			String access_token = sp.getString("access_token", "");
			long saveTime = sp.getLong("saveTime", 0);
			long currentTime = System.currentTimeMillis() / 1000;
			long expires_in = sp.getLong("expires_in",0);
			if ("".equals(access_token)
					|| currentTime > saveTime +expires_in) {
				ConfigUtil.oauthInter = new Sina();
				break;
			}

			ServiceShell.updateSinaWeibo(access_token,
					new ServiceShellListener<SinaWeiBoSM>() {

						@Override
						public boolean failed(String message) {
							return false;
						}

						@Override
						public void completed(SinaWeiBoSM data) {
							Toast.makeText(MainAct.this, data.toString(), 1)
									.show();
						}
					});
			return;
		}
		Intent intent = new Intent(MainAct.this, AuthorizationAct.class);
		startActivity(intent);
	}
}