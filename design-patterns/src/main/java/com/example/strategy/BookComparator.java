package com.example.strategy;

public class BookComparator implements MyComparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {
        if(o1.width < o2.width) {
            return -1;
        } else if(o1.width > o2.width) {
            return 1;
        }else{
            return 0;
        }
    }

}
