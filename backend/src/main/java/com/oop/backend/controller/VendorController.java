package com.oop.backend.controller;
import com.oop.backend.entity.Vendor;
import com.oop.backend.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
@CrossOrigin

public class VendorController {
    @Autowired
    public VendorService vendorService;

    @PostMapping("/saveVendor")
    public Vendor saveVendor(@RequestBody Vendor vendor){
        return vendorService.vendorSave(vendor);
    }


    /**
     * @return  List of all vendors
     */
//    @GetMapping("/getAllVendor")
//    public List<VendorDTO> getVendor(){
//        return vendorService.getAllVendors();
//    }
//
//    @GetMapping("/getVendor")
//    public VendorDTO getVendor (@PathVariable"vendorID") Integer vendorID){
//        return vendorService.getVendor(vendorID);
//    }

}
