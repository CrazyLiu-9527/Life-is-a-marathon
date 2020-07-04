package Array.GenericDataStructures06;

/**
 * @author liuzy
 * @date 2020/6/14 00:45
 */
public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static void main(String[] args) {
        Array<Student> arr = new Array<Student>();
        arr.addLast(new Student("xiaoming", 10));
        ;
        arr.addLast(new Student("xiaohong", 13));
        arr.addLast(new Student("xiaogang", 12));
        System.out.println(arr);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
