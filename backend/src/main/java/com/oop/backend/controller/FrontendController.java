package com.oop.backend.controller;

import com.oop.backend.entity.Configuration;
import com.oop.backend.model.TreadPool;
import com.oop.backend.service.ConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oop.backend.dto.MyRequest;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/frontend")
@CrossOrigin
@AllArgsConstructor
public class FrontendController {

    private Configuration config;
    @Autowired
    private ConfigurationService configurationService;

    private final TreadPool treadPool;



    @PostMapping("/startButton")
    public ResponseEntity<Object> startButton(@RequestBody MyRequest myRequest) {
        // Log the message received from the frontend
        System.out.println(myRequest.getMessage());
        if(myRequest.getMessage().equalsIgnoreCase("start")){
            treadPool.startThreadPool(configurationService.getConfig());
            System.out.println("Thread Started");
        }

        // Wrap the response message in a Map or use a custom response object
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from the backend");

        // Return the response as JSON
        return ResponseEntity.ok(response);
    }

    @PostMapping("/stopButton")
    public ResponseEntity<Object> stopButton(@RequestBody MyRequest request){
        System.out.println(request.getMessage());
        if (request.getMessage().equalsIgnoreCase("stop")){
            treadPool.stopAllThreads();
        }
        Map<String,String> response = new HashMap<>();
        response.put("Stop"," ");
        return ResponseEntity.ok(response);
    }


}
