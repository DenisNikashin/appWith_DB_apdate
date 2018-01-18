package com.homework.DAOLayer;

import com.homework.userAccount.User;

public interface DAO {
    void addNewUserDataInDB(User user);
    User checkingTheExistenceOfTheUser(String email, String password);
}
