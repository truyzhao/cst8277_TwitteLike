package com.truby.cst8277.service;

import com.truby.cst8277.entities.Follower;
import com.truby.cst8277.entities.User;
import com.truby.cst8277.exception.CantFollow;
import com.truby.cst8277.exception.NotFoundUserName;
import com.truby.cst8277.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SubscriptionService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public void addOrUnf(UUID proId, UUID subId) {
        if (Objects.equals(proId, subId)) {
            throw new CantFollow();
        }
        User user = userRepository.findById(subId).orElseThrow(NotFoundUserName::new);
        Follower follower = modelMapper.map(userRepository.findById(proId), Follower.class);
        Follower sub1 = user.getFollowers().stream().filter(item -> Objects.equals(item.getId(),
                follower.getId())).findAny().orElse(null);
        if (sub1 != null) {
            user.unFollower(sub1);
        } else {

            user.addFollower(follower);
        }
        userRepository.save(user);
    }
}
