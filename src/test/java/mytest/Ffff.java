package mytest;

/** 斐波拉契数列 f(0)=0 f(1)=1 f(n)=f(n-1)*f(n-2)
  * @author mayangbo666 [github.com/mayangbo666] myb m-yb
 * @time 2018/11/24  18:43
 */
public class Ffff {

    public static void main(String[] args) {
        System.out.println(ff(3));
    }

    public static int ff(int n){
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {

            return ff(n - 1) + ff(n - 2);
        }
    }

}
