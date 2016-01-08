package org.hannes.nhlstream.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a channel
 * 
 * @author brock
 */
public class Channel {
	
	/**
	 * Link to the audio stream. Usually a radio broadcast
	 */
	@SerializedName("radio")
	private AudioStream audio;
	
	/**
	 * The stream to the condensed game.
	 * Condensed games are games with just the highlights of the game
	 */
	@SerializedName("vod-condensed")
	private VideoStream condensed;

	/**
	 * The stream to the continuous game.
	 * Continuous games are games with all of the break cut out
	 */
	@SerializedName("vod-continuous")
	private VideoStream continuous;

	/**
	 * The stream to the full game
	 */
	@SerializedName("vod-whole")
	private VideoStream full;

	public AudioStream getAudio() {
		return audio;
	}

	public VideoStream getCondensed() {
		return condensed;
	}

	public VideoStream getContinuous() {
		return continuous;
	}

	public VideoStream getFull() {
		return full;
	}

	@Override
	public String toString() {
		return "Channel [audio=" + audio + ", condensed=" + condensed + ", continuous=" + continuous + ", full=" + full + "]";
	}

}