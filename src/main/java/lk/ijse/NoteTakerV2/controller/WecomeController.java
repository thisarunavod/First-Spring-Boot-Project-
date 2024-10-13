package lk.ijse.NoteTakerV2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/welcome")
public class WecomeController {
    @GetMapping
    public String welcome(Model model){

        model.addAttribute(
                "message",
                "Welcome to Note Taker 2024"
        );

        return "Welcome";
    }


}
