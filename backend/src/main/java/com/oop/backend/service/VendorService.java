package com.oop.backend.service;
import com.oop.backend.entity.Vendor;
import com.oop.backend.repo.VendorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VendorService {
    private  final VendorRepository vendorRepository;


    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;

    }

    public Vendor vendorSave(Vendor vendor){
        return vendorRepository.save(vendor);

    }


}
