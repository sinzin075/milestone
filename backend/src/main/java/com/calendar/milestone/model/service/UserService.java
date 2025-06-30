package com.calendar.milestone.model.service;

import com.calendar.milestone.controller.dto.request.user.UserEmailChangeRequest;
import com.calendar.milestone.controller.dto.request.user.UserPasswordChangeRequest;
import com.calendar.milestone.controller.dto.request.user.UserPostRequest;
import com.calendar.milestone.controller.dto.request.user.UserPutRequest;
import com.calendar.milestone.controller.dto.response.common.ApiStatus;
import com.calendar.milestone.controller.dto.response.user.UserApiStatusResponse;
import com.calendar.milestone.controller.dto.response.user.UserResponse;
import com.calendar.milestone.model.entity.User;
import com.calendar.milestone.model.repository.UserRepository;
import com.calendar.milestone.model.value.Password;
import com.calendar.milestone.model.value.RawPassword;
import com.calendar.milestone.model.value.Email;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final int STATUS_CHANGE_SUCCESS = 1;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User convertToUserUpdate(UserPutRequest userPutRequest) {
        User user = userRepository.select(userPutRequest.getId());
        if (userPutRequest.getName() != null) {
            user.setName(userPutRequest.getName());
        }
        if (userPutRequest.getEmail() != null) {
            user.setEmail(Email.of(userPutRequest.getEmail()));
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
        userResponse.setEmail(user.getEmailObject().getValue());
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

    public int insert(UserPostRequest userPostRequest) {
        User user = new User();
        user.setName(userPostRequest.getName());
        user.setEmail(Email.of(userPostRequest.getEmail()));
        user.setPassword(Password.encode(userPostRequest.getPassword()));
        if (userPostRequest.getPhoto() != null) {
            user.setPhoto(userPostRequest.getPhoto());
        }
        if (userPostRequest.getBirthday() != null) {
            user.setBirthday(userPostRequest.getBirthday());
        }
        return userRepository.insert(user);
    }

    public int update(UserPutRequest userPutRequest) {
        return userRepository.update(convertToUserUpdate(userPutRequest));
    }

    public UserApiStatusResponse emailUpdate(final UserEmailChangeRequest userEmail)
            throws IllegalArgumentException {
        RawPassword rawPassword = RawPassword.of(userEmail.getPassword());
        String usagePassword = userRepository.findPassword(Email.of(userEmail.getCurrentEmail()));
        if (!rawPassword.passwordMatch(usagePassword)) {
            return new UserApiStatusResponse(ApiStatus.UNAUTHORIZED);
        }
        if (userRepository.updateEmail(userEmail.getId(),
                Email.of(userEmail.getNewEmail())) != STATUS_CHANGE_SUCCESS) {
            return new UserApiStatusResponse(ApiStatus.INTERNAL_SERVER_ERROR);
        }
        return new UserApiStatusResponse(ApiStatus.OK);
    }

    public UserApiStatusResponse passwordUpdate(final UserPasswordChangeRequest user)
            throws IllegalArgumentException {
        RawPassword rawPassword = RawPassword.of(user.getCurrentPassword());
        String usagePassword = userRepository.findPassword(Email.of(user.getEmail()));
        if (!rawPassword.passwordMatch(usagePassword)) {
            return new UserApiStatusResponse(ApiStatus.UNAUTHORIZED);
        }
        Password newPassword = Password.encode(user.getNewPassword());
        if (userRepository.updatePassword(user.getId(), newPassword) != STATUS_CHANGE_SUCCESS) {
            return new UserApiStatusResponse(ApiStatus.INTERNAL_SERVER_ERROR);
        }
        return new UserApiStatusResponse(ApiStatus.OK);
    }

    public UserResponse select(int id) {
        return convertToUserResponse(userRepository.select(id));
    }

    public int delete(int id) {
        return userRepository.delete(id);
    }

}
