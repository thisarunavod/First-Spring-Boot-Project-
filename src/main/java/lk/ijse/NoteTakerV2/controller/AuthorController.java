package lk.ijse.NoteTakerV2.controller;

import lk.ijse.NoteTakerV2.Util.AppUtil;
import lk.ijse.NoteTakerV2.dto.impl.UserDTO;
import lk.ijse.NoteTakerV2.exeption.DataPersistFailedException;
import lk.ijse.NoteTakerV2.jwtModels.JwtAuthResponse;
import lk.ijse.NoteTakerV2.jwtModels.SignIn;
import lk.ijse.NoteTakerV2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2/auth")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value = "signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JwtAuthResponse> signUp(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("password") String password,
            @RequestPart("email") String email,
            @RequestPart("profilePic") MultipartFile profilePic,
            @RequestPart("Role") String role) {

        try {
            // Handle profile picture
            byte[] imageByteCollection = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(imageByteCollection); /* <--- converting to base64 format*/

            // build the user
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(AppUtil.createUserID());
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(passwordEncoder.encode(password));
            buildUserDTO.setProfilePic(base64ProfilePic);
            buildUserDTO.setRole(role);

            //Send to service layer
            /*authenticationService.saveUser(buildUserDTO);*/
            return ResponseEntity.ok(authenticationService.signUp(buildUserDTO));

        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn){

        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

}
