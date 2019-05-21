package com.ots.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.alipay.AlipayConfig;
import com.ots.controller.vo.AlipayVo;
import com.ots.entity.Order;
import com.ots.resultbean.ResultBean;
import com.ots.service.AlipayService;
import com.ots.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alipay/")
public class AlipayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/payview/{jspPageName}")
    public String index(@PathVariable String jspPageName) {
        return "/alipay/" + jspPageName;
    }

    @RequestMapping("/payOrder")
    @ResponseBody
    public String payOrder(String orderId) {
        Order orderCondition = new Order();

        orderCondition.setOrderId(Long.valueOf(orderId));
        try {
            Order order = this.orderService.showOrders(orderCondition).get(0);
            if (order == null) {
                return null;
            }
            String orderPrice = order.getPrice().toString();
            String orderName = "ots-order0000" + orderId;
            AlipayVo alipayVo = new AlipayVo();
            alipayVo.setBody("");
            alipayVo.setOut_trade_no(orderId);
            alipayVo.setTotal_amount(orderPrice);
            alipayVo.setProduct_code("FAST_INSTANT_TRADE_PAY");
            alipayVo.setSubject(orderName);
            return this.alipayService.payOrder(alipayVo);

        } catch (Exception e) {
            return null;

        }
    }

    @RequestMapping("/queryPay")
    @ResponseBody
    public String changePayStatus(String orderId) {
        try {
            AlipayVo alipayVo = new AlipayVo();
            alipayVo.setOut_trade_no(orderId);
            return this.alipayService.queryOrder(alipayVo);
        } catch (Exception e) {
            return null;
        }
    }
}
