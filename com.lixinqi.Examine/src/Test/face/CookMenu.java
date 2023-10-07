//
package Test.face;

import Test.data.CookData;
import Test.util.CookUtil;

import java.util.Scanner;



public class CookMenu {
    CookStore cookstore = new CookStore();

    Scanner scanner = new Scanner(System.in);
    boolean bool = true;

    /**
     * （菜单模块）中（查看菜单）的菜单方法
     */
    public void muneLook() {
        System.out.println("------食品列表------");
       CookData[] d =  cookstore.cookservice.bank();
       for(int i = 0 ; i < d.length; i++){
           if(d[i] != null){
               System.out.println(d[i]);
           }
       }
       System.out.println("------食品列表------");
    }

    /**
     * （菜单模块）中（修改菜单）的菜单方法
     */
    public void menuRevise(){
        double price = 0;
        System.out.println("请输入你要修改的菜品的id(-1放弃)");
        int id = CookUtil.readInt();
        if(id == -1){
            System.out.println("------你选择放弃修改菜单------");
            return;
        }
        CookData d = cookstore.cookservice.search(id);
        if(cookstore.cookservice.search(id) == null){
            System.out.println("------该菜品不存在，修改失败------");
            return;
        }
        System.out.print("菜名" +"(" + d.getName() + "):");
        String name = scanner.next();
        d.setName(name);
        System.out.print("菜价" + "(" + d.getPrice() + "):");
        do {
            price = CookUtil.readDouble();
            if(price > 0){
                break;
            }
            System.out.println("------菜价不能负，请重新输入------");
        }while(true);
        d.setPrice(price);
        System.out.print("描述" + "(" + d.getTaste() + "):");
        String taste = scanner.next();
        d.setTaste(taste);
        System.out.println("------菜单修改成功------");
    }

    /**
     * 在（菜单模块）中以id的方式（删除特色菜）的菜单方法
     */
    public void menuDeleted(){
        System.out.println("请选择待删除的特色菜的编号(-1退出)");
        int id = CookUtil.readInt();
        if(id == -1){
            System.out.println("------你放弃了删除特色菜------");
            return;
        }
       char c = CookUtil.choice();
        if(c == 'N'){
            System.out.println("------你放弃了删除特色菜------");
            return;
        }
        //删除菜的业务的方法
        if(cookstore.cookservice.deleted(id)){
            System.out.println("------特色菜删除成功------");
            return;
        }
        System.out.println("未找到相应的特色菜编号，删除失败");
    }
    /**
     * （菜单模块）中（增加特色菜）的菜单方法
     */
    public void menuAdd(){
        System.out.println("------新增菜单------");
        System.out.print("菜名：");
        double price = 0;
        String name = scanner.next();
         name = cookstore.cookservice.singleName(name);
        System.out.print("菜价：");
        do {
            price = CookUtil.readDouble();
            if(price > 0){
                break;
            }
            System.out.println("------菜价不能为负值，请重新输入------");
        }while(true);
        System.out.print("描述：");
        String taste = scanner.next();
        CookData d = new CookData(0,name,price,taste,0);
        //增加菜的业务的方法
        cookstore.cookservice.add(d);
    }


    /**
     * 菜单模块的主菜单方法
     */
    public void mainMenu() {
        do {
            System.out.println("------菜单模块------");
            System.out.println("1.查  看  菜  单  ");
            System.out.println("2.修  改  菜  单  ");
            System.out.println("3.删  除  特  色  菜");
            System.out.println("4.增  加  特  色  菜");
            System.out.println("5.返  回  上  一  级");
            System.out.println("请输入你的选择1 -- 5");
            int num = CookUtil.readInt();
            switch (num) {
                case 1:
                    muneLook();
                    break;
                case 2:
                    menuRevise();
                    break;
                case 3:
                    menuDeleted();
                    break;
                case 4:
                    menuAdd();
                    break;
                case 5:
                 return;
                default:
                    System.out.println("你输入的数字有误");
            }
        } while (bool);

    }
}
