package mytest;

/**
 * @author mayangbo666 [github.com/mayangbo666] myb m-yb
 * @time 2018/11/24  18:30
 */
public class Recurision2 {

    public static void main(String[] args) {
        System.out.println( fatorial(4));
    }

    public static int fatorial(int n){
        if (n == 0){
            return 1;
        }
        return  n * fatorial(n-1);
    }

}
