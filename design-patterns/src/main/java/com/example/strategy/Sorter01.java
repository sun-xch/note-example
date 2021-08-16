package com.example.strategy;

public class Sorter01<T> {

    public void sort(T[] arr, MyComparator<T> comparator){
        for (int i = 0; i < arr.length-1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j], arr[minPos]) == -1 ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    public void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
