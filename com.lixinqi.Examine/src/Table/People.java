package Table;

import javax.xml.soap.Node;
import java.util.*;

@SuppressWarnings({"all"})
public class People {
    public static int n9 = 1;
    public static int i = 1;
    public static double CustomerMoney = 1000;
    private static double ChefIncome = 0;
    private static Map hashMap = new HashMap();
    private static int h = 0;                   //hashMap 的长度
    private static String account = "admin";
    private static String password = "123";
    private static int time = 3;                //登录的次数
    public static LinkedList<Menu> linkedList = new LinkedList<>();
    public static ArrayList<Order> arrayList = new ArrayList<>();


    static {
        linkedList.add(new Menu(1, "宫保鸡丁", 9.0, "不辣巨好吃", 0));
        linkedList.add(new Menu(2, "彪哥小面", 8.0, "加豆干好吃", 0));
        linkedList.add(new Menu(3, "杂粮煎饼", 7.0, "加肉松好吃", 0));
    }



    //厨师登录页面
    public void ChefEntry() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎回厨!\n注意,你有三次登入管理系统的机会.");
        while (true) {
            System.out.println("请输入账号:");
            String str1 = scanner.next();
            System.out.println("请输入密码:");
            String str2 = scanner.next();
            if (!(str1.equals(account) && str2.equals(password))) {
                System.out.println("账号或者密码错误,请重新输入,还有" + (--time) + "次机会");
                if (time == 0) {
                    System.out.println("三次机会用完,休眠5秒才能重新进入系统...");
                    Thread.sleep(5000);
                    time = 3;
                }
                continue;
            } else {
                System.out.println("登录成功,欢迎大厨们.");
                break;
            }
        }
    }

    //厨师管理
    public void ChefManage() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------后 厨 管 理 模 块---------");
            System.out.println("1.仓库管理");
            System.out.println("2.菜单管理");
            System.out.println("3.订单管理");
            System.out.println("4.查看余额");
            System.out.println("5.返回上一级");
            System.out.println("请 输 入 您 的 选 择 :");
            int choose;
            while (true) {
                try {
                    String s1 = scanner.next();
                    choose = Integer.parseInt(s1);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("数字输入错误\t请在此处重新输入:");
                }
            }
            boolean loop3 = true;
            switch (choose) {
                case 1:
                    while (loop3) {
                        System.out.println("--------- 仓 库 摸 块 ---------");
                        System.out.println("1.查看库存");
                        System.out.println("2.炒菜");
                        System.out.println("3.扔菜");
                        System.out.println("4.返回上一级");
                        int choose1 ;
                        while (true) {
                            try {
                                String s1 = scanner.next();
                                choose1 = Integer.parseInt(s1);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("数字输入错误\t请在此处重新输入:");
                            }
                        }
                        switch (choose1) {
                            case 1:
                                System.out.println(" ");
                                show(linkedList);
                                break;
                            case 2:
                                System.out.println(" ");
                                show(linkedList);
                                boolean loop = true;
                                System.out.println("----- 新 增 库 存 -----");
                                while (loop) {
                                    System.out.print("你想炒的菜名:");
                                    String foodName = scanner.next();
                                    int i = 0;
                                    int num;
                                    for (i = 0; i < linkedList.size(); i++) {
                                        if (foodName.equals(linkedList.get(i).getFood())) {
                                            System.out.print("你想炒的数量:");
                                            while (true) {
                                                try {
                                                    String s1 = scanner.next();
                                                    num = Integer.parseInt(s1);
                                                    if(num < 0){
                                                        System.out.print("数量不能为负数,请重新输入:");
                                                        continue;
                                                    }
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("数字输入错误\t请在此处重新输入:");
                                                }
                                            }
                                            linkedList.get(i).setStock(linkedList.get(i).getStock() + num);
                                            loop = false;
                                            break;
                                        }
                                    }
                                    if (i == linkedList.size()) {
                                        System.out.println("没有找到这个菜,请输入正确的菜名");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println(" ");
                                show(linkedList);
                                boolean loop1 = true;
                                System.out.println("----- 减 少 库 存 -----");
                                while (loop1) {
                                    System.out.print("你想扔的菜名:");
                                    String foodName = scanner.next();
                                    int i = 0;
                                    int num = 0;
                                    boolean loop4 = true;
                                    for (i = 0; i < linkedList.size(); i++) {
                                        if (foodName.equals(linkedList.get(i).getFood())) {
                                            System.out.print("你想扔的数量:");
                                            while (loop4) {
                                                while (true) {
                                                    try {
                                                        String s1 = scanner.next();
                                                        num = Integer.parseInt(s1);
                                                        if(num < 0){
                                                            System.out.print("数量不能为负数,请重新输入:");
                                                            continue;
                                                        }
                                                        loop4 = false;
                                                        break;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("数字输入错误\t请在此处重新输入:");
                                                    }
                                                }
                                                if(linkedList.get(i).getStock() == 0){
                                                    System.out.println("库存是0不能再扔了");
                                                    loop1 = false;
                                                    loop4 = false;
                                                    num = 0;
                                                    break;
                                                } else if (num > linkedList.get(i).getStock()) {
                                                    System.out.println(linkedList.get(i).getFood() + "的库存为" + linkedList.get(i).getStock());
                                                    System.out.print("扔的比炒的多了,请重新输入扔的数量:");
                                                    loop4 = true;
                                                }
                                            }
                                            linkedList.get(i).setStock(linkedList.get(i).getStock() - num);
                                            loop1 = false;
                                            break;
                                        }
                                    }
                                    if (i == linkedList.size()) {
                                        System.out.println("没有找到这个菜,请输入正确的菜名");
                                        continue;
                                    }
                                }
                                break;
                            case 4:
                                ChefManage();
                                break;
                            default:
                                System.out.println("输入的数字有错请重新输入");
                                break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("--------- 菜 单 模 块 ---------");
                        System.out.println("1.查看菜单");
                        System.out.println("2.修改菜单");
                        System.out.println("3.删除特色菜");
                        System.out.println("4.增加特色菜");
                        System.out.println("5.返回上一级");
                        System.out.println("请 输 入 您 的 选 择 :");
                        boolean lop = true;
                        int num2 ;
                        while (true) {
                            try {
                                String s1 = scanner.next();
                                num2 = Integer.parseInt(s1);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.print("数字输入错误\t请在此处重新输入:");
                            }
                        }
                        while (lop) {
                            switch (num2) {
                                case 1:
                                    System.out.println(" ");
                                    show(linkedList);
                                    lop = false;
                                    break;
                                case 2:
                                    System.out.println(" ");
                                    show(linkedList);
                                    boolean loop2 = true;
                                    while (loop2) {
                                        System.out.print("请输入要修改的食品id : ");
                                        while (true) {
                                            try {
                                                String s1 = scanner.next();
                                                num2 = Integer.parseInt(s1);
                                                break;
                                            } catch (NumberFormatException e) {
                                                System.out.print("数字输入错误\t请在此处重新输入:");
                                            }
                                        }
                                        boolean loop = true;
                                        boolean loop1 = true;
                                        String str1 = "";
                                        int i;
                                        double price;
                                        while (loop) {
                                            for (i = 0; i < linkedList.size(); i++) {
                                                if (num2 == linkedList.get(i).getId()) {
                                                    System.out.println("----------- 修 改 食 品 -----------");
                                                    while (loop1) {
                                                        System.out.print("请输入食品名称 :");
                                                        str1 = scanner.next();
                                                        int j = 0;
                                                        for (j = 0; j < linkedList.size(); j++) {
                                                            if (str1.equals(linkedList.get(j).getFood())) {
                                                                System.out.println("重名了,请再次修改!");
                                                                break;
                                                            }
                                                        }
                                                        if(j == linkedList.size()){
                                                            loop1 = false;
                                                        }
                                                    }
                                                    linkedList.get(i).setFood(str1);
                                                    System.out.print("请输入食品价格 :");
                                                    while (true) {
                                                        try {
                                                            String s1 = scanner.next();
                                                            price = Integer.parseInt(s1);
                                                            if(price < 0){
                                                                System.out.println("价格不能为负数,请重新输入:");
                                                                continue;
                                                            }
                                                            break;
                                                        } catch (NumberFormatException e) {
                                                            System.out.print("数字输入错误\t请在此处重新输入:");
                                                        }
                                                    }
                                                    linkedList.get(i).setPrice(price);
                                                    System.out.print("请输入描述 :");
                                                    String str2 = scanner.next();
                                                    linkedList.get(i).setDescribe(str2);
                                                    linkedList.get(i).setStock(0);
                                                    System.out.println(linkedList.get(i).getFood() + " 修改成功!");
                                                    loop = false;
                                                    loop2 = false;
                                                    break;
                                                }
                                            }
                                            if (i == linkedList.size()) {
                                                System.out.println("你输入的id有错误,请重新输入id");
                                                break;
                                            }
                                        }
                                    }
                                    lop = false;
                                    break;
                                case 3:
                                    System.out.println(" ");
                                    show(linkedList);
                                    boolean loop1 = true;
                                    boolean loop5 = true;
                                    int i = 0;
                                    while (loop1) {
                                        System.out.print("请输入要删除的菜品的id : ");
                                        while (loop5) {
                                            try {
                                                String s1 = scanner.next();
                                                num2 = Integer.parseInt(s1);
                                                for (i = 0; i < linkedList.size(); i++) {
                                                    if(num2 == linkedList.get(i).getId()){
                                                        loop5 = false;
                                                        break;
                                                    }
                                                }
                                                if(i == linkedList.size()){
                                                    System.out.print("您要删除的id不存在,请重新输入: ");
                                                    continue;
                                                }
                                                break;
                                            } catch (NumberFormatException e) {
                                                System.out.println("数字输入错误\t请在此处重新输入:");
                                            }
                                        }
                                        while (true) {
                                            System.out.println("确定要删除吗?(Y/N)");
                                            String s1 = scanner.next();
                                            if (s1.equals("Y")) {
                                                linkedList.remove(num2 - 1);
                                                for (int j = num2 - 1; j < linkedList.size(); j++) {
                                                    linkedList.get(j).setId(linkedList.get(j).getId() - 1);
                                                }
                                                loop1 = false;
                                                lop = false;
                                                break;
                                            } else if (s1.equals("N")) {
                                                loop1 = false;
                                                break;
                                            } else {
                                                System.out.println("请输入Y/N");
                                                continue;
                                            }
                                        }
                                    }
                                    lop = false;
                                    break;
                                case 4:
                                    System.out.println("----------- 新 增 菜 单 -----------");
                                    System.out.print("请输入id : ");
                                    int i2 = 0;
                                    while (true) {
                                        try {
                                            String s1 = scanner.next();
                                            i2 = Integer.parseInt(s1);
                                            break;
                                        } catch (NumberFormatException e) {
                                            System.out.print("数字输入错误\t请在此处重新输入:");
                                        }
                                    }
                                    for (int j = 0; j < linkedList.size(); j++) {
                                        if (i2 == linkedList.get(j).getId()) {
                                            System.out.println("id重复了!");
                                            System.out.print("请重新输入id :");
                                            while (true) {
                                                try {
                                                    String s1 = scanner.next();
                                                    i2 = Integer.parseInt(s1);
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.out.print("数字输入错误\t请在此处重新输入:");
                                                }
                                            }
                                            j = 0;
                                        } else if (j == linkedList.size() - 1) {
                                            break;
                                        }
                                    }
                                    System.out.print("请输入菜名 : ");
                                    String sname = scanner.next();
                                    for (int j = 0; j < linkedList.size(); j++) {
                                        if (sname.equals(linkedList.get(j).getFood())) {
                                            System.out.println("菜名重复了!");
                                            System.out.print("请重新输入菜名 :");
                                            sname = scanner.next();
                                            j = 0;
                                        } else if (j == linkedList.size() - 1) {
                                            break;
                                        }
                                    }
                                    double price = 0;
                                    System.out.print("请输入菜价 : ");
                                    while (true) {
                                        try {
                                            String s1 = scanner.next();
                                            price = Integer.parseInt(s1);
                                            if(price < 0){
                                                System.out.println("价格不能为负数,请重新输入:");
                                                continue;
                                            }
                                            break;
                                        } catch (NumberFormatException e) {
                                            System.out.print("数字输入错误\t请在此处重新输入:");
                                        }
                                    }
                                    System.out.print("请输入描述 : ");
                                    String s1 = scanner.next();
                                    linkedList.add(new Menu(i2, sname, price, s1, 0));
                                    lop = false;
                                    break;
                                case 5:
                                    ChefManage();
                                    break;
                                default:
                                    System.out.println("输入的数字有错请重新输入");
                                    lop = false;
                                    break;
                            }
                        }
                    }
                case 3:
                    String str1 = "";
                    String str2 = "";
                    int i = 0;
                    boolean looop = true;
                    boolean looop1 = true;
                    int n4 = arrayList.size();
                    ShowOrder(arrayList);
                    if(arrayList.size() == 0){
                        System.out.println("现在还没有订单...");
                        break;
                    }
                    int n3 = 1;
                    int j = 0;
                    int a = -1;
                    System.out.println("请输入想要处理的订单编号 :");
                    while (looop1) {
                        str1 = scanner.next();
                        for (i = 0; i < arrayList.size(); i++) {
                                if (str1.equals(arrayList.get(i).getCode())) {
                                    n3 = 2;
                                    while (looop) {
                                        System.out.print("是否处理此订单?(Y/N)");
                                        str2 = scanner.next();
                                        if (str2.equals("Y")) {
                                            Set set = arrayList.get(i).getHashMap().entrySet();
                                           boolean loop11 = true;
                                            while (loop11) {
                                                for (j = 0; j < linkedList.size(); j++) {
                                                    for (Object o : set) {
                                                        Map.Entry entry = (Map.Entry) o;
                                                        if (linkedList.get(j).getFood().equals(entry.getKey())) {
                                                            if (linkedList.get(j).getStock() == 0) {
                                                                System.out.println(linkedList.get(j).getFood() + "库存为零,主厨需要炒菜!");
                                                                loop11 = false;
                                                            }
                                                        }
                                                    }
                                                }

                                                for (j = 0; j < linkedList.size(); j++) {
                                                    for (Object o1 : set) {
                                                        Map.Entry entry = (Map.Entry) o1;
                                                        if (linkedList.get(j).getFood().equals(entry.getKey())) {
                                                            if (linkedList.get(j).getStock() - (int) entry.getValue() < 0) {
                                                                System.out.println("主厨还需要炒" + linkedList.get(j).getFood() + ((int) entry.getValue() - linkedList.get(j).getStock()) + "份");
                                                                loop11 = false;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            }

                                          while (loop11) {
                                              for (j = 0; j < linkedList.size(); j++) {
                                                  for (Object o : set) {
                                                      Map.Entry entry = (Map.Entry) o;
                                                      if (linkedList.get(j).getFood().equals(entry.getKey())) {
                                                          if (linkedList.get(j).getStock() - (int) entry.getValue() >= 0) {
                                                              linkedList.get(j).setStock(linkedList.get(j).getStock() - (int) entry.getValue());
                                                              a = 1;
                                                              loop11 = false;
                                                          }
                                                      }
                                                  }
                                              }
                                          }


                                            if(a == 1 ) {
                                                System.out.println("订单处理成功!");
                                                ChefIncome += arrayList.get(i).getTotalPrice();
                                                arrayList.remove(i);
                                                for ( j = i + 1; j < arrayList.size(); j++) {
                                                    arrayList.get(j).setId(arrayList.get(j).getId() - 1);
                                                }
                                            }
                                            looop = false;
                                            looop1 = false;

                                        } else if (str2.equals("N")) {
                                            System.out.println("订单处理失败!");
                                            looop = false;
                                            looop1 = false;
                                        } else {
                                            System.out.print("输入错误,请重新输入 : ");
                                        }
                                    }
                                }
                                if(n3 == 2){
                                    break;
                                }
                        }
                            if (i == n4) {
                                System.out.print("没有此订单,请重新输入订单:");
                            }
                    }
                    break;
                case 4:
                    System.out.println("我的余额为 : " + ChefIncome + "元");
                    break;
                case 5:
                    new Page().page();
                    break;
                default:
                    System.out.println("你输入的数字有误请重新输入");
                    break;
            }
        }
    }


    //顾客
    public void CustomerManage() throws InterruptedException {
//        if(num2 == 2) {
//            linkedList1 = linkedList;
//        }

        boolean loop3 = true;
        Scanner scanner = new Scanner(System.in);
        while(loop3) {
            System.out.println("---------- 顾 客 模 块 ----------");
            System.out.println("1.查看菜单");
            System.out.println("2.点菜");
            System.out.println("3.查看余额");
            System.out.println("4.返回上一级");
            System.out.print("请 输 入 您 的 选 择 :");
            int n1 ;
            while (true) {
                try {
                    String s1 = scanner.next();
                    n1 = Integer.parseInt(s1);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("数字输入错误\t请在此处重新输入:");
                }
            }
            switch (n1) {
                case 1:
                    show(linkedList);
                    break;
                case 2:
                    Map hashMap1 = new HashMap();
                    hashMap = hashMap1;
                    h = 0;
                    show(linkedList);
                    double cost = 0;
                    int i1 = 0;
                    boolean loop1 = true;
                    boolean loop = true;
                    boolean loop2 = true;
                    String s2 = "";
                    int nummer = 0;
                    while (loop) {
                        while (loop1) {
                            loop2 = true;
                            System.out.print("请输入想要购买的商品名字 :");
                            s2 = scanner.next();
                            for (i1 = 0; i1 < linkedList.size(); i1++) {
                                if (s2.equals(linkedList.get(i1).getFood())) {
                                    int nums;
                                    System.out.print("请输入购买的数量 :");
                                    while (true) {
                                        try {
                                            String s3 = scanner.next();
                                            nums = Integer.parseInt(s3);
                                            if (nums <= 0) {
                                                System.out.println("数量不能为负数或者零,请重新输入:");
                                                continue;
                                            }
                                            break;
                                        } catch (NumberFormatException e) {
                                            System.out.print("数字输入错误\t请在此处重新输入:");
                                        }
                                    }

                                    if(!(hashMap.size() == 0)) {
                                        Set set = hashMap.entrySet();
                                        int i3 = 0;
                                        for (Object o : set) {
                                            Map.Entry entry = (Map.Entry) o;
                                            if (entry.getKey().equals(s2)) {
                                                entry.setValue((int) entry.getValue() + nums);
                                                i3++;
                                            }
                                            if(i3 == 1){
                                                break;
                                            }
                                        }
                                        if(i3 == 0){
                                            hashMap.put(s2,nums);
                                            h++;
                                        }
                                    }else if(h == hashMap.size()){
                                        hashMap.put(s2,nums);
                                        h++;
                                    }

                                    cost += nums * linkedList.get(i1).getPrice();
                                    break;
                                }
                            }
                            if (i1 == linkedList.size()) {
                                System.out.println("没有找到你输入的菜名,请重新输入!");
                                continue;
                            }
                            while (loop2) {
                                System.out.println("请问您是否还要继续?(Y/N)");
                                String s3 = scanner.next();
                                if (s3.equals("Y")) {
                                    loop2 = false;
                                } else if (s3.equals("N")) {
                                    loop2 = false;
                                    loop1 = false;
                                } else {
                                    System.out.println("请输入正确的字母!");
                                    continue;
                                }
                            }
                        }
                        String s5 = "";

                        System.out.println("下单成功,您的购物车如下:");
                            String s6 = String.valueOf(n9++);
                            s5 = "51de9Bee*-8271-9978-3ffvde87" + s6;

                        Order order = new Order(i++, s5,hashMap, cost);
                        arrayList.add(order);
                        System.out.println(order);
                        System.out.println("请付款!");
                        System.out.println("请稍等....");
                        Thread.sleep(1000);
                        if (CustomerMoney - cost >= 0) {
                            System.out.println("...付款成功!");
                            CustomerMoney -= cost;
                        } else {
                            System.out.println("...付款失败,余额不足!");
                            arrayList.remove(order);
                        }
                        loop = false;
                        break;
                    }
                    break;
                case 3:
                    System.out.println("您当前的余额为: " + CustomerMoney + "元");
                    break;
                case 4:
                    new Page().page();
                    break;
                default:
                    System.out.println("输入错误请重新输入");
                    loop3 = false;
                    break;
            }//switch
        }//CustomeManage
    }
    public static void show(LinkedList<Menu> linkedList){
        Collections.sort(linkedList, new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getId() - o2.getId();
            }
        });
        System.out.println("------------------------------------- 食 品 列 表 -------------------------------------");

        for (Menu o : linkedList) {
            System.out.println(o);
        }
        System.out.println("------------------------------------- 食 品 列 表 -------------------------------------");
        System.out.println("共计" + linkedList.size() + "条数据");
    }

    public static void ShowOrder(ArrayList<Order> arrayList){
        System.out.println("--------------------------------------------------------- 订 单 列 表 ---------------------------------------------------------");
        for (Order order : arrayList) {
            System.out.println(order);
        }
        System.out.println("--------------------------------------------------------- 订 单 列 表 ---------------------------------------------------------");
        System.out.println("共计" + arrayList.size() + "条数据");
    }
}
class NumException extends RuntimeException{
    public NumException(String message) {
        super(message);
    }
}