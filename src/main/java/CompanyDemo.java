import Companies.Choice;

import java.util.Scanner;

public class CompanyDemo {
  public static void main(String[] args) {

    GetDataFromH getDataFromH = new GetDataFromH();
    System.out.println("Welcome in FinTech application Version 1.0");
    System.out.println("Menu");
    System.out.println("What would you like to do ?");
    System.out.println(" Load data: ");
    System.out.println(" 1. To load ING data press 'ING'");
    System.out.println(" 1. To load CD data press 'CD'");
    System.out.println("Do you want see values ?");
    System.out.println("Press YES or NO");
    System.out.println("Do you want to see chart of your company?");
    System.out.println("Press YES or NO");


    Scanner scanner = new Scanner(System.in);
    System.out.println("What would you like to do ?");
    System.out.println(" Load data: ");
    System.out.println(" 1. To load ING data press 'ING'");
    System.out.println(" 1. To load CD data press 'CD'");
    String firstChoice = scanner.nextLine();
    if (firstChoice.equalsIgnoreCase("ing")) {
      getDataFromH.getAllPrices_ING();
      if (!getDataFromH.storingINGList.isEmpty()) {
        System.out.println("Data loaded");
      }
      System.out.println();
      System.out.println("Do you want see values ?");
      System.out.println("Press YES or NO");
      String secondChoice = scanner.nextLine();
      if (secondChoice.equalsIgnoreCase("yes")) {
        getDataFromH.showData_ING(getDataFromH.storingINGList);
      } else {
        System.out.println("You can skip to chart");
      }
      System.out.println("Do you want to see chart of your company?");
      System.out.println("Press YES or NO");
      String thirdChoice = scanner.nextLine();
      if (thirdChoice.equalsIgnoreCase("yes")) {
        PriceChartWithoutVolume ingChart=new PriceChartWithoutVolume("Test","Test",Choice.ING);
        ingChart.showChart(ingChart);
      } else {
        return;
      }
    } else if (firstChoice.equalsIgnoreCase("cd")) {
      getDataFromH.getAllPrices_CD();
      if (!getDataFromH.storingCDList.isEmpty()) {
        System.out.println("Data loaded");
      }
      System.out.println();
      System.out.println("Do you want see values ?");
      System.out.println("Press YES or NO");
      String secondChoice = scanner.nextLine();
      if (secondChoice.equalsIgnoreCase("yes")) {
        getDataFromH.showData_CD(getDataFromH.storingCDList);
      } else {
        System.out.println("You can skip to chart");
      }
      System.out.println("Do you want to see chart of your company?");
      System.out.println("Press YES or NO");
      String thirdChoice = scanner.nextLine();
      if (thirdChoice.equalsIgnoreCase("yes")) {
        PriceChartWithoutVolume cdChart = new PriceChartWithoutVolume("Historically prices", "CDProject",Choice.CDProject);
        cdChart.showChart(cdChart);
      } else {
        return;
      }

    }
//    getDataFromH.IngPricesListToChart().get(1);
//    getDataFromH.IngDatesListToChart().get(1);
//    getDataFromH.showData_ING(getDataFromH.storingINGList);
//    getDataFromH.getAllPrices_CD();
//    getDataFromH.CDPricesListToChart().get(1);
//    getDataFromH.showData_CD(getDataFromH.storingCDList);
////    PriceChartWithoutVolume priceChartWithoutVolume = new PriceChartWithoutVolume("Test","Test");
////    priceChartWithoutVolume.showChart();
//    PriceChartWithoutVolume priceChartWithoutVolume1 = new PriceChartWithoutVolume("CD", "CD");
//    priceChartWithoutVolume1.showChart();

  }

}
