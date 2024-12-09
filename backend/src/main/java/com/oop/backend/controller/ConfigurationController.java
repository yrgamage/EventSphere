package com.oop.backend.controller;
import com.oop.backend.entity.Configuration;
import com.oop.backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
@CrossOrigin


public class ConfigurationController {
    @Autowired
    private ConfigurationService configService;

    @GetMapping("/getPara")
    public ResponseEntity<Configuration> getConfiguration(){
        Configuration config = configService.getConfig();
        return ResponseEntity.ok(config);
    }

    @PostMapping("/postPara")
    public Configuration configurationSave(@RequestBody Configuration config){

        return configService.saveConfiguration(config);


    }



}
