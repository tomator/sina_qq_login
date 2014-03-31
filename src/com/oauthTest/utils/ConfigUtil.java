package com.oauthTest.utils;

import java.util.Map;

import android.app.Activity;

import com.oauthTest.sns.BaseSNS;


/**
 * 获取配置文件
 * 
 * @author bywyu
 * 
 */
public class ConfigUtil {
	/**
	 * 当前操作的社交网站对象
	 * */
	public static BaseSNS oauthInter;
	/**
	 * 请求的token信息
	 * */
	public static Object tokenInfo;
	/**
	 * SharedPreferences的文件名
	 * */
	public static final String sharePreferencesName="config";
	// ---------------------sina
	public static final String sina_client_id = "4081845536";//appKey
	public static final String sina_client_secret="6fcaa2c3ce740cbb36d3734280ffb227";//appSecret
	public static final String sina_Authoriz_url = "https://open.weibo.cn/oauth2/authorize";
	public static final String sina_Access_token_url = "https://api.weibo.com/oauth2/access_token";
	public static final String sina_redirect_uri = "https://api.weibo.com/oauth2/default.html";
	//----------------------qq
	public static final String qq_client_id = "100550836";
	public static final String qq_client_secret="1d89d0292f0f4e4dfb24170c34199f63";
	public static final String qq_Authoriz_url = "https://openmobile.qq.com/oauth2.0/m_authorize";
	public static final String qq_redirect_uri = "http://www.qq.com";
}
