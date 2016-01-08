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
     * Gets the season according from the date string. The date string must be of
     * yyyyMMdd for it to be parsed correctly.
     * 
     * The NHL season runs approximately from october -> june so if the month is
     * between 1 and 8, it is in the first year of the season and if it is after,
     * it is from the next.
     * 
     * For example -> the 2015 - 2016 season runs from October 2015 until June 2016.
     * 
     * @param date
     * @return
     */
    public static String toSeason(String date) {
    	if (date == null || !date.matches(DATE_PATTERN)) {
    		throw new IllegalArgumentException(String.format("date must be of format yyyyMMdd - %s", date));
    	}
    	int year = Integer.parseInt(date.substring(0, 4));
    	int month = Integer.parseInt(date.substring(4, 6));
    	return month >= 1 && month <= 8 ? String.format("%d%d", year - 1, year) : String.format("%d%d", year, year + 1);
    }

}