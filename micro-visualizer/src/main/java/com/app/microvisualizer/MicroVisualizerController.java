package com.app.microvisualizer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/micro-visualizer")
public class MicroVisualizerController {

    private final MicroVisualizerService microVisualizerService;

    @GetMapping
    List<UserDto> getAllUsers(){
        return microVisualizerService.getAllUsers();
    }

}
