public class MyClass {
    public static void main(String args[]) {
        for (int i = 0; i <= 25; i++) {
            System.out.println(fib(i));
        }
    }
    
    public static int fib(int n) {
        if (n <  2) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}