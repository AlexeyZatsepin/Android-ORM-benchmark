package com.study.benchmarkorm;

public class LibraryDbSchema {
    public static final class BookTable {
        public static final String NAME = "Book";
        public static final class Cols {
            public static final String ID = "_id";
            public static final String TITLE = "title";
            public static final String AUTHOR = "author";
            public static final String PAGES_COUNT = "pagesCount";
            public static final String BOOK_ID = "bookId";
        }
    }
    public static final class PersonTable {
        public static final String NAME = "Person";
        public static final class Cols {
            public static final String ID = "_id";
            public static final String F_NAME = "firstName";
            public static final String S_NAME = "secondName";
            public static final String DATE = "birthdayDate";
            public static final String GENDER = "gender";
            public static final String PHONE = "phone";
        }
    }
    public static final class LibraryTable {
        public static final String NAME = "Library";
        public static final class Cols {
            public static final String ID = "_id";
            public static final String ADDRESS = "address";
            public static final String NAME = "name";
        }
    }
}
