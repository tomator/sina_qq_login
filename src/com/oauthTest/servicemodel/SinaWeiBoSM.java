package com.oauthTest.servicemodel;

public class SinaWeiBoSM {
	public String created_at;// 微博创建时间
	public String id;// 微博ID
	public String mid; // 微博MID
	public String idstr; // 字符串型的微博ID
	public String text; // 微博信息内容
	public String source; // 微博来源
	public String favorited; // 是否已收藏，true：是，false：否
	public String truncated; // 是否被截断，true：是，false：否
	public String thumbnail_pic; // 缩略图片地址，没有时不返回此字段
	public String bmiddle_pic; // 中等尺寸图片地址，没有时不返回此字段
	public String original_pic; // 原始图片地址，没有时不返回此字段
	public GeoSM geo; // 地理信息字段
	public UserSM user; // 微博作者的用户信息字段
	public int reposts_count;//	转发数
	public int comments_count;//评论数
	public int attitudes_count;//表态数

	@Override
	public String toString() {
		return "SinaWeiBoSM [created_at=" + created_at + ", id=" + id
				+ ", mid=" + mid + ", idstr=" + idstr + ", text=" + text
				+ ", source=" + source + ", favorited=" + favorited
				+ ", truncated=" + truncated + ", thumbnail_pic="
				+ thumbnail_pic + ", bmiddle_pic=" + bmiddle_pic
				+ ", original_pic=" + original_pic + ", geo=" + geo + ", user="
				+ user + ", reposts_count=" + reposts_count
				+ ", comments_count=" + comments_count + ", attitudes_count="
				+ attitudes_count + "]";
	}
}
