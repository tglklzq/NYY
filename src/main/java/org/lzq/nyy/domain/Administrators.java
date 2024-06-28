package org.lzq.nyy.domain;

import java.util.Date;

public class Administrators {
    private Integer administratorid;

    private String administratorname;

    private String administratorpassword;

    private String administratorphonenumber;

    private String administratoremail;

    private Integer roleid;

    private Date createdat;

    private Date updatedat;

    public Integer getAdministratorid() {
        return administratorid;
    }

    public void setAdministratorid(Integer administratorid) {
        this.administratorid = administratorid;
    }

    public String getAdministratorname() {
        return administratorname;
    }

    public void setAdministratorname(String administratorname) {
        this.administratorname = administratorname;
    }

    public String getAdministratorpassword() {
        return administratorpassword;
    }

    public void setAdministratorpassword(String administratorpassword) {
        this.administratorpassword = administratorpassword;
    }

    public String getAdministratorphonenumber() {
        return administratorphonenumber;
    }

    public void setAdministratorphonenumber(String administratorphonenumber) {
        this.administratorphonenumber = administratorphonenumber;
    }

    public String getAdministratoremail() {
        return administratoremail;
    }

    public void setAdministratoremail(String administratoremail) {
        this.administratoremail = administratoremail;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }
}