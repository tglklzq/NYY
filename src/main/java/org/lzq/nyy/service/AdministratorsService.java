package org.lzq.nyy.service;

import org.lzq.nyy.domain.Administrators;

public interface AdministratorsService {
    public Administrators selectEailandPassword(String administratorEmail, String administratorPassword);

}
