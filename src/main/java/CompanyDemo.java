import javax.persistence.*;
import java.util.List;

public class CompanyDemo {
  public static void main(String[] args) {
    System.out.println(getAllPrices());
  }
  public static List<Company> getAllPrices(){
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cdr_julyPU");
    final EntityManager em = emf.createEntityManager();

    try {
      final TypedQuery<Company> query = em.createQuery(
          "FROM Company", Company.class);
      return query.getResultList();
    }finally {
      emf.close();
    }

  }
}
