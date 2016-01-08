package org.hannes.nhlstream.request;

/**
 * Listens to the request and gets called at certain Request milestones
 * 
 * @author brock
 *
 * @param <T>
 */
public interface RequestListener<T> {

	/**
	 * Called when the request is completed
	 * 
	 * @param request
	 * @param result
	 */
	void requestCompleted(Request<T> request, T result);

}