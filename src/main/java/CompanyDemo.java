import Companies.ING;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class CompanyDemo {
  public static void main(String[] args) {

    GetDataFromH getDataFromH = new GetDataFromH();
    getDataFromH.IngPricesListToChart().get(1);
    getDataFromH.IngDatesListToChart().get(1);
    getDataFromH.showData_ING(getDataFromH.storingList);
    PriceChartWithoutVolume priceChartWithoutVolume = new PriceChartWithoutVolume("Test","Test");
    priceChartWithoutVolume.showChart();

  }

}
