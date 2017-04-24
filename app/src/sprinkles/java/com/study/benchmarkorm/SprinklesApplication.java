package com.study.benchmarkorm;

import android.app.Application;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

public class SprinklesApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Sprinkles sprinkles = Sprinkles.getInstance(getApplicationContext());

        Migration initialMigration = new Migration();
        initialMigration.createTable(Book.class);
        initialMigration.createTable(Person.class);
        initialMigration.createTable(Library.class);
        sprinkles.addMigration(initialMigration);
    }
}
