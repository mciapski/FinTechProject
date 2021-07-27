import javax.persistence.*;
import java.util.List;

public class CompanyDemo {
  public static void main(String[] args) {
    GetDataFromH getDataFromH = new GetDataFromH();
    getDataFromH.getAllPrices();
    getDataFromH.showData(getDataFromH.getAllPrices());
  }

}
