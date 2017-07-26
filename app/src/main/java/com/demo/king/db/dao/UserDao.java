package com.demo.king.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.demo.king.db.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAll();

    @Query("select * from user where id = :id")
    User getUserById(int id);

    @Insert
    List<Long> insertUsers(User...users);

    @Insert
    Long insertUser(User user);

    @Delete
    void deleteUsers(User...users);

    void updateUsers(User...users);

}
