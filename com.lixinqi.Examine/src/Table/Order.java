package Table;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"all"})
public class Order {
    private int id;
    private String code;
    private Map hashMap = new HashMap();
    private int num;
    private double totalPrice;
    LocalDateTime localDateTime = LocalDateTime.now();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh时mm分ss秒");
    String formatter = dateTimeFormatter.format(localDateTime);

    public Order(int id, String code, Map hashMap, double totalPrice) {
        this.id = id;
        this.code = code;
        this.hashMap = hashMap;
        this.totalPrice = totalPrice;
    }

    public Map getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map hashMap) {
        this.hashMap = hashMap;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", code='" + code + '\'' +
                ", food='" + hashMap.entrySet() + '\'' +
                ", totalPrice=" + totalPrice +
                "  " + formatter ;
    }
}