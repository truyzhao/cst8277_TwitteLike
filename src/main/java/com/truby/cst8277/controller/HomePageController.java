package com.truby.cst8277.controller;

import com.truby.cst8277.entities.Message;
import com.truby.cst8277.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping()
public class HomePageController {
    private HomeService homeService;

    @GetMapping("")
    public List<Message> showMyPage(@RequestParam(name = "userId") UUID userId){
        return homeService.myHomePage(userId);}
}
