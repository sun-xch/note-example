package com.example.strategy;

public class Book01Comparator implements MyComparator<Book01>{

    @Override
    public int compare(Book01 o1, Book01 o2) {
        if(o1.height < o2.height) {
            return -1;
        } else if(o1.height > o2.height) {
            return 1;
        }else{
            return 0;
        }
    }
}
