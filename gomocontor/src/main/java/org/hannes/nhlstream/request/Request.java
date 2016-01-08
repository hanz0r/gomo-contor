package org.hannes.nhlstream.request;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.google.gson.Gson;

public interface Request<T> {

	/**
	 * 
	 * @param url
	 * @param type
	 * @return
	 */
	T request(ExecutorService service, Gson gson) throws IOException;

}