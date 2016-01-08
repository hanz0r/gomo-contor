package org.hannes.nhlstream.request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.hannes.nhlstream.model.Game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Looks up the games for a given season
 * 
 * @author brock
 *
 */
public class SearchRequest extends AbstractRequest<List<Game>> {

	/**
	 * The unformatted string to the location of the games played for the specified season
	 */
	private static final String UNFORMATTED_URL = "http://live.nhl.com/GameData/SeasonSchedule-%s.json";

	/**
	 * The season requested for lookup
	 */
	private final String season;

	public SearchRequest(String season) {
		this.season = season;
	}

	@Override
	public List<Game> request(ExecutorService service, Gson gson) throws IOException {
		URL url = new URL(String.format(UNFORMATTED_URL, season));
		Reader reader = new InputStreamReader(url.openStream());
		return gson.fromJson(reader, new TypeToken<List<Game>>(){}.getType());
	}

}
