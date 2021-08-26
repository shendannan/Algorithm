package C_scenario;

import java.util.Scanner;

/**
 * 在如下4*5的矩阵中，请计算从A移动到B一共有多少种走法？要求每次只能向上或向右移动一格，并且不能经过P。
 * 已知A在左下角
 * https://blog.csdn.net/tomcmd/article/details/47906787
 * 输入：
 * B的坐标 5 4
 * P的坐标 3 3
 * 输出：走法数量
 */
public class PathNum {
    public static int f(int m, int n)
    {
        if(m == 1 || n == 1)
            return 1;
        return f(m-1,n)+f(m,n-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m,n;//右上角的坐标（以左下角为坐标轴）
        while(true){
            m = sc.nextInt();
            n = sc.nextInt();
            if(m == -1 || n == -1){
                break;
            }
            //输入不能经过的点的坐标
            int p0,p1;
            p0 = sc.nextInt();
            p1 = sc.nextInt();
            int result = f(m,n) - f(p0,p1) * f(m-p0+1,n-p1+1);
            System.out.println(result);
        }
    }
}
