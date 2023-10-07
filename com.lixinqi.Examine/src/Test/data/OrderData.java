//这是记录顾客点单使的菜名和数量的信息，方便库存的入库和出库
package Test.data;

public class OrderData {
    private String name;//顾客预定的菜品的名字
    private int num;//顾客预定的菜品的数量

    /**
     * 构造器
     * 目的是为了厨师中内存的出库于入库
     *
     * @param name 订单中顾客点的菜名
     * @param num  订单中顾客点的菜品的数量
     */

    public OrderData(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    /**
     * 重写toString方法，规范输出的形式
     * @return 名字和数量的输出的格式
     */
    public String toString() {
    return "name" + name +
            "num" + num;
    }
}