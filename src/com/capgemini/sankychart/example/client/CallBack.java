package com.capgemini.sankychart.example.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CallBack implements AsyncCallback<CSVmodel> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("RPC to sendEmail() failed.");
	}

	@Override
	public void onSuccess(CSVmodel result) {
		System.out.println(result);
	}

}
