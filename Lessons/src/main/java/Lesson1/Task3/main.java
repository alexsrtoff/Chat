package Lesson1.Task3;

public class main {

    public static void main(String[] args) {
        Apple apple = new Apple();
        Orange orange = new Orange();

        Box<Fruit> appleBox = new Box(apple, apple.weight);
        Box<Fruit> appleBox1 = new Box(apple, apple.weight);
        Box<Fruit> orangeBox = new Box(orange, orange.weight);

        System.out.println("Добавляем фрукты в коробку");
        appleBox.add(3);
        System.out.println("Коробка appleBox весит: " + appleBox.getWeight());
        orangeBox.add(2);
        System.out.println("Коробка orangeBox весит: " + orangeBox.getWeight());
        appleBox1.add(30);
        System.out.println("Коробка appleBox1 весит: " + appleBox1.getWeight());

        System.out.println("Сравниваем вес коробок appleBox и orangeBox");
        System.out.println(appleBox.compare(orangeBox));

        System.out.println("Пересыпаем appleBox1 в appleBox");
        appleBox.move(appleBox1);
        System.out.println("appleBox весит " + appleBox.getWeight());
        System.out.println("appleBox1 весит " + appleBox1.getWeight());
        System.out.println("Добавляем фрукты в коробку appleBox1");
        appleBox1.add(8);
        System.out.println("Коробка appleBox1 весит: " + appleBox1.getWeight());


        System.out.println("Пересыпаем orangeBox в appleBox");
        appleBox.move(orangeBox);
    }
}
