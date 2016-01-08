package org.hannes.nhlstream.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hannes.nhlstream.GomoContor;
import org.hannes.nhlstream.model.Game;
import org.hannes.nhlstream.model.GameInformation;
import org.hannes.nhlstream.request.SearchRequest;
import org.hannes.nhlstream.request.VODRequest;
import org.junit.Test;

/**
 * Various tests for the Gomocontor class
 * @author brock
 *
 */
public class GomoContorTest {

	private final GomoContor application = new GomoContor();

	@Test
	public void test_season() throws InterruptedException, ExecutionException {
		List<Game> games = application.get(new SearchRequest(GomoContor.getSeason("20160107"))).get();
		games.forEach(game -> {
			String date = game.getDate().split(" ")[0];
			assertEquals(getSeason(date), GomoContor.getSeason(date));
		});
	}

	@Test
	public void test_search() throws InterruptedException, ExecutionException {
		List<Game> games = application.get(new SearchRequest(GomoContor.getSeason("20160107"))).get();
		assertNotNull(games);
		assertEquals(games.size(), 1230);
	}
	
	@Test
	public void test_vod() throws InterruptedException, ExecutionException {
		List<Game> games = application.get(new SearchRequest(GomoContor.getSeason("20160107"))).get();
		Game ranger_game = games.stream().filter(game -> game.getHomeTeam().equals("NYR")).findAny().get();
		GameInformation information = application.get(new VODRequest(ranger_game)).get();
		assertNotNull(information.getPlatform());
		assertNotNull(information.getPlatform().getHome());
		assertNotNull(information.getPlatform().getAway());
	}
	
	/**
	 * Credits go to https://bitbucket.org/ntyler92/
	 * 
	 * @param date
	 * @return
	 */
    public String getSeason(String date) {
        String year, month;
        if (!date.contains("/")) {
            month = date.substring(4, 6);
            year = date.substring(0, 4);
        } else {
            year = date.substring(6, date.length());
            month = date.substring(0, 2);
        }

        int y = Integer.parseInt(year), m = Integer.parseInt(month);

        if (m >= 1 && m <= 8) {
            y--;
        }
        m = y++;
        return "" + m + y;
    }
	
}