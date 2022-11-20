package com.example.producer.controller;

import com.example.producer.domain.Message;
import com.example.producer.domain.User;
import com.example.producer.service.MessageSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController
{
    MessageSender sender;

    @PutMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message)
    {
        sender.sendMessage(message.getMessage());
        return ResponseEntity.ok(message);
    }
}
