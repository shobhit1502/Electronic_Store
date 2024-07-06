package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //create


    //update
    //delete
    //getAllUser
    //get All User
    //get Single User
    //get Single User By ID
    //get Single User By Email
    //search User

    //create
    UserDto createUser(UserDto userDto);
    //update
    UserDto updateUser(UserDto userDto, String userId);
    //delete
    void deleteUser(String userId);
    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
//    List<UserDto> getAllUser(int pageNumber,int pageSize,String sortBy,String sortDir);
    //get single user by id
    UserDto getUserById(String userId);
    //get  single user by email
    UserDto getUserByEmail(String email);
    //search user
    List<UserDto> searchUser(String keyword);
    //other user specific features

    //Optional<User> findUserByEmailOptional(String email);

}