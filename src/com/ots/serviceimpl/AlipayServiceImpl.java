package com.ots.serviceimpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.alipay.AlipayConfig;
import com.ots.controller.vo.AlipayVo;
import com.ots.service.AlipayService;
import org.springframework.stereotype.Service;

@Service
public class AlipayServiceImpl implements AlipayService {
    @Override
    public String payOrder(AlipayVo alipayVo) throws JsonProcessingException, AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url+"?referOrderId="+alipayVo.getOut_trade_no());
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        ObjectMapper mapper = new ObjectMapper();
        String alipayVoString = "";
        alipayVoString = mapper.writeValueAsString(alipayVo);
        alipayRequest.setBizContent(alipayVoString);
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @Override
    public String queryOrder(AlipayVo alipayVo) throws JsonProcessingException, AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        ObjectMapper mapper = new ObjectMapper();
        String alipayVoString = "";
        alipayVoString = mapper.writeValueAsString(alipayVo);
        alipayRequest.setBizContent(alipayVoString);
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }
}