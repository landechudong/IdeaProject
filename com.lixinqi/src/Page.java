import java.util.Scanner;

@SuppressWarnings({"all"})
public class Page {
    public static void main(String[] args) throws InterruptedException {
        showChoiceMenu();
    }
    public static void showChoiceMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================== 起 点 超 市 管 理 系 统 ====================");
        System.out.println("请选择你的身份");
        System.out.println("1.用户登录");
        System.out.println("2.管理员登录");
        System.out.println("3.退出");
        String s2 = null;
        int i = 0;
        try {
            s2 = scanner.next();
            i = Integer.parseInt(s2);
        } catch (NumberFormatException e) {
            System.out.println("数字输入形式不正确,请重新输入!");
            showChoiceMenu();
        }

        switch (i){
            case 1 :
                People.showUserMenu();
                break;
            case 2 :
                People.AdminEntry();
                break;
            case 3 :
                boolean loop9 = true;
                System.out.println("确定要退出吗?(Y/N)");
                while (loop9) {
                    String s1 = scanner.next();
                    if (s1.equals("Y")) {
                        System.out.println("退出程序...");
                        System.exit(0);
                    } else if (s1.equals("N")) {
                        showChoiceMenu();
                        break;
                    } else {
                        System.out.println("输入错误,请重新输入:");
                    }
                }
                break;
            default:
                System.out.println("数字输入有误,请重新输入!");
                showChoiceMenu();
        }
    }
}
