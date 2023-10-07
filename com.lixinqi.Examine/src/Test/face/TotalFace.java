//总的菜单页面（选择身份（厨师或顾客）或退出）
package Test.face;


import Test.util.CookUtil;

import java.lang.Thread;
import java.util.Scanner;

public class TotalFace {
Thread t = new Thread();
    boolean bool = true;
    Scanner scanner = new Scanner(System.in);
    CookFace cookface = new CookFace();
    CustomerFace customerface = new CustomerFace();

    /**
     * 厨师的登录界面
     */
    public void cookLog() {
        int i = 3;
        final String SECRET_CODE = "123";
        final String ACCOUNT = "123";
        System.out.println("注意你有三次登入管理系统的机会");

        do {
            System.out.print("请输入账号：");
            String account = scanner.next();
            System.out.print("请输入密码：");
            String secretcode = scanner.next();
            if (!(ACCOUNT.equals(account))) {
                System.out.println("账号或密码错误，请重新输入，还有" + --i + "次机会");
                continue;
            }
            if (!(SECRET_CODE.equals(secretcode))) {
                System.out.println("账号或密码错误，请重新输入，还有" + --i + "次机会");
                continue;
            }

            System.out.println("登录成功，欢迎大厨们");
            cookface.mainCook();
            return;
        } while (i >= 1);
        if(i == 0){
            System.out.println("三次机会用完，休眠10秒，才能重新的进入系统");
            try{Thread.sleep(1000 * 10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("休眠结束,可以继续执行操作");
            System.out.println("---------------------");
        }
    }

    /**
     * 退出菜单界面的方法
     */
    public void exit(){
        char c = CookUtil.choice();
        if(c == 'Y'){
            bool = false;
            return;
        }
        System.out.println("------你选择放弃退出------");
    }


    /**
     * 系统的总的菜单界面
     */

        public void total() {
            label1:{
            do {
                System.out.println("欢迎来到起点炒个菜");
                System.out.println("1.厨    ~    师");
                System.out.println("2.顾    ~    客");
                System.out.println("3.退    ~    出");
                System.out.println("请选择代表你身份的数字1~3");
               int num = CookUtil.readInt();
                switch (num) {
                    case 1:
                        cookLog();
                        break;
                    case 2:
                        customerface.mainCustomer();
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("数字输入错误1 -- 3");
                }
            } while (bool);
            System.out.println("正在退出系统，谢谢光临");
        }
    }
}
