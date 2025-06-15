package com.calendar.milestone.model.service;

import com.calendar.milestone.controller.dto.request.UserPutRequest;
import com.calendar.milestone.controller.dto.request.UserRequest;
import com.calendar.milestone.controller.dto.response.UserResponse;
import com.calendar.milestone.model.entity.User;
import com.calendar.milestone.model.repository.UserRepository;
import com.calendar.milestone.model.value.Password;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User convertToUserUpdate(UserPutRequest userPutRequest) {
        User user = userRepository.select(userPutRequest.getId());
        if (userPutRequest.getName() != null) {
            user.setName(userPutRequest.getName());
        }
        if (userPutRequest.getEmail() != null) {
            user.setEmail(userPutRequest.getEmail());
        }
        if (userPutRequest.getPhoto() != null) {
            user.setPhoto(userPutRequest.getPhoto());
        }
        if (userPutRequest.getBirthday() != null) {
            user.setBirthday(userPutRequest.getBirthday());
        }
        if (userPutRequest.getPassword() != null) {
            user.setPassword(Password.encode(userPutRequest.getPassword()));
        }
        return user;
    }

    public UserResponse convertToUserResponse(final User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        if (user.getPhoto() != null) {
            userResponse.setPhoto(user.getPhoto());
        }
        if (user.getBirthday() != null) {
            userResponse.setBirthday(user.getBirthday());
        }
        if (user.getUpdatedAt() != null) {
            userResponse.setUpdatedAt(user.getUpdatedAt());
        }
        if (user.getDeletedAt() != null) {
            userResponse.setDeletedAt(user.getDeletedAt());
        }
        return userResponse;
    }


    public int insert(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        if (userRequest.getPhoto() != null) {
            user.setPhoto(userRequest.getPhoto());
        }
        if (userRequest.getBirthday() != null) {
            user.setBirthday(userRequest.getBirthday());
        }
        return userRepository.insert(user);
    }

    public int update(UserPutRequest userPutRequest) {
        return userRepository.update(convertToUserUpdate(userPutRequest));
    }

    public UserResponse select(int id) {
        return convertToUserResponse(userRepository.select(id));
    }

    public int delete(int id) {
        return userRepository.delete(id);
    }

}
