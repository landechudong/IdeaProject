//顾客在点菜是订单中的数据
package Test.data;

import java.util.UUID;
import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;

public class CustomerData {
    UUID code = UUID.randomUUID();
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm");
    private int id;//订单编号
    private double allmoney;//顾客点单时应该支付的钱
    private String name;
    private int num;
    private static double money = 1000;//顾客卡中的存储的钱
    OrderData[] orderdata;


    /**
     * 构造器
     *
     * @param id       订单编号
     * @param allmoney 总的金额
     */
    public CustomerData(int id, String name,UUID code,double allmoney, String time,OrderData[] orderdata) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.allmoney = allmoney;
        time = sdf.format(date);
        this.orderdata = orderdata;////////
    }

    public UUID getCode() {
        return code;
    }

    //四个属性的set和get方法
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setName(String name){
        this.name = name;

    }
    public String getName(){
        return name;
    }

    public void setAllmoney(double allmoney) {
        this.allmoney = allmoney;
    }

    public double getAllmoney() {
        return allmoney;
    }

    public static void setMoney(double money) {
        CustomerData.money = money;
    }

    public static double getMoney() {
        return money;
    }
    public void setOrderdata(OrderData[]orderdata ){
        this.orderdata = orderdata;
    }
    public OrderData[] getOrderdata(){
        return orderdata;
    }

    /**
     * 重写的toString方法
     *
     * @return 订单的标准输出格式
     */
    public String toString() {
        return "id = " + id +
                " code = " + code +
                " name" + name +
                " allmoney = " + allmoney +
                " time = " + sdf.format(date);
    }
}
