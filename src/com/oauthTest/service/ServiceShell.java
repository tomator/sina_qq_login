package com.oauthTest.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.content.IntentSender.SendIntentException;

import com.google.gson.Gson;
import com.oauthTest.servicemodel.SinaTokenSM;
import com.oauthTest.servicemodel.SinaWeiBoSM;
import com.oauthTest.sns.Sina;
import com.oauthTest.utils.ConfigUtil;
import com.oauthTest.utils.HttpClientFactory;
import com.oauthTest.utils.LogUtil;

public class ServiceShell {
	private ServiceShell() {

	}

	/**
	 * 获取授权信息请求
	 */
	public static void getSinaTokenInfo(
			ServiceShellListener<SinaTokenSM> serviceShellListener) {
		StringBuilder sb = new StringBuilder(
				"https://api.weibo.com/oauth2/access_token?");
		sb.append("client_id=" + ConfigUtil.sina_client_id);
		sb.append("&client_secret=" + ConfigUtil.sina_client_secret);
		sb.append("&grant_type=authorization_code");
		sb.append("&code=" + ((Sina) ConfigUtil.oauthInter).getCode());
		sb.append("&redirect_uri=" + ConfigUtil.sina_redirect_uri);
		HttpPost post = new HttpPost(sb.toString());
		send(serviceShellListener, post, SinaTokenSM.class);
	}

	/**
	 * 发布新浪微博
	 * */
	public static void updateSinaWeibo(String access_token,
			ServiceShellListener<SinaWeiBoSM> serviceShellListener) {
		StringBuilder sb = new StringBuilder(
				"https://api.weibo.com/2/statuses/update.json");
		HttpPost post = new HttpPost(sb.toString());
		try {
			post.setEntity(new StringEntity("access_token=" + access_token
					+ "&status="
					+ URLEncoder.encode("中文测试--DF4456", HTTP.UTF_8), HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*
		 * 有关Content-Type属性值可以如下两种编码类型： 
		 *  （1）“application/x-www-form-urlencoded”： 表单数据向服务器提交时所采用的编码类型，
		 *  默认的缺省值就是“application/x-www-form-urlencoded”。 
		 *  然而，在向服务器发送大量的文本、包含非ASCII字符的文本或二进制数据时这种编码方式效率很低。  
		 *  （2）“multipart/form-data”： 在文件上载时，所使用的编码类型应当是“multipart/form-data”，
		 *  它既可以发送文本数据，也支持二进制数据上载。  当提交为单单数据时，
		 *  可以使用“application/x-www-form-urlencoded”；
		 *  当提交的是文件时，就需要使用“multipart/form-data”编码类型。  
		 *  在Content-Type属性当中还是指定提交内容的charset字符编码。
		 * 一般不进行设置，它只是告诉web服务器post提交的数据采用的何种字符编码。
		 * */
		post.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		send(serviceShellListener, post, SinaWeiBoSM.class);
	}

	/**
	 * 发送请求
	 * */
	private static void send(ServiceShellListener listenner,
			HttpUriRequest request, Class clazz) {
		try {
			HttpClient httpClient = HttpClientFactory.getInstance();
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String msg = EntityUtils
						.toString(response.getEntity(), "UTF-8");
				Gson gson = new Gson();
				listenner.completed(gson.fromJson(msg, clazz));
			} else {
				listenner.failed("网络问题");
			}
		} catch (Exception e) {
			LogUtil.info(ServiceShell.class, e.getMessage());
			listenner.failed(e.getMessage());
		}
	}
}
