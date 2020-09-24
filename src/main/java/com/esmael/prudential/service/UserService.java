package com.esmael.prudential.service;


import com.esmael.prudential.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}