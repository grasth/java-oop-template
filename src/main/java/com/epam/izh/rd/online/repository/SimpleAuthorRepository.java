package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository{

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) == null){
            authors = Arrays.copyOf(authors, authors.length+1);
            authors[authors.length-1] = author;
            return true;
        }
            return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author obj:authors) {
            if(obj.getName().equals(name) && obj.getLastName().equals(lastname))
                return obj;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int id = 0;
        while(id < authors.length){
            if(authors[id] == author)
            {
                authors[id] = authors[authors.length-1];
                authors[authors.length-1] = new Author();

                authors = Arrays.copyOf(authors, authors.length-1);
                return true;
            }
            id++;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
