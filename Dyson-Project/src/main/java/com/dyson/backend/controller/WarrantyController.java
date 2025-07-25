package com.dyson.backend.controller;

import com.dyson.backend.model.Warranty;
import com.dyson.backend.service.WarrantyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    @PostMapping
    public ResponseEntity<Warranty> createWarranty(@RequestBody Warranty warranty) {
        Warranty created = warrantyService.registerWarranty(warranty);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warranty> getWarranty(@PathVariable("id") Long id) {
        Warranty warranty = warrantyService.getWarrantyById(id);
        return ResponseEntity.ok(warranty);
    }

    @GetMapping
    public ResponseEntity<List<Warranty>> getAllWarranties() {
        List<Warranty> warranties = warrantyService.getAllWarranties();
        return ResponseEntity.ok(warranties);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warranty> updateWarranty(@PathVariable("id") Long id, @RequestBody Warranty warranty) {
        Warranty updated = warrantyService.updateWarranty(id, warranty);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable("id") Long id) {
        warrantyService.deleteWarranty(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/validity")
    public ResponseEntity<String> checkWarrantyValidity(@PathVariable("id") Long id) {
        Warranty warranty = warrantyService.getWarrantyById(id);

        if (warranty.getWarrantyExpiryDate() == null) {
            return new ResponseEntity<>("Warranty expiry date not set.", HttpStatus.BAD_REQUEST);
        }

        LocalDate expiryDate = warranty.getWarrantyExpiryDate();
        LocalDate today = LocalDate.now();

        if (expiryDate.isBefore(today)) {
            return ResponseEntity.ok("Product warranty has expired.");
        }

        long daysLeft = ChronoUnit.DAYS.between(today, expiryDate);

        if (daysLeft < 30) {
            return ResponseEntity.ok("Product is within warranty, but it expires in less than 1 month.");
        }

        return ResponseEntity.ok("Product is within warranty.");
    }

}
