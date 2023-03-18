package com.truby.cst8277.service;

import com.truby.cst8277.entities.Follower;
import com.truby.cst8277.entities.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class HomeService {
    private MessageService messageService;
    public List<Message> myHomePage(UUID userId) {
        List<Message> myMessages = messageService.getByUser(userId);
        Set<Follower> subUser = new HashSet<>();
        Iterator<Follower> iterator = subUser.iterator();
        while (iterator.hasNext()) {
            Follower next = iterator.next();
            myMessages.addAll(messageService.getByUser(next.getId()));
        }
        return myMessages;
    }
}
