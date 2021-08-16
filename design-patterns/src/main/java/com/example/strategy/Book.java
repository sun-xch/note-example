package com.example.strategy;

public class Book {

    int width, height;

    public Book(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int compareTo(Book book){
        if(this.width < book.width) {
            return -1;
        } else if(this.width > book.width) {
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
