package com.example.springbootsecurity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @Column(name = "r_id")
    private long id;

    @Column(name = "r_rolename")
    private String rolename;

    @Column(name = "r_roleflag")
    private String roleflag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleflag() {
        return roleflag;
    }

    public void setRoleflag(String roleflag) {
        this.roleflag = roleflag;
    }
}