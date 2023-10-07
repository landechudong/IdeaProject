//顾客模块下的总的菜单方法
package Test.face;


import Test.data.CookData;
import Test.data.CustomerData;
import Test.data.OrderData;
import Test.service.CustomerService;
import Test.util.CookUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class CustomerFace {

 OrderData[] o1 = new OrderData[101];
    private int num1 = 0;
    Scanner scanner = new Scanner(System.in);
    CookStore cookstore = new CookStore();
  public CustomerService customerservice = new CustomerService(2);
    boolean bool = true;
    /**
     * （顾客模块）下的（查看菜单）的菜单方法
     */
    public void lookMenu() {
        System.out.println("------食品列表------");
        CookData[] d = cookstore.cookservice.bank();
        for (int i = 0; i < d.length; i++) {
            if (d[i] != null) {
                System.out.println(d[i]);
            }
        }
        System.out.println("------食品列表------");
    }

    /**
     * （顾客模块）下的（点菜）菜单方法
     */
    public void orderMenu(){
        UUID code = UUID.randomUUID();
        int ornum = 0;
        OrderData[] orderData = new OrderData[10];
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm");
        String name = "";
        String allname = "";
        double money;
        double allmoney = 0;
        int num = 0;
        while (true) {
            int temp = -1;
            System.out.println("------点菜------");
            System.out.print("你想选择的菜名");
            name = scanner.next();
            CookData c1 = customerservice.rightName(name);
            name = c1.getName();
            System.out.print("请选择相应的份数");
           do{
               num = CookUtil.readInt();
               if(num > 0){
                   break;
               }
               System.out.println("点菜的数量必须为大于零的数,请重新输入");
           }while(true);
         orderData[ornum++] = new OrderData(name,num);
            for(int i = 0; i < num1; i++){
                if(o1[i] != null){
                    if(name.equals(o1[i].getName())){
                        temp = i;
                    }
                }
            }
            if(temp == -1){
                o1[num1++] = new OrderData(name,num);
            }else{
                o1[temp].setNum(o1[temp].getNum() +num);
            }
            allmoney += c1.getPrice() * num;
            char c = CookUtil.choice1();
            if (c == 'N') {
                break;
            }
        }
        for(int i = 0; i < num1; i++){
            allname += o1[i].getName() + "(" + o1[i].getNum() +")";
        }
        CustomerData d1 = new CustomerData(0, allname, code, allmoney, sdf.format(date),orderData);
        for(int i = 0; i < num1; i++){
            o1[i] = null;
        }
        num1 = 0;

        //下面是点菜在业务上的方法
        CustomerData c = customerservice.order(d1);
        if (c != null) {
            System.out.println(c);
        }
        System.out.println("下单成功，等待厨师处理完该订单后会自动收取相应的金额");
        System.out.println("期待你的下次光临");
    }


    /**
     * 顾客模块下查看余额的菜单方法
     */
    public void balanceCustomer(){
        System.out.print("顾客账中的余额是：");
        System.out.println(CustomerData.getMoney());
    }

    /**
     * （顾客模块）下的（主菜单）方法
     */
    public void mainCustomer() {
        do {
            System.out.println("------顾客模块------");
            System.out.println("1.查   看   菜   单");
            System.out.println("2.点            菜");
            System.out.println("3.查   看   余   额");
            System.out.println("4.返  回  上  一  级");
            System.out.println("请输入你的选择1 -- 4 ");
            int num = CookUtil.readInt();
            switch (num) {
                case 1:
                    lookMenu();
                    break;
                case 2:
                  orderMenu();
                    break;
                case 3:
                    balanceCustomer();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("你输入的数字有误");
            }
        } while (bool);
    }
}
