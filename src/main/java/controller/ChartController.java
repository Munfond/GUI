package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.KolRanking;
import model.*;

import java.util.List;

public class ChartController {

    private final CsvReader csvReader;

    public ChartController() {
        this.csvReader = new CsvReader();
    }

    /**
     * 
     *
     * @param keyword 
     * @return 
     * @throws Exception 
     */
    public ObservableList<XYChart.Data<String, Number>> getChartData(String keyword) throws Exception {
   
        List<KolRanking> rankings = csvReader.readKolData(keyword);

   
        ObservableList<XYChart.Data<String, Number>> dataList = FXCollections.observableArrayList();
        for (KolRanking ranking : rankings) {
            dataList.add(new XYChart.Data<>(ranking.getUsername(), ranking.getRankingPoint()));
        }


        // dataList.sort((a, b) -> Double.compare(b.getYValue().doubleValue(), a.getYValue().doubleValue()));
        return FXCollections.observableArrayList(dataList.subList(0, Math.min(10, dataList.size())));
    }
}
