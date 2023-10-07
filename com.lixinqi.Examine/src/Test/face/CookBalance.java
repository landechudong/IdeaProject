//在后厨管理的模块下查看余额的菜单方法
package Test.face;


import Test.data.CookData;

public class CookBalance {
    public void balanceAll() {
        System.out.print("厨师的工资是：");
      System.out.println(CookData.getMoney());
    }

}
