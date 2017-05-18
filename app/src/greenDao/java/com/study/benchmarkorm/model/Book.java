package com.study.benchmarkorm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity
public class Book{

    @Id
    private Long id;
    private String author;
    private String title;
    private int pagesCount;
    private int bookId;
    @ToOne(joinProperty = "id")
    private Library library;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1097957864)
    private transient BookDao myDao;
    @Generated(hash = 510062427)
    private transient Long library__resolvedKey;

    public Book(@NotNull String author, @NotNull String title,
                int pagesCount, int bookId, Library library) {
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
        this.library = library;
    }

    @Generated(hash = 2026124379)
    public Book(Long id, String author, String title, int pagesCount, int bookId) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.pagesCount = pagesCount;
        this.bookId = bookId;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setId(long id) {
        this.id = id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 290165030)
    public Library getLibrary() {
        Long __key = this.id;
        if (library__resolvedKey == null || !library__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LibraryDao targetDao = daoSession.getLibraryDao();
            Library libraryNew = targetDao.load(__key);
            synchronized (this) {
                library = libraryNew;
                library__resolvedKey = __key;
            }
        }
        return library;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1817530046)
    public void setLibrary(Library library) {
        synchronized (this) {
            this.library = library;
            id = library == null ? null : library.getId();
            library__resolvedKey = id;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1115456930)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookDao() : null;
    }
}
