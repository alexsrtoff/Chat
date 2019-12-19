package Lesson1.Task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    private  T obj;
    double BoxWeight;
    double objWeight;

    public Box(T obj, double objWeight) {
        this.obj = obj;
        this.objWeight = objWeight;
    }

    List<T> list = new ArrayList<T>();

    public void add(int number){
        for (int i = 0; i < number; i++) {
            list.add(obj);

        }
    }

    public void clearBox(){
        this.list.clear();
    }

    public void move(Box e){
        if(this.obj.getClass().equals(e.obj.getClass())) {
            T tempObj = null;
            for (int i = 0; i < e.list.size(); i++) {
                tempObj = (T) e.list.get(i);
                this.list.add(tempObj);
            }
            e.clearBox();
        }else System.out.println("Пересыпать можно только одинаковые фрукты");
    }

    public double getWeight() {
       this.BoxWeight = list.size() * objWeight;
        return BoxWeight;
    }

    public boolean compare(Box e){
        return this.getWeight() == e.getWeight();
    }
}
