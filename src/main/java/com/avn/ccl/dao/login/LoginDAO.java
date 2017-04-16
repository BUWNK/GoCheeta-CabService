/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.dao.login;

import com.avn.ccl.model.user.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @Author : Office Isuru
 * @Document : LoginDAO
 * @Date : Sep 23, 2015 11:34:39 AM
 */
public interface LoginDAO {

    public int checkUserAlreadyExists(String username) throws Exception;

    public List<User> getUserNamePasswordAndRole(String username) throws Exception;

    public Date getLastLoginDateTime(String username) throws Exception;

    public void updateLastLogin(String username, String logintime) throws Exception;

}
