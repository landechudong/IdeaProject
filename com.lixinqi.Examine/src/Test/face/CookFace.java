//后厨管理模块的主菜单界面（厨师可以查看仓库，菜单，订单和余额情况并进行处理）
package Test.face;


import Test.util.CookUtil;

public class CookFace {
    CookStore cookstore = new CookStore();
    CookMenu cookmenu = new CookMenu();
    CookOrder cookorder = new CookOrder();
    CustomerFace customerface = new CustomerFace();
    CookBalance cookbalance = new CookBalance();
    boolean bool = true;


    public void mainCook() {
        do {
            System.out.println("------后厨管理模块------");
            System.out.println("1.仓  库  管  理 ");
            System.out.println("2.菜  单  管  理 ");
            System.out.println("3.订  单  管  理 ");
            System.out.println("4.查  看  余  额 ");
            System.out.println("5.返 回 上 一 级 ");
            System.out.println("请输入你的选择1 -- 5");
            int num = CookUtil.readInt();
            switch (num) {
                case 1:
                    cookstore.mainStore();
                    break;
                case 2:
                    cookmenu.mainMenu();
                    break;
                case 3:
                   cookorder.orderManage();
                    break;
                case 4:
                    cookbalance.balanceAll();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("输入的数字有误");

            }
        } while (bool);
    }
}
