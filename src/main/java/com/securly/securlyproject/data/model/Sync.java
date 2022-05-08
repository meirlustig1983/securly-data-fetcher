package com.securly.securlyproject.data.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "syncs")
public class Sync {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sync_id")
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "accounts_exists")
    private Integer accountsExists;

    @Column(name = "accounts_new")
    private Integer accountsNew;

    @Column(name = "accounts_updated")
    private Integer accountsUpdated;

    @Column(name = "accounts_deleted")
    private Integer accountsDeleted;

    @Column(name = "courses_exists")
    private Integer coursesExists;

    @Column(name = "courses_new")
    private Integer coursesNew;

    @Column(name = "courses_updated")
    private Integer coursesUpdated;

    @Column(name = "courses_deleted")
    private Integer coursesDeleted;

    @Column(name = "sections_exists")
    private Integer sectionsExists;

    @Column(name = "sections_new")
    private Integer sectionsNew;

    @Column(name = "sections_updated")
    private Integer sectionsUpdated;

    @Column(name = "sections_deleted")
    private Integer sectionsDeleted;

    public Sync() {
        this.createDate = new Date();
        this.accountsExists = 0;
        this.accountsNew = 0;
        this.accountsUpdated = 0;
        this.accountsDeleted = 0;
        this.coursesExists = 0;
        this.coursesNew = 0;
        this.coursesUpdated = 0;
        this.coursesDeleted = 0;
        this.sectionsExists = 0;
        this.sectionsNew = 0;
        this.sectionsUpdated = 0;
        this.sectionsDeleted = 0;
    }
}
