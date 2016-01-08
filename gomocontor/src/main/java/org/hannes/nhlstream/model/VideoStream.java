package org.hannes.nhlstream.model;

import com.google.gson.annotations.SerializedName;

/**
 * Contains information about a video stream
 * 
 * @author brock
 *
 */
public class VideoStream {
	
//	private static final String DESKTOP_URL = "http://nlds150.cdnl3nl.neulion.com/nlds_vod/nhl/vod";
//	private static final String MOBILE_URL = "http://nlds150.cdnak.neulion.com/nlds_vod/nhl/vod";

	/**
	 * The link to the broadcast
	 */
	@SerializedName("bitrate0")
	private String url;
	
	/**
	 * The thumbnail of the stream
	 */
	@SerializedName("image")
	private String image;


	public String getUrl() {
		return url;
	}

	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "VideoStream [url=" + url + ", image=" + image + "]";
	}

}