package com.ots.serviceimpl;

import com.ots.dao.OrderDao;
import com.ots.entity.Order;
import com.ots.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Long teacherId, Long studentId, Long courseId, Float price) {
        Order order = new Order();
        order.setCourseId(courseId);
        order.setPrice(price);
        order.setStudentId(studentId);
        order.setTeacherId(teacherId);
        order.setTeacherMoney(0.80F*price);
        order.setPlatformMoney(0.20F*price);
        return order;
    }

    @Override
    public List<Order> showOrders(Order order) {
        return this.orderDao.select(order);
    }

    @Override
    public Order addOrder(Order order) {
        int flag =  this.orderDao.insert(order);
        if(flag==1){
            return order;
        }
        return null;
    }
}
