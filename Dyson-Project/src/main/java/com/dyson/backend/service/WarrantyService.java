package com.dyson.backend.service;

import com.dyson.backend.model.Warranty;
import java.util.List;

public interface WarrantyService {

    Warranty registerWarranty(Warranty warranty);

    Warranty getWarrantyById(Long id);

    List<Warranty> getAllWarranties();

    Warranty updateWarranty(Long id, Warranty warranty);

    void deleteWarranty(Long id);
}
