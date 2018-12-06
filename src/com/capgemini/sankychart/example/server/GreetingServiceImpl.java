package com.capgemini.sankychart.example.server;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.sankychart.example.client.CSVmodel;
import com.capgemini.sankychart.example.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import au.com.bytecode.opencsv.CSVReader;

/**
 * The server-side implementation of the RPC service.
 */

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public CSVmodel csvRead(String path) {
		List<String> src = new ArrayList<String>();
		List<String> dest = new ArrayList<String>();
		List<String> src_dest = new ArrayList<String>();
		try {
			String strFile = path;
			CSVReader reader = new CSVReader(new FileReader(strFile));
			String[] nextLine;
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
//			System.out.println(nextLine[1] + " " + nextLine[2]);
				src.add(nextLine[1]);
				dest.add(nextLine[2]);
				src_dest.add(nextLine[1] + nextLine[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] count = src_dest.toArray(new String[src_dest.size()]);
		Set<String> uniquCount = new HashSet<String>();

		for (int i = 0; i < count.length; i++) {
			if (!uniquCount.add(count[i])) {
				count[i] = "Duplicate";
			}
		}

//		System.out.println(uniquCount);

		String[] from = src.toArray(new String[src.size()]);
		Set<String> uniquFrom = new HashSet<String>();

		for (int i = 0; i < from.length; i++) {
			if (!uniquFrom.add(from[i])) {
				from[i] = "Duplicate";
			}
		}
//		System.out.println(uniquFrom);
		String[] to = dest.toArray(new String[dest.size()]);
		Set<String> uniquTo = new HashSet<String>();

		for (int i = 0; i < to.length; i++) {
			if (!uniquTo.add(to[i])) {
				to[i] = "Duplicate";
			}
		}
//		System.out.println(uniquTo);

		String[] finalCount = uniquCount.toArray(new String[uniquCount.size()]);
		String[] finalFrom = uniquFrom.toArray(new String[uniquFrom.size()]);
		String[] finalTo = uniquTo.toArray(new String[uniquTo.size()]);

//		System.err.println(Arrays.toString(finalCount));

		int[] totalCount = new int[finalCount.length];
		int[] fromCount = new int[finalFrom.length];
		int[] toCount = new int[finalTo.length];

		for (int c = 0; c < finalCount.length; c++) {
			int totalCounter = Collections.frequency(src_dest, finalCount[c]);
			totalCount[c] = totalCounter;
		}

		for (int i = 0; i < finalFrom.length; i++) {
			int fromCounter = Collections.frequency(src, finalFrom[i]);
			fromCount[i] = fromCounter;

		}
		for (int j = 0; j < finalTo.length; j++) {
			int toCounter = Collections.frequency(dest, finalTo[j]);
			toCount[j] = toCounter;

		}

		List<String[]> csvList = new ArrayList<String[]>();
		csvList.add(finalFrom);
		csvList.add(finalTo);
		csvList.add(finalCount);

//		System.out.println(csvList.to);
//		Object[] obj = new Object[] { finalFrom, finalTo, totalCount };

		CSVmodel model = new CSVmodel();
		model.setFrom(finalFrom);
		model.setTo(finalTo);
		model.setWeight(totalCount);
		model.setFrom_to(finalCount);
		System.out.println(model.getFrom().toString());
		return model;
	}
}
