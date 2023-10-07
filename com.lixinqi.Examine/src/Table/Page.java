package Table;

import java.util.InputMismatchException;
import java.util.Scanner;


@SuppressWarnings({"all"})
public class Page {
    private static double CustomerMoney = 1000;
    private static double ChefIncome = 0;
    private static String account = "admin";
    private static String password = "123";
    private static int time = 3;
    public static int num2 = 1;

    public static void main(String[] args) throws InterruptedException {
        new Page().page();
    }

    public void page() throws InterruptedException {
        int i = 0;
        boolean loop1 = true;
        boolean loop = true;
        People people = new People();
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            loop1 = true;
            System.out.println("欢迎来到起点炒个菜,请选择你的身份前面的数字");
            System.out.println("1.厨师\t2.顾客\t3.退出");
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
            while (loop1) {
                boolean loop2 = true;
                switch (choose) {
                    case 1:
                        people.ChefEntry();
                        while (loop2) {
                            people.ChefManage();
                        }
                        break;
                    case 2:
                        while (loop2) {
                            people.CustomerManage();
                        }
                        break;
                    case 3:
                        boolean loop3 = true;
                        String s1 = "";
                        i++;
                        System.out.println("确定要退出吗?(Y/N)");
                        while (loop3) {
                            s1 = scanner.next();
                            if (s1.equals("Y")) {
                                System.out.println("退出程序...");
                                System.exit(0);
                            } else if (s1.equals("N")) {
                                loop1 = false;
                                loop = true;
                                break;
                            } else {
                                System.out.println("输入错误,请重新输入:");
                            }
                        }
                        break;
                    default:
                        System.out.println("输入数字错误请重新输入");
                        loop1 = false;
                        break;
                }
            }
        }
    }
}


