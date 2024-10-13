package lk.ijse.NoteTakerV2.controller;

import lk.ijse.NoteTakerV2.Util.AppUtil;
import lk.ijse.NoteTakerV2.dto.impl.UserDTO;
import lk.ijse.NoteTakerV2.exeption.DataPersistFailedException;
import lk.ijse.NoteTakerV2.jwtModels.JWTResponse;
import lk.ijse.NoteTakerV2.jwtModels.SignIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2/auth")
public class AuthorController {

    @PostMapping(value = "signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTResponse> signUp(
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
    @PostMapping(value = "signIn")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn){
        return null;
    }
}
