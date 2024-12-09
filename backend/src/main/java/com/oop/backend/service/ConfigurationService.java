package com.oop.backend.service;
import com.oop.backend.entity.Configuration;
import com.oop.backend.repo.ConfigurationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class ConfigurationService {

    private ConfigurationRepo configRepo;

    @Autowired
    public ConfigurationService(ConfigurationRepo configRepo) {
        this.configRepo = configRepo;
    }

    public Configuration saveConfiguration(Configuration config) {
        return configRepo.save(config);
    }

    public Configuration getConfig() {
        return configRepo.findLastRow();
    }


}
