/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Account;
import dao.AccountDAO;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class AccountLogic extends GenericLogic<Account, AccountDAO> {
    // **************************************************
    // Constants
    // **************************************************

    public static final String DISPLAY_NAME = "firstName";
    public static final String PASSWORD = "joined";
    public static final String USER = "lastName";
    public static final String ID = "id";

    // Constructor
    public AccountLogic() {
        super(new AccountDAO());
    }

    /**
     * this is getAll method
     *
     * @return calling findAll thorugh lambda
     */
    @Override
    public List<Account> getAll() {
        return get(() -> dao().findAll());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Account getWithId(int id) {

        return get(() -> dao().findById(id));
    }

    /**
     * this method is to display the name
     *
     * @param displayName
     * @return displaying name
     */
    public Account getAccountWithDisplayName(String displayName) {
        return get(() -> dao().findByDisplayName(displayName));

    }

    /**
     * this method is for the user
     *
     * @param user
     * @return user
     */
    public Account getAccountWithUser(String user) {
        return get(() -> dao().findByUser(user));
    }

    /**
     * this is method for password
     *
     * @param password
     * @return findbyPassword
     */
    public List<Account> getAccountWithPassword(String password) {
        return get(() -> dao().findByPassword(password));
    }

    /**
     * This method is for the Account Password
     *
     * @param username
     * @param password
     * @return returning the password
     */
    public Account getAccountWith(String username, String password) {
        return get(() -> dao().validateUser(username, password));
    }

    @Override
    public List<Account> search(String search) {

        return get(() -> dao().findContaining(search));
    }

    @Override
    public Account createEntity(Map<String, String[]> parameterMap) {
        Account account = new Account();

        account.setDisplayName(parameterMap.get(DISPLAY_NAME)[0]);
        account.setPassword(parameterMap.get(PASSWORD)[0]);
        account.setUser(parameterMap.get(USER)[0]);
        return account;

    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("firstName", "joined", "lastName", "id");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(DISPLAY_NAME, PASSWORD, USER, ID);
    }

    @Override
    public List<?> extractDataAsList(Account e) {
        return Arrays.asList(e.getId(), e.getDisplayName(), e.getPassword(), e.getUser());
    }
}
