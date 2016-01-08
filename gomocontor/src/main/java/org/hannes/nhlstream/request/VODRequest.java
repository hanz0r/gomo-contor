package org.hannes.nhlstream.request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.concurrent.ExecutorService;

import org.hannes.nhlstream.model.Game;
import org.hannes.nhlstream.model.GameInformation;

import com.google.gson.Gson;

public class VODRequest implements Request<GameInformation> {
	
	/**
	 * The unformatted url to the location of the 
	 */
	private static final String UNFORMATTED_URL = "http://smb.cdnak.neulion.com/fs/nhl/mobile/feed_new/data/streams/%s/ipad/%s_%s.json";
	
	/**
	 * The game you are looking up
	 */
	private final String id;

	public VODRequest(Game game) {
		this.id = game.getId();
	}
	
	public VODRequest(String id) {
		this.id = id;
	}

	@Override
	public GameInformation request(ExecutorService service, Gson gson) throws IOException {
		String season_id = id.substring(0, 4);
		String part_1 = id.substring(4, 6);
		String part_2 = id.substring(6);
		URL url = new URL(String.format(UNFORMATTED_URL, season_id, part_1, part_2));
		Reader reader = new InputStreamReader(url.openStream());
		return gson.fromJson(reader, GameInformation.class);
	}

}
