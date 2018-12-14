package com.capgemini.sankychart.example.server;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.sankychart.example.client.SankeyModel;
import com.capgemini.sankychart.example.client.SankeyService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import au.com.bytecode.opencsv.CSVReader;

/**
 * The server-side implementation of the RPC service.
 */

public class SankeyServiceImpl extends RemoteServiceServlet implements SankeyService {

	private static final long serialVersionUID = 1L;

	@Override
	public SankeyModel csvRead(String path) {
		List<String> src = new ArrayList<String>();
		List<String> dest = new ArrayList<String>();
		List<String> src_dest = new ArrayList<String>();
		CSVReader reader;
		System.out.println(path);
		try {
			String strFile = path;
			reader = new CSVReader(new FileReader(strFile));
			String[] nextLine;
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
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

		String[] from = src.toArray(new String[src.size()]);
		Set<String> uniquFrom = new HashSet<String>();

		for (int i = 0; i < from.length; i++) {
			if (!uniquFrom.add(from[i])) {
				from[i] = "Duplicate";
			}
		}

		String[] to = dest.toArray(new String[dest.size()]);
		Set<String> uniquTo = new HashSet<String>();

		for (int i = 0; i < to.length; i++) {
			if (!uniquTo.add(to[i])) {
				to[i] = "Duplicate";
			}
		}

		String[] finalCount = uniquCount.toArray(new String[uniquCount.size()]);
		String[] finalFrom = uniquFrom.toArray(new String[uniquFrom.size()]);
		String[] finalTo = uniquTo.toArray(new String[uniquTo.size()]);

		int[] totalCount = new int[finalCount.length];

		for (int c = 0; c < finalCount.length; c++) {
			int totalCounter = Collections.frequency(src_dest, finalCount[c]);
			totalCount[c] = totalCounter;
		}

		SankeyModel model = new SankeyModel();
		model.setFrom(finalFrom);
		model.setTo(finalTo);
		model.setWeight(totalCount);
		model.setFrom_to(finalCount);
		System.out.println(model.getFrom().toString());
		return model;
	}
}
