package com.example.strategy;

public class Sorter {

    public void sort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void sort(Book[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1  ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    public void swap(Book[] arr, int i, int j){
        Book temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void sort(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1  ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }

    public void swap(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
