package com.app.microrecipient;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/micro-recipient")
public class MicroRecipientController {

    private final MicroRecipientService microRecipientService;

    @GetMapping
    List<User> getMessages() {
        return microRecipientService.getAllUsers();
    }



}
