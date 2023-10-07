//（后厨管理模块）下的（仓库管理）的菜单界面（库存，炒菜，出菜）
package Test.face;


import Test.data.CookData;
import Test.service.CookService;
import Test.util.CookUtil;

public class CookStore {
    boolean bool = true;
    public CookService cookservice = new CookService(3);

    /**
     * （仓库管理）中的菜的（库存）的菜单方法
     */
    public void cookBank(){
        System.out.println("------食品明细------");
        CookData[] d = cookservice.bank();
        for(int i = 0; i < d.length; i++){
           if(d[i] != null) {
               System.out.println(d[i]);
           }
        }
        System.out.println("------食品明细------");
    }

    /**
     * （仓库模块）中的以id的方式（炒菜）的菜单方法
     */
    public void cookAdd(){
        int num = 0;
        System.out.println("------炒 菜（即增加库存）------");
        System.out.print("菜的序号：");
        int id = CookUtil.readInt();
        System.out.print("炒菜的数量：");
        do {
             num = CookUtil.readInt();
            if(num > 0){
                break;
            }
            System.out.println("炒菜的数量输入错误，请重新输入");
        }while(true);
        if(cookservice.addStore(id,num)){
            System.out.println("------库存添加成功------");
        }else{
            System.out.println("未找到该菜品，库存添加失败");
        }
    }

    /**
     * （仓库管理的模块）中以id的方式（出菜）的菜单方法
     */
    public void cookReduce(){
        int num = 0;
        System.out.println("------出 菜（即为减少库存）------");
        System.out.print("菜的序号：");
        int id = CookUtil.readInt();
        System.out.print("出菜的数量：");
        do {
             num = CookUtil.readInt();
            if(num > 0){
                break;
            }
            System.out.println("出菜的数量输入错误，请重新输入");
        }while(true);
        char c = CookUtil.choice();
        if(c == 'N'){
            System.out.println("你已经放弃出菜，减少库存失败");
            return;
        }
        //这里要用的是出菜的业务方法
        cookservice.reduce(id,num);
    }

    /**
     * （仓库管理）的（主菜单）界面
     */
    public void mainStore() {
        do {
            System.out.println("仓 库 管 理 模 块");
            System.out.println("1.库         存");
            System.out.println("2.炒         菜");
            System.out.println("3.出         菜");
            System.out.println("4.返 回 上 一 级");
            System.out.println("请输入的选择1 --4");
            int num = CookUtil.readInt();
            switch(num){
                case 1:
                    cookBank();
                    break;
                case 2:
                    cookAdd();
                    break;
                case 3:
                    cookReduce();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("你输入的数字有误");
            }
        } while (bool);
    }
}
