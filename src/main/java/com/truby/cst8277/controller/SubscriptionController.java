package com.truby.cst8277.controller;

import com.truby.cst8277.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("follow")
public class SubscriptionController {
    private SubscriptionService followService;

    @PostMapping("add")
    public ResponseEntity addOrUnf(@RequestParam(name = "producer") UUID producerId, @RequestParam(name = "subscriber") UUID subscriberId) {
        followService.addOrUnf(producerId, subscriberId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
