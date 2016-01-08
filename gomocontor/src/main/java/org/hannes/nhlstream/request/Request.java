package org.hannes.nhlstream.request;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.google.gson.Gson;

/**
 * Represents a JSON request
 * 
 * @author brock
 *
 * @param <T>
 */
public interface Request<T> {

	/**
	 * 
	 * @param url
	 * @param type
	 * @return
	 */
	T request(ExecutorService service, Gson gson) throws IOException;

	/**
	 * Adds a request listener
	 * 
	 * @param listener
	 */
	void addListener(RequestListener<T> listener);

	/**
	 * Notifies all of the listeners the request has been completed
	 * 
	 * @param result
	 */
	void fireCompleted(T result);

}