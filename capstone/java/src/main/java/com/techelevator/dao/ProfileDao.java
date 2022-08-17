package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    public boolean createProfileOnRegister(int userId);

    public Profile getUserProfile(int userId);

    public boolean updateUserProfile(Profile profile);
}
