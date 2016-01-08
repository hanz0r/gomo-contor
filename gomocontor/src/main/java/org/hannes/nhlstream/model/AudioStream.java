package org.hannes.nhlstream.model;

import com.google.gson.annotations.SerializedName;

/**
 * Contains information about an audio stream
 * 
 * @author brock
 *
 */
public class AudioStream {

	/**
	 * The link to the broadcast
	 */
	@SerializedName("default")
	private String url;

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "AudioStream [url=" + url + "]";
	}

}
