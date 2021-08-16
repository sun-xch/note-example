package com.example.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Sorter sorter = new Sorter();

        int[] a = {9,2,3,5,7,1,4};
        sorter.sort(a);
        System.out.println(Arrays.toString(a));

        Book[] books = {new Book(6,6), new Book(5,5), new Book(7,7)};
        sorter.sort(books);
        System.out.println(Arrays.toString(books));

        Sorter01<Book> sorter01 = new Sorter01<>();
        sorter01.sort(books, new BookComparator());
        System.out.println(Arrays.toString(books));

        Book01[] Book01s = {new Book01(6,6), new Book01(5,5), new Book01(7,7)};
        Sorter01<Book01> sorter02 = new Sorter01<>();
        sorter02.sort(Book01s, new Book01Comparator());
        System.out.println(Arrays.toString(books));

    }
}
