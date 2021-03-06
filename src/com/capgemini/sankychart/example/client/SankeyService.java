package com.capgemini.sankychart.example.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("sankey")
public interface SankeyService extends RemoteService {
	public SankeyModel csvRead(String path);
}
