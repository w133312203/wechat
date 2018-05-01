
package com.hm.domain.message.resp;

/**
 * 音乐消息
 * 
 */
public class MusicMessage extends BaseMessage {
	
	private Music Music;//音乐

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}