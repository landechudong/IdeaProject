//顾客模块下的业务的方法
package Test.service;

import Test.data.CookData;
import Test.data.CustomerData;
import Test.data.OrderData;
import Test.face.CookStore;

import java.util.Scanner;


public class CustomerService {
    Scanner scanner = new Scanner(System.in);
    CookStore cookstore = new CookStore();
    private static CustomerData[] data1;
    private static int customernum = 0;//订单的数量
    private static int customerorder = 0;//订单的序号

    public CustomerService(int num) {
        data1 = new CustomerData[num];
    }

    public static CustomerData[] orderInformation() {
        return data1;
    }


    public CustomerData order(CustomerData d1) {
        if (customernum == data1.length) {
            CustomerData[] d = new CustomerData[data1.length + 1];
            for (int i = 0; i < data1.length; i++) {
                d[i] = data1[i];
            }
            d[data1.length] = d1;

            customernum++;
            d1.setId(++customerorder);
            data1 = d;
            return d1;
        }
        data1[customernum++] = d1;
        d1.setId(++customerorder);
        return d1;

    }

    public CookData rightName(String name) {
        CookData[] d = cookstore.cookservice.bank();
        while (true) {
            int temp = -1;
            int i = 0;
            for (i = 0; i < d.length; i++) {
                if (d[i] != null) {
                    if (name.equals(d[i].getName())) {
                        temp = i;
                        break;
                    }
                }
            }
            if (temp == i) {
                return d[temp];
            }
            System.out.println("菜名输入有误，请重新输入");
            name = scanner.next();
        }
    }

    public void dealOrder(String code) {
        int temp = -1;
        CustomerData[] d = orderInformation();
        for (int i = 0; i < customernum; i++) {
            String code1 = d[i].getCode().toString();
            if (code.equals(code1)) {
                temp = i;
                break;
            }
        }
        if (temp == -1) {
            System.out.println("------没有该订单编号，无法完成处理------");
            return;
        }
        //在厨师处理订单钱应该对顾客的钱和库存做出一个判断
        double money = d[temp].getAllmoney();
        if (CustomerData.getMoney() < money) {
            System.out.println("客户账户余额不足，订单失败");
            for (int i1 = temp; i1 < customernum - 1; i1++) {
                d[i1] = d[i1 + 1];
            }
            d[--customernum] = null;
            return;
        }

        OrderData[] o = d[temp].getOrderdata();
        for(int i = 0; i < o.length; i++){
            int id  = 0;
            if(o[i] != null){
                 id = cookstore.cookservice.byName(o[i].getName());
                if(o[i].getNum() > CookService.data[id].getCount()){
                    System.out.println("库存数量不足，订单处理失败");
                    for (int i1 = temp; i1 < customernum - 1; i1++) {
                        d[i1] = d[i1 + 1];
                    }
                    d[--customernum] = null;
                    return;
                }
            }
        }

        for (int i = 0; i < o.length; i++) {
            if (o[i] != null) {
                cookstore.cookservice.byNameReduce(o[i].getName(), o[i].getNum());
            }
        }

        for (int i = temp; i < customernum - 1; i++) {
            d[i] = d[i + 1];
        }

        d[--customernum] = null;
        //处理完订单后收取的钱
        CookData.setMoney(CookData.getMoney() + money);
        CustomerData.setMoney(CustomerData.getMoney() - money);
        System.out.println("------订单处理成功------");
    }
}