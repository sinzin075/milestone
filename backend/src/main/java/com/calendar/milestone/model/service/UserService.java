package com.calendar.milestone.model.service;

import com.calendar.milestone.model.entity.User;
import com.calendar.milestone.model.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public int insert(User user){
        return userRepository.insert(user);
    }

    public int update(User user){
        User existUser = userRepository.select(user.getId());
        if(user.getName()!=null){existUser.setName(user.getName());}
        if(user.getPhoto()!=null){existUser.setPhoto(user.getPhoto());}
        if(user.getBirthday()!=null){existUser.setBirthday(user.getBirthday());}
        if(user.getEmail()!=null){existUser.setEmail(user.getEmail());}
        return userRepository.update(existUser);
    }

        public User findById(int id){
        return userRepository.select(id);
    }

        public int delete(int id){
        return userRepository.delete(id);
    }



}