package com.oauthTest.servicemodel;

public class UserSM {
	public String id;// 用户UID
	public String idstr;// 字符串型的用户UID
	public String screen_name;// 用户昵称
	public String name;// 友好显示名称
	public int province;// 用户所在省级ID
	public int city;// 用户所在城市ID
	public String location;// 用户所在地
	public String description;// 用户个人描述
	public String url;// 用户博客地址
	public String profile_image_url;// 用户头像地址，50×50像素
	public String profile_url;// 用户的微博统一URL地址
	public String domain;// 用户的个性化域名
	public String weihao;// 用户的微号
	public String gender;// 性别，m：男、f：女、n：未知
	public int followers_count;// 粉丝数
	public int friends_count;// 关注数
	public int statuses_count;// 微博数
	public String created_at;// 用户创建（注册）时间
	public boolean following;// 暂未支持
	public boolean allow_all_act_msg;// 是否允许所有人给我发私信，true：是，false：否
	public boolean geo_enabled;// 是否允许标识用户的地理位置，true：是，false：否
	public boolean verified;// 是否是微博认证用户，即加V用户，true：是，false：否
	public int verified_type;// 暂未支持
	public String remark;// 用户备注信息，只有在查询用户关系时才返回此字段
	public boolean allow_all_comment;// 是否允许所有人对我的微博进行评论，true：是，false：否
	public String avatar_large; // 用户大头像地址
	public String verified_reason;// 认证原因
	public boolean follow_me;// 该用户是否关注当前登录用户，true：是，false：否
	public int online_status;// 用户的在线状态，0：不在线、1：在线
	public int bi_followers_count;// 用户的互粉数
	public String lang;// 用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语

	@Override
	public String toString() {
		return "User [id=" + id + ", idstr=" + idstr + ", screen_name="
				+ screen_name + ", name=" + name + ", province=" + province
				+ ", city=" + city + ", location=" + location
				+ ", description=" + description + ", url=" + url
				+ ", profile_image_url=" + profile_image_url + ", profile_url="
				+ profile_url + ", domain=" + domain + ", weihao=" + weihao
				+ ", gender=" + gender + ", followers_count=" + followers_count
				+ ", friends_count=" + friends_count + ", statuses_count="
				+ statuses_count + ", created_at=" + created_at
				+ ", following=" + following + ", allow_all_act_msg="
				+ allow_all_act_msg + ", geo_enabled=" + geo_enabled
				+ ", verified=" + verified + ", verified_type=" + verified_type
				+ ", remark=" + remark + ", allow_all_comment="
				+ allow_all_comment + ", avatar_large=" + avatar_large
				+ ", verified_reason=" + verified_reason + ", follow_me="
				+ follow_me + ", online_status=" + online_status
				+ ", bi_followers_count=" + bi_followers_count + ", lang="
				+ lang + "]";
	}
}
