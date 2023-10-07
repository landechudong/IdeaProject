//工具类
package Test.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 判断是否要继续的工具方法
 * 1，用于控制输入的字符只能是大小写的y,n,Y.N
 * 2,把小写的y,n转成大写的
 * @return 返回的是Y或N
 */
public class CookUtil {
    public static char choice(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("确定要删除/退出吗？Y or N");
            char letter = scanner.next().charAt(0);
            if (letter == 'y' || letter == 'n') {
                letter = Character.toUpperCase(letter);
            }
            if (letter == 'Y' || letter == 'N'){

                return letter;
            }
        }
    }
    //
    public static char choice1(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("还要继续点菜吗？Y or N");
            char letter = scanner.next().charAt(0);
            if (letter == 'y' || letter == 'n') {
                letter = Character.toUpperCase(letter);
            }
            if (letter == 'Y' || letter == 'N'){

                return letter;
            }
        }
    }
    /**
     * 用于限制输入数字的地方只能是整型的数字
     * @return 返回一个int类型的数字
     */
    public static int readInt(){
        int n;
        Scanner scanner = new Scanner(System.in);
        for(;;){
            try{
                n = scanner.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("输入的数字格式错误，请重新输入");
                scanner.next();
            }
        }
        return n;
    }

    /**
     * 用于限定输入的数字只能是double类型的数字
     * @return 返回一个double类型的数字
     */
    public static double readDouble(){
        double n;
        Scanner scanner = new Scanner(System.in);
        for(;;){
            try{
                n = scanner.nextDouble();
                break;
            }catch (InputMismatchException e){
                System.out.println("输入的数字格式错误，请重新输入");
                scanner.next();
            }
        }
        return n;
    }
}
