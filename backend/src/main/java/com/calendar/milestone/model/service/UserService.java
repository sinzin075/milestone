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
    private final AuthService authService;
    private final int STATUS_CHANGE_SUCCESS = 1;


    public UserService(UserRepository userRepository , AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
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

    /**
     * ユーザ登録用(ログイン用のJWTトークン発行まで行う)
     * @param userPostRequest
     * @return
     * @exception IllegalArgumentException
     */
    public UserPostResponse insert(UserPostRequest userPostRequest) throws IllegalArgumentException{
        User user = new User();
        final Email email = Email.of(userPostRequest.getEmail());
        user.setName(userPostRequest.getName());
        user.setEmail(email);
        user.setPassword(Password.encode(userPostRequest.getPassword()));
        if (userPostRequest.getPhoto() != null) {
            user.setPhoto(userPostRequest.getPhoto());
        }
        if (userPostRequest.getBirthday() != null) {
            user.setBirthday(userPostRequest.getBirthday());
        }
        final int updateRows = userRepository.insert(user);
        if(updateRows != STATUS_CHANGE_SUCCESS){
            //TODO:更新が正常にできなかった場合の例外をどれにするか再検討する必要あり。
            //戻り値が想定外の場合どのような例外を使用するのか
            throw new IllegalArgumentException("User registration was not completed successfully.");
        }
        //response用のデータ作成
        final LoginResponse loginResponse = authService.issueLoginToken(userPostRequest);
        final UserId userId = userRepository.selectLoginUserId(email);
        final User registUser = userRepository.select(userId.getValue());
        final UserPostResponse userPostResponse = UserPostResponse.from(loginResponse,registUser);
        return userPostResponse;
    }


    /**
     * ユーザ情報の更新(name/photo/birthdayのみ)
     * @param userPutRequest
     * @return DBから更新後のデータを取得
     */
    public UserPutResponse update(UserPutRequest userPutRequest)throws IllegalArgumentException {
        if(userRepository.update(convertToUserUpdate(userPutRequest)) != STATUS_CHANGE_SUCCESS){
            throw new IllegalArgumentException("The update did not complete successfully.");
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
