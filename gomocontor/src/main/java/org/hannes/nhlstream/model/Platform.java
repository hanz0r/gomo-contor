package org.hannes.nhlstream.model;

import com.google.gson.annotations.SerializedName;

/**
 * Contains the home and away streams for a specific platform. (ipad, desktop, etc.)
 * @author brock
 *
 */
public class Platform {

	/**
	 * The streams from the hosting team's perspective
	 */
	@SerializedName("home")
	private Channel home;
	
	/**
	 * The streams from the visiting team's perspective
	 */
	@SerializedName("away")
	private Channel away;

	public Channel getHome() {
		return home;
	}

	public Channel getAway() {
		return away;
	}

	@Override
	public String toString() {
		return "Platform [home=" + home + ", away=" + away + "]";
	}

}