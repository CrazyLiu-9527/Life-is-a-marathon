package Array.DynamicArray07;

/**
 * @author liuzy
 * @date 2020/6/14 01:05
 */
public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<Integer>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }

        arr.addLast(100);
        System.out.println(arr);
        System.out.println(arr.getSize());

        arr.remove(1);
        System.out.println(arr);
    }
}
