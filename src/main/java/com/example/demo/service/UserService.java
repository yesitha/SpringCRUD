package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveUser(UserDTO userDTO) {
        if(userRepo.existsById(userDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else{
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public List<UserDTO> getAllUsers() {
        List<User> userList= userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public String updateUser(UserDTO userDto){
 if(userRepo.existsById(userDto.getId())){
     userRepo.save(modelMapper.map(userDto, User.class));
     return VarList.RSP_SUCCESS;
 }else{
 return  VarList.RSP_NO_DATA_FOUND;
 }




    }
    public String deleteUser(int userId) {
        if (userRepo.existsById(userId)) {
            userRepo.deleteById(userId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;


        }
    }

    public UserDTO searchUser(int userId){
        if(userRepo.existsById(userId)){
           User user = userRepo.findById(userId).orElse(null);
           return modelMapper.map(user,UserDTO.class);
        }else{
            return null;
        }
    }
}
