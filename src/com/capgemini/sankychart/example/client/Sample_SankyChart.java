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
	private SankeyServiceAsync service = (SankeyServiceAsync) GWT.create(SankeyService.class);

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

		AsyncCallback<SankeyModel> callBack = new AsyncCallback<SankeyModel>() {

			@Override
			public void onSuccess(SankeyModel result) {

				String[] from = result.getFrom();
				String[] to = result.getTo();
				int[] weight = result.getWeight();
				String[] from_to = result.getFrom_to();

				// Prepare the data
				DataTable data = DataTable.create();
				data.addColumn(ColumnType.STRING, "From");
				data.addColumn(ColumnType.STRING, "To");
				data.addColumn(ColumnType.NUMBER, "Count");

				for (int i = 0; i < from.length; i++) {
					for (int j = 0; j < to.length; j++) {
						String str = from[i] + to[j];
						for (int c = 0; c < from_to.length; c++) {
							if (str.equals(from_to[c])) {
								data.addRow(from[i], to[j], weight[c]);
							}
						}

					}
				}
				chart.draw(data);
				chart.setWidth("700px");
				chart.setHeight("400px");
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("ERROR!!");
			}
		};

		service.csvRead("..\\Book.csv", callBack);

	}

	public void onModuleLoad() {
		initialize();
	}
}