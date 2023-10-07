//（后厨管理模块）下的（订单管理）的主菜单页面
package Test.face;

import Test.data.CustomerData;

import java.util.UUID;

import java.util.Scanner;

public class CookOrder {

    CookStore cookstore = new CookStore();
    Scanner scanner = new Scanner(System.in);
    CustomerFace customerface = new CustomerFace();

    /**
     * 后厨管理中的订单管理中输出订单的界面方法
     */
    public void orderManage() {
        CustomerData[] d = customerface.customerservice.orderInformation();
        for (int i = 0; i < d.length; i++) {
            if (d[i] != null) {
                System.out.println(d[i]);
            }
        }
        System.out.println("请输入要处理的订单编号");
       String code = scanner.next();
        //删除订单编号的业务方法
        customerface.customerservice.dealOrder(code);
    }


}

