package com.truby.cst8277.service;


import com.truby.cst8277.dto.request.UpdateMsgRequestDto;
import com.truby.cst8277.dto.request.AddMsgRequestDto;
import com.truby.cst8277.dto.response.MsgDetailsResponseDto;
import com.truby.cst8277.dto.response.MessageResponseDto;
import com.truby.cst8277.entities.Message;
import com.truby.cst8277.exception.NotFoundMessageID;
import com.truby.cst8277.exception.MessageProm;
import com.truby.cst8277.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {
    private MessageRepository msgRep;
    private UserService userService;
    private ModelMapper modelMapper;

    public MessageResponseDto add(AddMsgRequestDto addMsgRequestDto) {
        Message message = new Message();
        message.setContent(addMsgRequestDto.getContent());
        message.setUser(userService.findById(addMsgRequestDto.getUserId()));
        //Message message = modelMapper.map(addMsgRequestDto, Message.class);
        msgRep.save(message);
        return modelMapper.map(message, MessageResponseDto.class);
    }

    public Message findById(UUID id) {
        return msgRep.findById(id).orElseThrow(NotFoundMessageID::new);
    }

    public String deleteMessage(UUID messageId) {
        msgRep.deleteById(messageId);
        return !msgRep.existsById(messageId) ? MessageProm.SUCCESSFULLY_DELETED : MessageProm.SOMETHING_WENT_WRONG;
    }

    public String update(UpdateMsgRequestDto updateMsgRequestDto) {
        Message message = msgRep.findById(updateMsgRequestDto.getMessageId()).orElseThrow(NotFoundMessageID::new);
        message.setContent(updateMsgRequestDto.getContent());
        msgRep.save(message);
        return MessageProm.SUCCESSFULLY_UPDATED;
    }


    public List<Message> getAll() {
        return msgRep.findAll();
    }

    public List<Message> getByUser(UUID userId) {
        return msgRep.findByUserId(userId);    }
    public List<MessageResponseDto> findAll() {
        return msgRep.findAll().stream().map(item -> modelMapper.map(item, MessageResponseDto.class)).toList();
    }

    public MsgDetailsResponseDto detail(UUID messageId) {
        Message message = msgRep.findById(messageId).orElseThrow(NotFoundMessageID::new);
        MsgDetailsResponseDto msgDetailsResponseDto = modelMapper.map(message, MsgDetailsResponseDto.class);
        msgDetailsResponseDto.setProducerName(message.getUser().getUserName());
        return msgDetailsResponseDto;
    }
}
