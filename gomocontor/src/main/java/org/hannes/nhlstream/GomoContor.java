package org.hannes.nhlstream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.hannes.nhlstream.request.Request;

import com.google.gson.Gson;

public class GomoContor {

	/**
	 * The executor service
	 */
	private final ExecutorService service = Executors.newCachedThreadPool();
	
	/**
	 * The GSON object to parse all of the JSON requests/responses
	 */
	private final Gson gson = new Gson();

	/**
	 * 
	 * @param request
	 * @return
	 */
	public <T> Future<T> get(Request<T> request) {
		return service.submit(() -> {
			return request.request(service, gson);
		});
	}
	

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