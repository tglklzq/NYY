package org.lzq.nyy.vo;

import lombok.Data;
import org.lzq.nyy.domain.Administrators;
@Data
public class AdministratorsVo {
    public String email;
    public String password;
    public Administrators roleID;
}
