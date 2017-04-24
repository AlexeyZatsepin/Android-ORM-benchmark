package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

public class InverseRandomGenerator extends RandomObjectsGenerator{

    private int index = 0;

    public Library nextLibrary(List<Book> books, List<Person> persons) {
        Library next = new Library(randomString.nextString(), randomString.nextString());
        next.setId(index++);
        for (Book item:books) {
            item.setLibId(next.getId());
        }
        for (Person item:persons) {
            item.setLibId(next.getId());
        }
        return next;
    }

}
