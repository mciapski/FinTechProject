import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Entity
@Table(name="cdr")
public class Company {

  @Id
  private Date Date_stock;
  private double opening;
  private double lowest;
  private double closing;
  private double volume;

  public Company(Date date_stock, double opening, double lowest, double closing, double volume) {
    Date_stock = date_stock;
    this.opening = opening;
    this.lowest = lowest;
    this.closing = closing;
    this.volume = volume;
  }

  public Company() {
  }

  public Date getDate_stock() {
    return Date_stock;
  }

  public double getOpening() {
    return opening;
  }

  public double getLowest() {
    return lowest;
  }

  public double getClosing() {
    return closing;
  }

  public double getVolume() {
    return volume;
  }

  @Override
  public String toString() {
    return "Company{" +
        "Date_stock=" + Date_stock +
        ", opening=" + opening +
        ", lowest=" + lowest +
        ", closing=" + closing +
        ", volume=" + volume +
        '}';
  }
}
