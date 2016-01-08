package org.hannes.nhlstream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.hannes.nhlstream.request.Request;

import com.google.gson.Gson;

/**
 * Like a container class for the application
 * 
 * @author brock
 *
 */
public class GomoContor {

	/**
	 * The pattern the input date must adhere to
	 */
	private static final String DATE_PATTERN = "[0-9]{8}";
	
	/**
	 * The executor service
	 */
	private final ExecutorService service = Executors.newCachedThreadPool();
	
	/**
	 * The GSON object to parse all of the JSON requests/responses
	 */
	private final Gson gson = new Gson();

	/**
	 * Executes a request
	 * 
	 * @param request
	 * @return
	 */
	public <T> Future<T> get(Request<T> request) {
		return service.submit(() -> {
			T result = request.request(service, gson);
			request.fireCompleted(result);
			return result;
		});
	}
	
	/**
	 * Credits go to https://bitbucket.org/ntyler92/
	 * 
	 * @param date
	 * @return
	 */
	@Deprecated
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
    
    /**
     * 
     * @param date
     * @return
     */
    public static String toSeason(String date) {
    	if (date == null || !date.matches(DATE_PATTERN)) {
    		throw new IllegalArgumentException(String.format("date must be of format yyyyddMM - %s", date));
    	}
    	int year = Integer.parseInt(date.substring(0, 4));
    	int month = Integer.parseInt(date.substring(4, 6));
    	return month >= 1 && month <= 8 ? String.format("%d%d", year - 1, year) : String.format("%d%d", year, year + 1);
    }

}