package com.ots.controller;

import com.ots.entity.Order;
import com.ots.entity.User;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.OrderService;
import com.ots.utils.ContextUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order/")
public class OrderController {

    @RequestMapping(value = "submit")
    public String submitOrder(){
        return "submitorder";
    }

    @Autowired
    private OrderService orderService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public ResultBean<Order> createOrder(){
        ResultBean<Order> resultBean = GetResultBean.getResultBean();
        //目前测试先调用老师的接口.等补充完学生登陆模块，再修改
        User user = ContextUtil.getTeacherLoginInfo().getUser();
        Order order = this.orderService.addOrder(this.orderService.createOrder(23L,user.getUserId(),1L,37.2F));
        if(order != null){
            resultBean.setResult(200,"生成订单成功.",order);
        }else {
            resultBean.setResult(500,"生成订单出错.",null);
        }
        return resultBean;

    }

}
