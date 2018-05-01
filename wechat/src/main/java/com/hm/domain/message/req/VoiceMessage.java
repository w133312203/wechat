
package com.hm.domain.message.req;

/**
 * 音频消息
 * 
 */
public class VoiceMessage extends BaseMessage {
	
	private String MediaId;//媒体ID
	
	private String Format;//语音格式

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
}

