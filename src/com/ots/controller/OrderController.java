package com.ots.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.controller.vo.AlipayVo;
import com.ots.entity.Order;
import com.ots.entity.User;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.AlipayService;
import com.ots.service.OrderService;
import com.ots.utils.ContextUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order/")
public class OrderController {

    @RequestMapping(value = "submit")
    public String submitOrder(){
        return "submitorder";
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/queryOrders")
    @ResponseBody
    public ResultBean<List<Order>> createOrder(){
        ResultBean<List<Order>> resultBean = GetResultBean.getResultBean();
        //目前测试先调用老师的接口.等补充完学生登陆模块，再修改
        User user = ContextUtil.getUserLoginInfo().getUser();
        Order orderCondition = new Order();
        orderCondition.setStudentId(user.getUserId());
        List<Order> orders = this.orderService.showOrders(orderCondition);
        resultBean.setResult(200,"查询订单成功.",orders);
        return resultBean;
    }


    @RequestMapping("/changeOrderStatus")
    @ResponseBody
    public ResultBean<String> changeOrderStatus(String orderId) {
        ResultBean<String> result = GetResultBean.getResultBean();
        try {
            AlipayVo alipayVo = new AlipayVo();
            alipayVo.setOut_trade_no(orderId);
            Order orderCondition = new Order();

            ObjectMapper mapper = new ObjectMapper();
            Map map=mapper.readValue(this.alipayService.queryOrder(alipayVo), Map.class);

            Map resultMap1 = (Map) map.get("alipay_trade_query_response");

            if(resultMap1.get("code").equals("10000")){
                orderCondition.setOrderId(Long.valueOf(orderId));
                orderCondition.setIsPay(1);
                this.orderService.updateOrder(orderCondition);
                result.setResult(200,"订单已支付.","order_status=1");
                return result;
            }
            result.setResult(500,"订单未支付.","order_status=0");
            return result;

        } catch (Exception e) {
            return result;
        }
    }

}
