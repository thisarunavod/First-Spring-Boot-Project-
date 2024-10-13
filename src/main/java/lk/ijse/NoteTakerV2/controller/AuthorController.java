package lk.ijse.NoteTakerV2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/auth")
public class AuthorController {

    @PostMapping(value = "signIn")
    public String signIn(){
        return "Sign In";
    }
    @PostMapping(value = "signUp")
    public String signUp(){
        return "Sign Up";
    }
}
