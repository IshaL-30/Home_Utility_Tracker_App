package com.example.homeutility.repository.utility;

import com.example.homeutility.model.utility.UtilityBill;
import com.example.homeutility.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UtilityBillRepository extends JpaRepository<UtilityBill, Long> {
    List<UtilityBill> findByUser(User user);
}
