package org.hannes.nhlstream.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a game.
 * 
 * This contains the id of the game, the date and the teams that are playing.
 * 
 * {"id":2015020001,"est":"20151007 19:00:00","a":"MTL","h":"TOR"}
 * 
 * @author brock
 *
 */
public class Game {

	/**
	 * The id of the game. Used to identify during lookup
	 */
	@SerializedName("id")
	private String id;
	
	/**
	 * The date at which the game was playeds
	 */
	@SerializedName("est")
	private String date;
	
	/**
	 * The home team that participated in the game
	 */
	@SerializedName("h")
	private String homeTeam;
	
	/**
	 * The away team that participated in the game
	 */
	@SerializedName("a")
	private String awayTeam;

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + "]";
	}
	
}
