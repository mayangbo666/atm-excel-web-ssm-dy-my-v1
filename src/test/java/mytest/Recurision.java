package mytest;

/**
 * 4!
 *
 * @author mayangbo666 [github.com/mayangbo666] myb m-yb
 * @time 2018/11/24  18:05
 */
public class Recurision {

    public static void main(String[] args) {
        System.out.println(multis(10000000));
    }

    public static int multis(int n) {

        int result = 1;

        while (true){
            if (n <= 1){
                return result;
            }
            result = result * n;
            --n;
            multis(n);
        }
    }
}
