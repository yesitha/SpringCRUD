package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/getUsers")
    public ResponseEntity getUsers(){

       try {
           List<UserDTO> userDTOList = userService.getAllUsers();
           responseDTO.setCode(VarList.RSP_SUCCESS);
           responseDTO.setMessage("Suceess");
           responseDTO.setContent(userDTOList);
           return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
       }catch (Exception e){
               responseDTO.setCode(VarList.RSP_ERROR);
               responseDTO.setMessage(e.getMessage());
               responseDTO.setContent(null);
               return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
           }
    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody UserDTO userDTO){
        try {
            String res=userService.saveUser(userDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Suceess");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("User Already Exists");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PutMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO){
    try {
        String res=userService.updateUser(userDTO);
        if(res.equals("00")){
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Suceess");
            responseDTO.setContent(userDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }else if(res.equals("01")){
            responseDTO.setCode(VarList.RSP_DUPLICATED);
            responseDTO.setMessage("User Not Exists");
            responseDTO.setContent(userDTO);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }else{
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
        catch (Exception e){
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(e.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId){
        try {
            String res =userService.deleteUser(userId);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Suceess");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("User Not Exists");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchUser/{userID}")
    public ResponseEntity searchUser(@PathVariable int userID){
        try {
            UserDTO userDTO =userService.searchUser(userID);
            if(userDTO!=null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Suceess");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("User Not Exists");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
