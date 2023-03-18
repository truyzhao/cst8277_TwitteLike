package com.truby.cst8277.controller;


import com.truby.cst8277.dto.request.AddMsgRequestDto;
import com.truby.cst8277.dto.request.UpdateMsgRequestDto;
import com.truby.cst8277.dto.response.MsgDetailsResponseDto;
import com.truby.cst8277.dto.response.MessageResponseDto;
import com.truby.cst8277.entities.Message;
import com.truby.cst8277.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("message")
public class MessageController {
    private MessageService messageService;

    @PostMapping("add")
    public ResponseEntity<MessageResponseDto> add(@RequestBody AddMsgRequestDto addMsgRequestDto) {
        return new ResponseEntity<>(messageService.add(addMsgRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("deleteMessage")
    public ResponseEntity<String> deleteTweet(@RequestParam(name = "messageId") UUID messageId) {
        return new ResponseEntity<>(messageService.deleteMessage(messageId), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody UpdateMsgRequestDto updateMsgRequestDto) {
        return new ResponseEntity<>(messageService.update(updateMsgRequestDto), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<MessageResponseDto>> findAll(){
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<MsgDetailsResponseDto> MessageDetail(@RequestParam(name = "messageId") UUID messageId){
        return new ResponseEntity<>(messageService.detail(messageId),HttpStatus.OK);
    }
    @GetMapping("byProducer")
    public ResponseEntity<List<Message>> MessageByProducer(@RequestParam(name = "userId") UUID userId){
        return new ResponseEntity<>(messageService.getByUser(userId), HttpStatus.OK);
    }
}
