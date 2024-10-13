package lk.ijse.NoteTakerV2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/healthCheck")
public class HealthTestController {
    @GetMapping
    public String healthTest(){
        return "Note Collector App run Successfully !!" ;
    }

}
