package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.List;

public class InverseRandomGenerator extends RandomObjectsGenerator{

    public Library nextLibrary(List<Book> books, List<Person> persons) {
        Library next = new Library(randomString.nextString(), randomString.nextString());
        for (Book item:books) {
            item.setLibrary(next);
        }
        for (Person item:persons) {
            item.setLibrary(next);
        }
        return next;
    }

}
