package com.ots.service;

import com.alipay.api.AlipayApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ots.controller.vo.AlipayVo;

public interface AlipayService {
    public String payOrder(AlipayVo alipayVo) throws JsonProcessingException, AlipayApiException;
    public String queryOrder(AlipayVo alipayVo) throws JsonProcessingException, AlipayApiException;

}
