package lk.ijse.NoteTakerV2.controller;

import lk.ijse.NoteTakerV2.Util.AppUtil;
import lk.ijse.NoteTakerV2.customObj.UserResponse;
import lk.ijse.NoteTakerV2.dto.impl.UserDTO;
import lk.ijse.NoteTakerV2.exeption.DataPersistFailedException;
import lk.ijse.NoteTakerV2.exeption.UserNotFoundException;
import lk.ijse.NoteTakerV2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v2/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    //Save User
    @GetMapping("health")
    public String healthChecker(){ return "Runnng Perfectly";}

    @PostMapping (consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("password") String password,
            @RequestPart("email") String email,
            @RequestPart("profilePic") MultipartFile profilePic) {

        try {
            // Handle profile picture
            byte[] imageByteCollection = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection); /* <--- converting to base64 format*/

            // build the user
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setPassword(password);
            buildUserDTO.setEmail(email);
            buildUserDTO.setProfilePic(base64ProfilePic);

            //Send to service layer
            userService.saveUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
         try {
             userService.deleteUser(userId);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         } catch (UserNotFoundException e) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         } catch (Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable("id") String userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser(){
        return userService.getAllUser();
    }

    @PatchMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser( @PathVariable("id") String userId ,
                                              @RequestPart("firstName") String updateFirstName,
                                              @RequestPart("lastName") String updateLastName,
                                              @RequestPart("password") String updatePassword,
                                              @RequestPart("email") String updateEmail,
                                              @RequestPart("profilePic") MultipartFile updateProfilePic){

        try {
            byte[] imageByteCollection = updateProfilePic.getBytes();
            String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection);
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userId);
            userDTO.setFirstName(updateFirstName);
            userDTO.setLastName(updateLastName);
            userDTO.setPassword(updatePassword);
            userDTO.setEmail(updateEmail);
            userDTO.setProfilePic(updateBase64ProfilePic);

            userService.updateUser(userId,userDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
