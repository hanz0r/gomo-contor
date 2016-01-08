package org.hannes.nhlstream.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * Conntains information about the streams, the game highlights and the status
 * of the game.
 * 
 * @author brock
 *
 */
public class GameInformation {

	/**
	 * TODO: Not 100% sure
	 */
	@SerializedName("avaEvts")
	private String[] events;
	
	/**
	 * TODO: Not 100% sure
	 */
	@SerializedName("avaInGmEvts")
	private String[] inGameEvents;
	
	/**
	 * Indicates the game has been finished
	 */
	@SerializedName("finish")
	private boolean finished;

	/**
	 * The status of the game.
	 * Known values: 7 = finished
	 */
	@SerializedName("status")
	private int status;
	
	/**
	 * Should contain the mappings for the streams <-> platform but we only
	 * use the ipad streams so this should be faster.
	 */
	@SerializedName("gameStreams")
	private Container container;
	
	/**
	 * FIXME: Include the highlights in the model
	 */
	@SuppressWarnings("unused")
	private Container highlights;
	
	public String[] getEvents() {
		return events;
	}

	public String[] getInGameEvents() {
		return inGameEvents;
	}

	public boolean isFinished() {
		return finished;
	}

	public int getStatus() {
		return status;
	}

	public Platform getPlatform() {
		return container.platform;
	}

	@Override
	public String toString() {
		return "GameInformation [events=" + Arrays.toString(events) + ", inGameEvents=" + Arrays.toString(inGameEvents)
				+ ", finished=" + finished + ", status=" + status + ", platform=" + container.platform + "]";
	}

	/**
	 * In the information response, the the gamestreams are stored for their
	 * respective platforms. We should store them in a map and associate them
	 * with their platform key, but since we are only ever going to call for
	 * the ipad streams, it is more performant to create this class :-)
	 * 
	 * @author brock
	 */
	private static class Container {
		
		/**
		 * 
		 */
		@SerializedName("ipad")
		private Platform platform;
		
	}
	
}