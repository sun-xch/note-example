package com.example.strategy;

public class Book01 implements MyComparable<Book01>{

    int width, height;

    public Book01(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int compareTo(Book01 book01) {
        if(this.width < book01.width) {
            return -1;
        } else if(this.width > book01.width) {
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Book01{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
