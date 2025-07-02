package com.example.homeutility.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.UtilityBill;
import com.example.homeutility.repository.utility.UtilityBillRepository;

@Service
public class UtilityBillServiceImpl implements UtilityBillService {

    @Autowired
    private UtilityBillRepository billRepository;

    @Override
    public void saveBill(UtilityBill bill) {
        billRepository.save(bill);
    }

    @Override
    public List<UtilityBill> getBillsByUser(User user) {
        return billRepository.findByUser(user);
    }
    
    @Override
    public UtilityBill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

}
