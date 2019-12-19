package Lesson1.Task2;
import java.util.ArrayList;
import java.util.List;

/*2. Написать метод, который преобразует массив в ArrayList;*/
public class Task2<T> extends Object{
    private T[] obj;
    List<T> list = new ArrayList<T>();

    public Task2(T[] obj) {
        this.obj = obj;
    }

    public T arrToList(){
        for (int i = 0; i < obj.length; i++) {
            list.add(obj[i]);
        }
        return (T) list;
    }

    public void info(){
        System.out.println("Array: ");
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i] + " ");
        }
        System.out.println();
        System.out.println("List:");
        System.out.println(list);
    }
}
