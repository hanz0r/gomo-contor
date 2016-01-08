package org.hannes.nhlstream.test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hannes.nhlstream.GomoContor;
import org.hannes.nhlstream.model.Game;
import org.hannes.nhlstream.model.GameInformation;
import org.hannes.nhlstream.request.SearchRequest;
import org.hannes.nhlstream.request.VODRequest;
import org.junit.Test;

/**
 * 
 * @author brock
 *
 */
public class NHLStreamTest {

	private final GomoContor application = new GomoContor();

	@Test
	public void test_search() throws InterruptedException, ExecutionException {
		List<Game> games = application.get(new SearchRequest(application.getSeason("20160107"))).get();
		System.out.printf("%d games found%n", games.size());
		System.out.println("example: " + games.stream().findAny().get());
	}
	
	@Test
	public void test_vod() throws InterruptedException, ExecutionException {
		List<Game> games = application.get(new SearchRequest(application.getSeason("20160107"))).get();
		Game ranger_game = games.stream().filter(game -> game.getHomeTeam().equals("NYR")).findAny().get();
		GameInformation information = application.get(new VODRequest(ranger_game)).get();
		System.out.println(information);
	}

}