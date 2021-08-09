import Companies.CD;
import Companies.Choice;
import Companies.ING;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GetDataFromH<T> {
  List<ING> storingINGList = getAllPrices_ING();
  List<Date> storingINGDateList = IngDatesListToChart();
  List<CD> storingCDList = getAllPrices_CD();
  List<Date> storingCDDateList = CDDatesListToChart();


  public List getAllPrices_CD() {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cdr_julyPU");
    final EntityManager em = emf.createEntityManager();
    try {
      final TypedQuery<CD> CD = em.createQuery(
          "FROM CD", CD.class);
      List<CD> CDresultList = CD.getResultList();
      return CDresultList;
    } finally {
      emf.close();
    }
  }

  public void showData_CD(List<CD> list) {
    StringBuilder sb = new StringBuilder();
    String columns = "Data        Opening Lowest Closing  Volume";
    System.out.println(columns);
    for (CD element : list) {
      sb.append(element.getDate_stock()).append("   ").append(element.getOpening()).append("  ")
          .append(element.getLowest()).append("  ").append(element.getClosing()).append("  ")
          .append(element.getVolume()).append("\n");
    }
    System.out.println(sb);
  }

  public List<Double> CDPricesListToChart() {
    List<Double> priceIngList = storingCDList.stream().map(CD::getClosing).collect(Collectors.toList());
    return priceIngList;
  }

  public List<Date> CDDatesListToChart() {
    List<Date> dateCDList = storingCDList.stream().map(CD::getDate_stock).collect(Collectors.toList());
    return dateCDList;
  }

  public List<ING> getAllPrices_ING() {
    final EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("cdr_julyPU");
    final EntityManager em2 = emf2.createEntityManager();
    try {
      final List<ING> INGresultList = em2.createQuery(
          "FROM ING", ING.class).getResultList();

      return INGresultList;

    } finally {
      emf2.close();
    }
  }

  public List<Double> IngPricesListToChart() {
    List<Double> priceIngList = storingINGList.stream().map(ING::getClosing).collect(Collectors.toList());
    return priceIngList;
  }

  public List<Date> IngDatesListToChart() {
    List<Date> priceIngList = storingINGList.stream().map(ING::getDate_stock).collect(Collectors.toList());
    return priceIngList;
  }

  public void showData_ING(List<ING> list) {
    StringBuilder sb = new StringBuilder();
    String columns = "Data        Opening Lowest Closing  Volume";
    System.out.println(columns);
    for (ING element : list) {
      sb.append(element.getDate_stock()).append("   ").append(element.getOpening()).append("  ")
          .append(element.getLowest()).append("  ").append(element.getClosing()).append("  ")
          .append(element.getVolume()).append("\n");
    }
    System.out.println(sb);
  }
}
