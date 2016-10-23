package com.testography.androidmiddlegot.data.storage.models;

import com.testography.androidmiddlegot.data.network.res.SwornMemberModelRes;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

@Entity(active = true, nameInDb = "SWORN_MEMBERS")
public class SwornMember {

    @Id
    private Long id;

    @NotNull
    @Unique
    private String remoteId;

    private String houseRemoteId;
    private String name;
    private String born;
    private String died;
    private String titles;
    private String aliases;
    private String father;
    private String mother;
    private String words;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 118802721)
    private transient SwornMemberDao myDao;

    public SwornMember(SwornMemberModelRes swornMemberModelRes, int
            houseRemoteId, String words) {
        this.houseRemoteId = String.valueOf(houseRemoteId);
        this.remoteId = swornMemberModelRes.getId();
        this.name = swornMemberModelRes.getName();
        this.born = swornMemberModelRes.getBorn();
        this.died = swornMemberModelRes.getDied();
        this.titles = swornMemberModelRes.getTitles();
        this.aliases = swornMemberModelRes.getAliases();
        this.father = swornMemberModelRes.getFather();
        this.mother = swornMemberModelRes.getMother();
        this.words = words;
    }

    @Generated(hash = 434436921)
    public SwornMember(Long id, @NotNull String remoteId, String houseRemoteId,
                       String name, String born, String died, String titles, String aliases,
                       String father, String mother, String words) {
        this.id = id;
        this.remoteId = remoteId;
        this.houseRemoteId = houseRemoteId;
        this.name = name;
        this.born = born;
        this.died = died;
        this.titles = titles;
        this.aliases = aliases;
        this.father = father;
        this.mother = mother;
        this.words = words;
    }

    @Generated(hash = 211909483)
    public SwornMember() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public String getHouseRemoteId() {
        return houseRemoteId;
    }

    public void setHouseRemoteId(String houseRemoteId) {
        this.houseRemoteId = houseRemoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getDied() {
        return died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 853564134)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSwornMemberDao() : null;
    }
}