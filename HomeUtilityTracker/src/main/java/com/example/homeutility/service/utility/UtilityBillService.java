package com.example.homeutility.service.utility;

import java.util.List;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.UtilityBill;

public interface UtilityBillService {

	void saveBill(UtilityBill bill);
    List<UtilityBill> getBillsByUser(User user);
    
    UtilityBill getBillById(Long id);
    void deleteBill(Long id);

}
