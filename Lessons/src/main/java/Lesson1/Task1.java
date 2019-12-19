package Lesson1;
/*1. Написать метод, который меняет два элемента массива местами
(массив может быть любого ссылочного типа);*/
public class Task1 {
    public static void main(String[] args) {
        Object[] arr = {"A", "B", "C", "ZZ", "E", "F"};
        System.out.printf("Исходный массив:");
        System.out.println();
        printArr(arr);
        changePlaces(arr, 1, 4);

    }

    private static void changePlaces(Object[] arr, int firstElement, int secondElement) {
        if (firstElement < 0 || secondElement< 0){
            System.out.println("Индексы массива не могут быть меньше 0");
        }else if (firstElement > arr.length - 1 || secondElement > arr.length - 1) {
            System.out.println("Пытаешься поменять местами несуществующие элементы");
        }else {
            Object temp = arr[firstElement];
            arr[firstElement] = arr[secondElement];
            arr[secondElement] = temp;
            System.out.println("Измененный массив:");
            printArr(arr);
        }
    }

    private static void printArr(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
