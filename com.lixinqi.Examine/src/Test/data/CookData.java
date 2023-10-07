//厨师模块中应用的数据
package Test.data;

public class CookData {
    private int id;//菜品的编号
    private String name; //菜品的名字
    private double price;//菜品的价格
    private String taste; //菜品的口味
    private int count; //菜品的库存
    private static double money = 1000;//厨师的基本工资


    /**
     * 构造器给属性赋值
     * @param id 菜的编号
     * @param name 菜名
     * @param price 菜的价格
     * @param taste 菜的口味
     * @param count 菜的库存
     */
    public CookData(int id, String name, double price, String taste, int count){
        this.id = id;
        this.name = name;
        this.price = price;
        this.taste = taste;
        this.count = count;
    }

    //下面是五个属性的get和set方法
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getTaste() {
        return taste;
    }

    public void setCount(int count) {
       this.count = count;
    }

    public  int getCount() {
        return count;
    }

    public static void setMoney(double money) {/////////
        CookData.money = money;
    }

    public static double getMoney() {///////////
        return money;
    }

    /**
     * 重写的toString方法统一输出的格式
     * @return 五个属性的统一的输出格式
     */
    public String toString(){
        return "id:" + id +
                "\t菜名:" + name +
                "\t菜价:" + price +
                "\t描述：" + taste +
                "\t库存：" + count;

    }
}
