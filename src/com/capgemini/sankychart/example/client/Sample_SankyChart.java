package com.capgemini.sankychart.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.sankey.Sankey;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sample_SankyChart implements EntryPoint {

	private Sankey chart;
	private GreetingServiceAsync service = (GreetingServiceAsync) GWT.create(GreetingService.class);

	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.SANKEY);
		chartLoader.loadApi(new Runnable() {
			public void run() {
				// Create and attach the chart

				chart = new Sankey();
				RootPanel.get().add(chart);
				draw();

			}
		});
	}

	private void draw() {
		AsyncCallback<CSVmodel> callBack = new AsyncCallback<CSVmodel>() {

			@Override
			public void onSuccess(CSVmodel result) {

				String[] from = result.getFrom();
				String[] to = result.getTo();
				int[] weight = result.getWeight();

				// Prepare the data
				DataTable data = DataTable.create();
				data.addColumn(ColumnType.STRING, "From");
				data.addColumn(ColumnType.STRING, "To");
				data.addColumn(ColumnType.NUMBER, "Weight");

				for (int i = 0; i < from.length; i++) {
					for (int j = 0; j < to.length; j++) {
//						for (int c = 0; c < weight.length; c++) {
							data.addRow(from[i], to[j], weight[j]);
//						}
					}
				}
				chart.draw(data);
				chart.setWidth("700px");
				chart.setHeight("400px");
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("ERROR");
			}
		};

		service.csvRead("C:\\Users\\smaringa\\Documents\\saketh workspace\\sankyChart-poc-master\\Book3.csv", callBack);

		// Prepare the data

//	  DataTable data = DataTable.create(); data.addColumn(ColumnType.STRING,
//		  "From"); data.addColumn(ColumnType.STRING, "To");
//		  data.addColumn(ColumnType.NUMBER, "Weight");

		/*
		 * data.addRow("TSTR", "Non SCR", 20); data.addRow("TSTR", "SCR", 20);
		 * 
		 * data.addRow("SCR", "cancelled", 10); data.addRow("SCR", "Convert to SRO",
		 * 10); data.addRow("SCR", "Down Point", 10); data.addRow("SCR", "Truck roll",
		 * 10);
		 * 
		 * data.addRow("Non SCR", "Truck Roll", 20); data.addRow("Non SCR", "Cancelled",
		 * 20);
		 * 
		 * data.addRow("cancelled", "Truck Roll", 6); data.addRow("cancelled",
		 * "Cancelled", 6);
		 * 
		 * data.addRow("Truck roll", "Truck Roll", 6); data.addRow("Truck roll",
		 * "Cancelled", 6);
		 * 
		 * data.addRow("Down Point", "Truck Roll", 6); data.addRow("Down Point",
		 * "Cancelled", 6);
		 * 
		 * data.addRow("Convert to SRO", "Truck Roll", 6); data.addRow("Convert to SRO",
		 * "Cancelled", 6);
		 */

		// Draw the chart

	}

	public void onModuleLoad() {
		initialize();
	}
}