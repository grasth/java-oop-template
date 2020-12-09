package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        try {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length+1);
            schoolBooks[schoolBooks.length-1] = book;
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[0];
        for (SchoolBook book:schoolBooks) {
            if(book.getName().equals(name)){
                books = Arrays.copyOf(books, books.length+1);
                books[books.length-1] = book;
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {

        int counter = 1;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = schoolBooks[schoolBooks.length - counter];
                counter++;
            }
        }
        if (counter>1) {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - (counter - 1));
            return true;
        }
        else
            return false;
    }

    @Override
    public int count() {
        return (int)Arrays.stream(schoolBooks).count();
    }
}
