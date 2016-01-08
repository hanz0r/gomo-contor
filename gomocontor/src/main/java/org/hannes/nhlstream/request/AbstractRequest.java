package org.hannes.nhlstream.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the addListener and fireCompleted methods
 * 
 * @author brock
 *
 * @param <T>
 */
abstract class AbstractRequest<T> implements Request<T> {

	/**
	 * The collection of listeners
	 */
	private final List<RequestListener<T>> listeners = new ArrayList<>();

	@Override
	public void addListener(RequestListener<T> listener) {
		listeners.add(listener);
	}

	@Override
	public void fireCompleted(T result) {
		listeners.forEach(listener -> listener.requestCompleted(AbstractRequest.this, result));
	}

}