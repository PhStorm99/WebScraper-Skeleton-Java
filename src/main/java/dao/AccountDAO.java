/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class AccountDAO extends GenericDAO<Account> {

    /**
     * Constructor AccountDAO calling super class
     */
    public AccountDAO() {
        super(Account.class);
    }
    // **************************************************
    // Methods
    // **************************************************
    /**
     * this is findAll method
     *
     * @return findAll
     */
    public List<Account> findAll() {
        return findResults("Account.findAll", null);

    }
    
    /**
     * this is method which find account through id
     *
     * @param id
     * @return findbyIId
     */
    public Account findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return findResult("Account.findById", map);

    }

    /**
     * this is method of DisplayName
     *
     * @param displayName
     * @return the displayName
     */
    public Account findByDisplayName(String displayName) {
        Map<String, Object> map = new HashMap<>();
        map.put("displayName", displayName);
        return findResult("Account.findByDisplayName", map);

    }

    /**
     * this is method of finding Account by User
     *
     * @param user
     * @return the user
     */
    public Account findByUser(String user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return findResult("Account.findByUser", map);

    }

    /**
     * this is the method of finding the password
     *
     * @param password
     * @return returning the password
     */
    public List<Account> findByPassword(String password) {

        Map<String, Object> map = new HashMap<>();
        map.put("password", password);
        return findResults("Account.findByPassword", map);

    }

    /**
     * this is the method for searching the account
     *
     * @param search
     * @return
     */
    public List<Account> findContaining(String search) {
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);
        return findResults("Account.findContaining", map);

    }

    /**
     * ValidateUser is for validating the user through his name and password
     *
     * @param username
     * @param pass
     * @return validateUser
     */
    public Account validateUser(String username, String pass) {

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", pass);

        return findResult("Account.validateUser", map);

    }
}
