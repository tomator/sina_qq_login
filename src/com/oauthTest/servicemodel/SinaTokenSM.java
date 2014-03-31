package com.oauthTest.servicemodel;

public class SinaTokenSM {
	public String access_token;
	public long expires_in;
	public String uid;

	public String toString() {
		return "access_token=" + access_token + ",expires_in=" + expires_in
				+ ",uid=" + uid;
	}
}
