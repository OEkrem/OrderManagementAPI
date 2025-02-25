package com.oekrem.SpringMVCBackEnd.utils;

import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0 2 * * ?")  // Bu görev her gün 2'de çalışacak
    public void removeOldOrders(){
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);
        List<Order> oldOrders = orderService.findByOrdersDateBefore(threeYearsAgo);
        if(oldOrders.isEmpty()){
            orderService.deleteAllOrders(oldOrders);
        }
    }

}
