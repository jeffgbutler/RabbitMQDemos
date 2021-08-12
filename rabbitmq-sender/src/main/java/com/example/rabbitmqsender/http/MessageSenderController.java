package com.example.rabbitmqsender.http;

import com.example.rabbitmqsender.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MessageSenderController {

    private final MessageService messageService;

    public MessageSenderController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        messageService.sendMessage(message);
        return ResponseEntity.ok("OK");
    }
}
