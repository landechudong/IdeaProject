//后厨管理模块下的业务的方法
package Test.service;


import Test.data.CookData;
import Test.util.CookUtil;

import java.util.Scanner;

public class CookService {
    public static CookData[] data;
    Scanner scanner = new Scanner(System.in);
    private static  int cooknum = 2;//菜的种类
    private static int cookorder = 2;//菜的序号id

    public CookService(int num) {
        data = new CookData[num];
        data[0] = new CookData(1, "鸡丁", 29, "微辣", 50);
        data[1] = new CookData(2, "豆腐", 19, "麻辣", 50);
    }


    /**
     * （仓库管理模块和菜单模块）中的查看菜的明细的业务方法
     *
     * @return 数组 菜的明细
     */
    public CookData[] bank() {
        //System.out.println("共计" + cooknum + "条数据");
        return data;
    }

    /**
     * （仓库模块）中以id的方式（炒菜）的业务方法
     *
     * @param id  菜的id
     * @param num 增加的库存数
     * @return 库存是否添加成功的boolean
     */
    public boolean addStore(int id, int num) {
        int temp = -1;
        for (int i = 0; i < cooknum; i++) {
            if (id == data[i].getId()) {
                temp = i;
                break;
            }
        }
        if (temp == -1) {
            return false;
        }
        data[temp].setCount(data[temp].getCount() + num);
        return true;
    }

    /**
     * （仓库管理模块）中以id的方式的（出菜）的业务方法
     *
     * @param id  菜的id
     * @param num 减少的库存数
     */
    public void reduce(int id, int num) {
        int temp = -1;
        for (int i = 0; i < cooknum; i++) {
            if (id == data[i].getId()) {
                temp = i;
                break;
            }
        }
        if (temp == -1) {
            System.out.println("------未找到该菜品，出菜失败------");
            return;
        }
        if (data[temp].getCount() - num < 0) {
            System.out.println("------库存不足，出菜失败------");
            return;
        }
        data[temp].setCount(data[temp].getCount() - num);
        System.out.println("------库存减少成功，已出菜------");
    }

    /**
     * 以名字和数量的方式来减少库存，用于订单生效的时候减少库存
     */

public void byNameReduce(String name, int num) {
    int temp = -1;
    for (int i = 0; i < cooknum; i++) {
        if (name.equals( data[i].getName())) {
            temp = i;
            break;
        }
    }

    data[temp].setCount(data[temp].getCount() - num);
}

    /**
     * 通过顾客点的菜名找到该菜在菜单中的序号
     * @param name 顾客点菜的菜名
     * @return 该菜在菜单中的序号
     */
    public int byName(String name) {
        int temp = -1;
    for (int i = 0; i < cooknum; i++) {
        if (name.equals(data[i].getName())) {
            temp = i;
            break;
        }
    }
    return temp;
}

    /**
     * 通过id号找到该菜品
     *
     * @param id 菜品的id号
     * @return 菜品对象或null
     */
    public CookData search(int id) {
        int temp = -1;
        for (int i = 0; i < cooknum; i++) {
            if (id == data[i].getId()) {
                temp = i;
                break;
            }
        }
        if (temp == -1) {
            return null;
        }
        return data[temp];
    }

    /**
     * （菜单模块）中（删除特色菜）的业务方法
     *
     * @param id 要删除的菜的编号
     * @return 是否删除成功的boolean
     */
    public boolean deleted(int id) {
        int temp = -1;
        for (int i = 0; i < cooknum; i++) {
            if (id == data[i].getId()) {
                temp = i;
                break;
            }
        }
        if (temp == -1) {
            return false;
        }
        for (int i = temp; i < cooknum - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--cooknum] = null;
        return true;
    }

    /**
     * （菜单模块）中（增加特色菜）的有业务方法
     *
     * @param d 增加的菜对象
     */
    public void add(CookData d) {
        if (cooknum == data.length) {
            CookData[] data1 = new CookData[data.length + 1];
            for (int i = 0; i < cooknum; i++) {
                data1[i] = data[i];
            }
            data1[cooknum++] = d;
            d.setId(++cookorder);
            data = data1;
            System.out.println("------增加特色菜成功------");
            return;
        }
        data[cooknum++] = d;
        d.setId(++cookorder);
        System.out.println("------增加特色菜成功------");

    }

    /**
     * 检查在（添加特色菜）的时候（菜名是否重复）的业务方法
     *
     * @param name 要添加的菜名
     * @return 独一无二的菜名
     */
    public String singleName(String name) {
        int temp = -1;
        while (true) {
            for (int i = 0; i < cooknum; i++) {
                if (name.equals(data[i].getName())) {
                    temp = i;
                    break;
                }
            }
            if (temp == -1) {
                return name;
            }
            System.out.println("名字重复了，请重新输入");
            System.out.print("菜名：");
            name = scanner.next();
            temp = -1;
        }
    }

    /**
     *从键盘输入决定删除的订单编号
     */
    public int numscanf() {
        System.out.println("请输入要处理的订单编号");
        int num = CookUtil.readInt();
        return num;
    }

}
