package org.lzq.nyy.service;

import org.lzq.nyy.domain.Administrators;

public interface AdministratorsService {
    public Administrators selectLogin(String administratorEmail, String administratorPassword,Integer roleID);

}
