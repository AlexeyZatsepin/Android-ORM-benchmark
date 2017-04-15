package com.study.benchmarkorm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class Library{

    @Id
    private long id;

    @NotNull
    private String address;
    private String name;

    @ToMany(referencedJoinProperty = "id")
    private List<Person> employees;

    @ToMany(referencedJoinProperty = "id")
    private List<Book> books;

    /** Used for active entity operations. */
    @Generated(hash = 1118012288)
    private transient LibraryDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Keep
    public Library() {
    }

    @Generated(hash = 2096938530)
    public Library(long id, @NotNull String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    @Keep
    public Library(long id, @NotNull String address, String name,List<Person> employees, List<Book> books) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.books = books;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Keep
    public List<Person> getEmployees() {
        return employees;
    }
    @Keep
    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }
    @Keep
    public List<Book> getBooks() {
        return books;
    }
    @Keep
    public void setBooks(List<Book> books) {
        this.books = books;
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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 353255226)
    public synchronized void resetBooks() {
        books = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1253254391)
    public synchronized void resetEmployees() {
        employees = null;
    }

    public void setId(long id) {
        this.id = id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1348567535)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLibraryDao() : null;
    }
}
