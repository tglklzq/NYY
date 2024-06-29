package org.lzq.nyy.domain;

import java.util.Date;
import lombok.Data;

@Data
public class Administrators {
    private Integer administratorid;

    private String administratorname;

    private String administratorpassword;

    private String administratorphonenumber;

    private String administratoremail;

    private Integer roleid;

    private Date createdat;

    private Date updatedat;
}