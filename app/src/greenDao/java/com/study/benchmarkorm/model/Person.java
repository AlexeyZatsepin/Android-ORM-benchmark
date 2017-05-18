package com.study.benchmarkorm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity
public class Person{

    @Id
    private Long id;
    private String firstName;
    private String secondName;
    private Date birthdayDate;
    private String gender;
    private long phone;

    @ToOne(joinProperty = "id")
    private Library library;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 778611619)
    private transient PersonDao myDao;
    @Generated(hash = 510062427)
    private transient Long library__resolvedKey;

    public Person() {
    }

    @Generated(hash = 2135609568)
    public Person(Long id, String firstName, String secondName, Date birthdayDate,
            String gender, long phone) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
    }

    public Person(@NotNull String firstName, @NotNull String secondName,
                  @NotNull Date birthdayDate, String gender, long phone, Library library) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.phone = phone;
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setId(Long id) {
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
    @Generated(hash = 2056799268)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }
}
