package com.dyson.backend.service.impl;

import com.dyson.backend.model.Warranty;
import com.dyson.backend.model.WarrantyStatus;
import com.dyson.backend.service.WarrantyService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    private final Map<Long, Warranty> warrantyStore = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Warranty registerWarranty(Warranty warranty) {
        Long id = idCounter.getAndIncrement();
        warranty.setId(id);
        updateWarrantyStatus(warranty);
        warrantyStore.put(id, warranty);
        return warranty;
    }

    @Override
    public Warranty getWarrantyById(Long id) {
        Warranty warranty = warrantyStore.get(id);
        if (warranty == null) {
            throw new NoSuchElementException("Warranty not found for id " + id);
        }
        return warranty;
    }

    @Override
    public List<Warranty> getAllWarranties() {
        return new ArrayList<>(warrantyStore.values());
    }

    @Override
    public Warranty updateWarranty(Long id, Warranty warranty) {
        Warranty existing = warrantyStore.get(id);
        if (existing == null) {
            throw new NoSuchElementException("Warranty not found for id " + id);
        }
        existing.setProductName(warranty.getProductName());
        existing.setCustomerName(warranty.getCustomerName());
        existing.setPurchaseDate(warranty.getPurchaseDate());
        existing.setWarrantyExpiryDate(warranty.getWarrantyExpiryDate());
        updateWarrantyStatus(existing);
        warrantyStore.put(id, existing);
        return existing;
    }

    @Override
    public void deleteWarranty(Long id) {
        Warranty removed = warrantyStore.remove(id);
        if (removed == null) {
            throw new NoSuchElementException("Warranty not found for id " + id);
        }
    }

    private void updateWarrantyStatus(Warranty warranty) {
        if (warranty.getWarrantyExpiryDate() != null && warranty.getWarrantyExpiryDate().isAfter(LocalDate.now())) {
            warranty.setStatus(WarrantyStatus.ACTIVE);
        } else {
            warranty.setStatus(WarrantyStatus.EXPIRED);
        }
    }
}
