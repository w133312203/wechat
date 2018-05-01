
package com.hm.domain;

/**
 * 图文消息素材表
 * 
 */
public class WxMaterialNews {
	
	private Integer id;
	
	private Integer accountId;//后台微信公众号ID
	
	private String mediaId;//图文消息素材ID
	
	private String title;//图文消息的标题
	
	private String author;//作者
	
	private Integer showCoverPic;//是否显示封面，0为false，即不显示，1为true，即显示
	
	private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	
	private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	
	private String contentSourceUrl;//图文消息的原文地址，即点击“阅读原文”后的URL
	
	private String thumbMediaId;//图文消息的封面图片素材id（必须是永久mediaID）
	
	private String url;//图文页的URL
	
	private String thumbUrl;//图文消息封面图片url
	
	private String picUrl;//图片OOS存储路径
	
	private long createTime;//这篇图文消息素材的创建时间
	
	private long updateTime;//这篇图文消息素材的最后更新时间

	private Integer sortLevel;//多图文排序

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(Integer showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSortLevel() {
		return sortLevel;
	}

	public void setSortLevel(Integer sortLevel) {
		this.sortLevel = sortLevel;
	}

}

