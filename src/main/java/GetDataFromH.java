import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class GetDataFromH {
  public List<Company> getAllPrices(){
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cdr_julyPU");
    final EntityManager em = emf.createEntityManager();

    try {
      final TypedQuery<Company> query = em.createQuery(
          "FROM Company", Company.class);
      List<Company> resultList = query.getResultList();
      return resultList;
    }finally {
      emf.close();
    }

  }
  public void showData (List<Company> list){
    StringBuilder sb = new StringBuilder();
    String columns  = "Data        Opening Lowest Closing  Volume";
    System.out.println(columns);
    for (Company element: list) {
      sb.append(element.getDate_stock()).append("   ").append(element.getOpening()).append("  ")
          .append(element.getLowest()).append("  ").append(element.getClosing()).append("  ")
          .append(element.getVolume()).append("\n");
    }
    System.out.println(sb);
  }
}
