package com.capgemini.sankychart.example.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void csvRead(String path, AsyncCallback<CSVmodel> callback) throws IllegalArgumentException;
}
