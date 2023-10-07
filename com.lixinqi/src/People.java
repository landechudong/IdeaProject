import java.util.*;

@SuppressWarnings({"all"})
public class People {
    private static String s1 = "123456";
    private static int numss = 0;
    private static int numsss = 0;
    private static int i1 = 0;
    private static int i2 = 1;
    private static int num = 1;
    private static int Allnum = 0;
    private static int AllKind = 0;
    private static int AllMoney = 0;
    private static double SystemBalance = 100000;
    private static double CustomerBalance = 100;
    public static LinkedList<Goods> list = new LinkedList();
    public static LinkedList<Goods> list2 = new LinkedList();
    public static LinkedList<Goods> list3 = new LinkedList();
    static String token = UUID.randomUUID().toString();
    static String token1 = UUID.randomUUID().toString();
    static String token2 = UUID.randomUUID().toString();
    static {
        list.add(new Goods(num++, token, "旺子牛奶", 5.0, 100));
        list.add(new Goods(num++, token1, "雪碧", 5.0, 100));
        list.add(new Goods(num++, token2, "六个核弹", 10.0, 100));
    }

    public static void AdminEntry() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入密码");
        String str1 = scanner.next();
        if (str1.equals(s1)) {
            showAdminMenu();
        } else {
            System.out.println("密码错误,请重试");
            System.out.println((++i1) + "次输入错误");
            if (i1 < 3) {
                new Page().showChoiceMenu();
            }
        }
        if (i1 == 3) {
            System.out.println("密码错误超过三次,账号锁定,请5s中后重试");
            Thread.sleep(5000);
            System.out.println("账号解封,请重试");
            i1 = 0;
            new Page().showChoiceMenu();
        }
    }
    public static void showUserMenu() throws InterruptedException {
        int i1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========== 用 户 系 统 ===========");
        System.out.println("1.购买商品");
        System.out.println("2.添加购物车");
        System.out.println("3.我的购物车");
        System.out.println("4.删除购物车商品");
        System.out.println("5.清空购物车");
        System.out.println("6.返回上一层");
        System.out.println("7.我的余额");
        System.out.println("8.退出");
        while (true) {
            try {
                String s1 = scanner.next();
                i1 = Integer.parseInt(s1);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误\t请在此处重新输入:");
            }
        }
        switch (i1) {
            case 1:
                String s2 = "";
                int i = 0;
                int i2 = 0;
                int i3 = 1;
                HashMap hashMap = new HashMap();
                double cost = 0;
                double costAll = 0;
                boolean loop = true;
                boolean loop1 = true;
                if (list2.size() == 0) {
                    System.out.println("货架上暂时无商品,请通知管理员上架商品!");
                    showUserMenu();
                }else{
                    show(list2);
                }

                    System.out.print("请输入你要购买的商品的条形码号:");
                while (loop) {
                    loop1 = true;
                    s2 = scanner.next();
                    for (i = 0; i < list2.size(); i++) {
                        if (s2.equals(list2.get(i).getGoodsCode())) {
                            System.out.print("请输入你要购买的数量:");
                            while (true) {
                                try {
                                    String s1 = scanner.next();
                                    i2 = Integer.parseInt(s1);
                                    if (i2 <= 0) {
                                        System.out.print("购买的数量不能为零或者负数!请重新输入数量:");
                                        continue;
                                    }
                                    if (i2 > list2.get(i).getGoodsNum()) {
                                        System.out.println("[" + list2.get(i).getGoodName() + "]" + "数量不足,仅有[" + list2.get(i).getGoodsNum() + "]" );
                                        show(list2);
                                        System.out.print("请重新输入数量:");
                                        continue;
                                    }
                                    cost = i2 * list2.get(i).getGoodsPrice();
                                    if(CustomerBalance >= cost){
                                        costAll += (i2 * list2.get(i).getGoodsPrice());
                                    } else if (CustomerBalance < cost) {
                                        System.out.println("购买失败!");
                                        CustomerBalance += costAll;
                                        SystemBalance -= costAll;
                                        Set set = hashMap.entrySet();
                                        for (int j = 0; j < list2.size(); j++) {
                                            for (Object o : set) {
                                                Map.Entry entry = (Map.Entry) o;
                                                if(list2.get(j).getGoodName().equals(entry.getKey())){
                                                    list2.get(j).setGoodsNum(list2.get(j).getGoodsNum() + (int)entry.getValue());
                                                }
                                            }
                                        }
                                        showUserMenu();
                                        continue;
                                    }
                                    list2.get(i).setGoodsNum(list2.get(i).getGoodsNum() - i2);
                                    SystemBalance += (i2 * list2.get(i).getGoodsPrice());
                                    CustomerBalance -= (i2 * list2.get(i).getGoodsPrice());
                                    System.out.println("[" + list2.get(i).getGoodName() + "]" + "购买成功,数量为[" + i2 + "]");
                                    hashMap.put(list2.get(i).getGoodName(),i2);

                                    i = -1;
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.print("数字输入错误\t请在此处重新输入:");
                                }
                            }
                        }
                        if (i == -1) {
                            break;
                        }
                    }
                    if(i == list2.size()){
                        show(list2);
                        System.out.print("未找到此商品的条形码!!请重新输入条形码:");
                        loop = true;
                        loop1 = false;
                    }

                    while (loop1) {
                        if (i == -1) {
                            System.out.print("是否继续购买?(Y/N)");
                            String s1 = scanner.next();
                            if (s1.equals("Y")) {
                                i3++;
                                show(list2);
                                System.out.print("请输入你要购买的商品的条形码号:");
                                break;
                            } else if (s1.equals("N")) {
                                for (int j = 0; j < list2.size(); j++) {
                                    if(list2.get(j).getGoodsNum() == 0){
                                        list2.remove(j);
                                    }
                                }
                                showUserMenu();
                            } else {
                                System.out.print("输入有误!请重新输入:");
                                continue;
                            }
                        }
                    }

                }
                break;
            case 2 :
                int i5 = 0;
                int j = 0;
                boolean loo1 = true;
                if (list2.size() == 0) {
                    System.out.println("货架上暂时无商品,请通知管理员上架商品!");
                    showUserMenu();
                }else{
                    System.out.println("货架上有:");
                    show(list2);
                }
                while (loo1) {
                    System.out.print("请输入将哪一个条形码的商品放到购物车中:");
                    String str1 = scanner.next();
                    for (j = 0; j < list2.size(); j++) {
                        if (str1.equals(list2.get(j).getGoodsCode())) {
                            System.out.print("[" + list2.get(j).getGoodName() + "]" + "的数量为:");
                            while (true) {
                                try {
                                    String s1 = scanner.next();
                                    i5 = Integer.parseInt(s1);
                                    if (i5 <= 0) {
                                        System.out.print("添加的数量不能为零或者负数!请重新输入数量:");
                                        continue;
                                    }
                                    if (i5 > list2.get(j).getGoodsNum()) {
                                        System.out.println("[" + list2.get(j).getGoodName() + "]" + "数量不足,仅有[" + list2.get(j).getGoodsNum() + "]");
                                        show(list2);
                                        System.out.print("请重新输入数量:");
                                        continue;
                                    }
//                                    if (CustomerBalance < i5 * list2.get(j).getGoodsPrice()) {
//                                        System.out.print("若要购买,商品所需要的钱数大于了自己的余额,请重新输入数量:");
//                                        continue;
//                                    }
                                    System.out.println("添加购物车成功!");
                                    int k = 0;
                                    boolean loop11 = false;
                                    int dd = 0;
                                    for (k = 0; k < list3.size(); k++) {
                                        if(list2.get(j).getGoodsCode().equals(list3.get(k).getGoodsCode())){
                                            list3.get(k).setGoodsNum(list3.get(k).getGoodsNum() + i5);
                                            Allnum += i5;
                                            AllMoney += list2.get(j).getGoodsPrice() * i5;
                                            loop11 = true;
                                            break;
                                        }
                                    }
                                    if( k == list3.size()) {
                                        list3.add(new Goods(++numsss, list2.get(j).getGoodsCode(), list2.get(j).getGoodName(), list2.get(j).getGoodsPrice(), i5));
                                        Allnum += i5;
                                        AllMoney += list2.get(j).getGoodsPrice() * i5;
                                    }

                                    showUserMenu();
                                } catch (NumberFormatException e) {
                                    System.out.print("数字输入错误\t请在此处重新输入:");
                                }
                            }
                        }
                    }
                    if (j == list2.size()) {
                        System.out.print("没有找到对应的商品条形码,请重新输入条形码!!");
                        continue;
                    }
                }
                break;
            case 3 :
                if(list3.size() == 0){
                    System.out.println("购物车中无订单!!");
                    showUserMenu();
                }else {
                    System.out.println("订单有:");
                    show(list3);
                }
                System.out.println("购物车共有[" + list3.size() + "]种商品[" + Allnum + "]件[" + AllMoney + "]元");
                showUserMenu();
                break;
            case 4 :
                if(list3.size() == 0){
                    System.out.println("购物车中无订单!!");
                    showUserMenu();
                }
                int k = 0;
                boolean lop = true;
                while (lop) {
                    show(list3);
                    System.out.println("购物车共有[" + list3.size() + "]种商品[" + Allnum + "]件[" + AllMoney + "]元");
                    System.out.println("请输入你要删除商品的条形码值:");
                    String str2 = scanner.next();
                    for (k = 0; k < list3.size(); k++) {
                        if (str2.equals(list3.get(k).getGoodsCode())) {
                            System.out.println("删除成功!!");
                            AllMoney -= list3.get(k).getGoodsNum() * list3.get(k).getGoodsPrice();
                            Allnum -= list3.get(k).getGoodsNum();
                            list3.remove(k);
                            --numsss;
                            for (int l = k; l < list3.size(); l++) {
                                list3.get(l).setGoodsId(list3.get(l).getGoodsId() - 1);
                            }
                            showUserMenu();
                        }
                    }
                    if (k == list3.size()) {
                        System.out.println("条形码错误,请重试!!");
                        continue;
                    }
                }
                break;
            case 5 :
                if(list3.size() == 0){
                    System.out.println("购物车中无订单!!");
                    showUserMenu();
                }
                boolean looop = true;
                int dd = 0;
                show(list3);
                System.out.println("购物车共有[" + list3.size() + "]种商品[" + Allnum + "]件[" + AllMoney + "]元");
                System.out.print("请确定是否继续(Y/N):");
                while (looop) {
                    String str2 = scanner.next();
                    if (str2.equals("Y")) {
                        show(list3);
                        for (int l = 0; l < list3.size(); l++) {
                            for (int m = 0; m < list2.size(); m++) {
                                if(list3.get(l).getGoodsCode().equals(list3.get(m).getGoodsCode())){
                                    if(list3.get(l).getGoodsNum() >= list2.get(m).getGoodsNum()){
                                        System.out.println(list3.get(l).getGoodName() + "的上架没有那么多,请删除订单!!");
                                        dd = 1;
                                        break;
                                    }
                                    else{
                                        break;
                                    }
                                }
                            }
                        }
                        if(dd == 1){
                            showUserMenu();
                        }
                        if (CustomerBalance >= AllMoney) {
                            String token = UUID.randomUUID().toString();
                            System.out.println("交易成功,订单号为[" + token + "]");
                            CustomerBalance -= AllMoney;
                            SystemBalance += AllMoney;
                            showUserMenu();
                        } else {
                            System.out.println("您的余额不足,交易失败!请选择性的删除订单!");
                            showUserMenu();
                        }
                    } else if (str2.equals("N")) {
                        System.out.println("交易已经取消");
                        showUserMenu();
                    } else {
                        System.out.print("输入错误,请重新输入(Y/N):");
                    }
                }
                break;
            case 6 :
                Page.showChoiceMenu();
                break;
            case 7 :
                System.out.println("余额为[" + CustomerBalance + "]");
                showUserMenu();
                break;
            case 8 :
                boolean loop9 = true;
                System.out.println("确定要退出吗?(Y/N)");
                while (loop9) {
                    String s1 = scanner.next();
                    if (s1.equals("Y")) {
                        System.out.println("退出程序...");
                        System.exit(0);
                    } else if (s1.equals("N")) {
                        showUserMenu();
                        break;
                    } else {
                        System.out.println("输入错误,请重新输入:");
                    }
                }
                break;
            default:
                System.out.println("数字输入有误,请重新输入!");
                showUserMenu();
                break;
        }
    }


    public static void showAdminMenu() throws InterruptedException {
        int i1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========== 管 理 员 系 统 ===========");
        System.out.println("1.货架展示");
        System.out.println("2.上架商品");
        System.out.println("3.仓库展示");
        System.out.println("4.商品进货");
        System.out.println("5.下架商品");
        System.out.println("6.返回上层");
        System.out.println("7.退出系统");
        while (true) {
            try {
                String s1 = scanner.next();
                i1 = Integer.parseInt(s1);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误\t请在此处重新输入:");
            }
        }
        switch (i1) {
            case 1:
                if(list2.size() == 0){
                    System.out.println("货架上暂时没有商品,请上架!");
                    showAdminMenu();
                }
                show(list2);
                showAdminMenu();
                break;
            case 2:
                String s3 = "";
                boolean loop = true;
                boolean loop2 = true;
                boolean loop3 = true;
                boolean loop4 = true;
                int i3 = 0;
                int i = 0;
                while (loop2) {
                    show(list);
                    System.out.print("请选择你要上架的商品条形码:");
                    s3 = scanner.next();
                    loop3 = true;
                    while (loop3) {
                        loop4 = true;
                        for (i = 0; i < list.size(); i++) {
                            if (s3.equals(list.get(i).getGoodsCode())) {
                                System.out.print("请输入上架的数量:");
                                loop = true;
                                while (loop) {
                                    try {
                                        String s1 = scanner.next();
                                        i3 = Integer.parseInt(s1);
                                        if (i3 <= 0) {
                                            System.out.print("上架的数量不能为负数或者零,请重新输入上架数量:");
                                            continue;
                                        } else if (i3 > list.get(i).getGoodsNum()) {
                                            System.out.print("上架的数量大于了仓库里的数量,请重新输入数量:");
                                            continue;
                                        } else {
                                            loop = false;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.print("数字输入错误\t请在此处重新输入:");
                                    }
                                }
                                System.out.println("上架成功 ==> 商品名称[" + list.get(i).getGoodName() + "]上架数量[" + i3 + "]");
                                int l = 0;
                                for (l = 0; l < list2.size(); l++) {
                                    if(list.get(i).getGoodsCode().equals(list2.get(l).getGoodsCode())){
                                        list2.get(l).setGoodsNum(list2.get(l).getGoodsNum() + i3);
                                        break;
                                    }
                                }
                                if(l == list2.size()) {
                                    list2.add(new Goods(++numss, list.get(i).getGoodsCode(), list.get(i).getGoodName(), list.get(i).getGoodsPrice(), i3));
                                }
                                if(i3 < list.get(i).getGoodsNum()) {
                                    list.get(i).setGoodsNum(list.get(i).getGoodsNum() - i3);
                                }else{
                                        list.remove(i);
                                        for (int j = i; j < list.size(); j++) {
                                            list.get(j).setGoodsId(list.get(j).getGoodsId() - 1);
                                        }
                                        num--;
                                }
                                loop4 = false;
                                i = -1;
                                showAdminMenu();
                            }
                            if(i == -1){
                                loop2 =false;
                                break;
                            }
                        }
                        while (loop4) {
                            if (i == list.size()) {
                                System.out.println("请效验你输入的条形码");
                                System.out.println("请确定是否继续?(Y/N)");
                                String s = scanner.next();
                                if (s.equals("Y")) {
                                    loop4 = false;
                                    i2++;
                                } else if (s.equals("N")) {
                                    showAdminMenu();
                                } else {
                                    System.out.println("请正确输入 Y/N");
                                }
                            }
                        }
                        if (i2 > 0) {
                            loop3 = false;
                        }
                    }
                }
                break;
            case 3:
                System.out.println("系统剩余金额:" + SystemBalance);
                show(list);
                showAdminMenu();
                break;
            case 4:
                String s1 = "";
                int nums1 = 0;
                int price1 = 0;
                int j = 0;
                System.out.println("系统剩余金额:" + SystemBalance);
                show(list);
                System.out.print("请输入你要购买的商品名:");
                s1 = scanner.next();
                System.out.print("[" + s1 + "]" + "数量:");
                while (true) {
                    try {
                        String s2 = scanner.next();
                        nums1 = Integer.parseInt(s2);
                        if (nums1 <= 0) {
                            System.out.print("进货的数量不能为负数或者零,请重新输入:");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("数字输入错误\t请在此处重新输入:");
                    }
                }
                System.out.print("[" + s1 + "]" + "单价:");
                while (true) {
                    try {
                        String s2 = scanner.next();
                        price1 = Integer.parseInt(s2);
                        if (price1 <= 0) {
                            System.out.print("单价不能为负数或者零,请重新输入:");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("数字输入错误\t请在此处重新输入:");
                    }
                }
                if(SystemBalance > nums1 * price1) {
                    for (j = 0; j < list.size(); j++) {
                        if (s1.equals(list.get(j).getGoodName())) {
                            if (price1 == list.get(j).getGoodsPrice()) {
                                list.get(j).setGoodsNum(list.get(j).getGoodsNum() + nums1);
                                System.out.println("管理员进货成功!!!");
                                SystemBalance -= nums1 * price1;
                                break;
                            }
                        }
                    }
                    if (j == list.size()) {
                        System.out.println("管理员进货成功!");
                        String token = UUID.randomUUID().toString();
                        list.add(new Goods(num++, token, s1, price1, nums1));
                        SystemBalance -= nums1 * price1;
                    }
                }else{
                    System.out.println("进货的钱数少于当前余额,进货失败!!");
                    showAdminMenu();
                }
                showAdminMenu();
                break;
            case 5:
                if (list2.size() == 0) {
                    System.out.println("货架上没有可下架的商品了...");
                    showAdminMenu();
                }
                show(list2);
                String s5 = "";
                int k = 0;
                int l = 0;
                int lp = 1;
                System.out.print("请输入你要删除的条形码号:");
                s5 = scanner.next();
                    for (l = 0; l < list2.size(); l++) {
                        if (s5.equals(list2.get(l).getGoodsCode())) {
                            for (k = 0; k < list.size(); k++) {
                                if(list2.get(l).getGoodsCode().equals(list.get(k).getGoodsCode())) {
                                    System.out.println("删除成功!");
                                    list.get(k).setGoodsNum(list.get(k).getGoodsNum() + list2.get(l).getGoodsNum());
                                    list2.remove(l);
                                    numss -= 1;
                                    lp = 0;
                                    for (int m = l; m < list2.size(); m++) {
                                        list2.get(m).setGoodsId(list2.get(m).getGoodsId() - 1);
                                    }
                                    showAdminMenu();
                                }
                            }
                            if(k == list.size()){
                                System.out.println("删除成功!");
                                list.add(new Goods(num++,s5,list2.get(l).getGoodName(),list2.get(l).getGoodsPrice(),list2.get(l).getGoodsNum()));
                                list2.remove(l);
                                numss -= 1;
                                lp = 0;
                                for (int m = l; m < list2.size(); m++) {
                                    list2.get(m).setGoodsId(list2.get(m).getGoodsId() - 1);
                                }
                                showAdminMenu();
                            }
                        }
                    }

                if (lp == 1) {
                    System.out.println("找不到对应的条形码,删除失败");
                    showAdminMenu();
                }
                break;
            case 6 :
                Page.showChoiceMenu();
            case 7 :
                boolean loop9 = true;
                System.out.println("确定要退出吗?(Y/N)");
                while (loop9) {
                    String s9 = scanner.next();
                    if (s9.equals("Y")) {
                        System.out.println("退出程序...");
                        System.exit(0);
                    } else if (s9.equals("N")) {
                        showAdminMenu();
                        break;
                    } else {
                        System.out.println("输入错误,请重新输入:");
                    }
                }
                break;
            default:
                System.out.println("输入数字错误");
                showAdminMenu();
                break;
        }


    }

    public static void show(LinkedList<Goods> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
//    public static void shows(LinkedList<Goods> list){
//        for (Object o : list) {
//            System.out.println(o);
//        }
//    }



//while (true) {
//        try {
//        String s1 = scanner.next();
//        i1 = Integer.parseInt(s1);
//        break;
//        } catch (NumberFormatException e) {
//        System.out.print("数字输入错误\t请在此处重新输入:");
//        }
//        }