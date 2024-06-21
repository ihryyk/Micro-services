package com.app.microsender;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/micro-sender")
public class MicroSenderController {

    private final MicroSenderService microSenderService;

    @PostMapping
    public void sendMessage(@RequestBody MicroSenderMessage microSenderMessage) {
        microSenderService.sendMessage(microSenderMessage);
    }

}
