package com.shengpay.hpsc.core.domainservice.ccb.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sdo.cmf.common.domain.ChannelFundRequest;
import com.sdo.cmf.common.domain.ChannelFundResult;
import com.sdo.cmf.common.enums.FundChannelApiType;
import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.enums.ErrorCode;
import com.shengpay.fcf.core.domain.exceptions.presend.PreSendException;
import com.shengpay.fcf.core.domain.utils.ResultUtils;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;
import com.shengpay.fcf.core.domainservice.template.AbstractChannelService;

import com.shengpay.hpsc.core.domainservice.ccb.bean.CheckOutContent;
import com.shengpay.hpsc.core.domainservice.ccb.bean.CheckOutContent;

/**
 * 功能描述：登出服务实现
 * @author
 * time : 2012-8-8 上午10:50:50
 */
@Component("ccbCheckOutChannelService")
public class CheckOutChannelService extends
        AbstractChannelService<String, ChannelFundRequest, CheckOutContent, CheckOutContent, ChannelFundResult> {

    private static final Log logger = LogFactory.getLog(CheckOutChannelService.class);

    @Resource(name="ccbCheckOutProcessor")
    private ChannelProcessor<CheckOutContent, CheckOutContent> channelProcessor;

    @Override
    protected ChannelFundRequest preProcess(String request) throws Exception {
        return JSON.parseObject(request, ChannelFundRequest.class);
    }

    @Override
    protected String getFundChannelCode(ChannelFundRequest preProcessedRequest) {
        return preProcessedRequest.getFundChannelCode();
    }

    @Override
    protected void validate(ChannelFundRequest preProcessedRequest, FundChannel fundChannel) throws Exception {
    	if (preProcessedRequest.getApiType() == null) {
            throw new PreSendException(ErrorCode.REQUEST_VERIFY_FAILED, "apiType为空");
        }
    }

    @Override
    protected CheckOutContent convert(ChannelFundRequest request, FundChannel fundChannel) throws Exception {
        return null;
    }

    @Override
    protected ChannelProcessor<CheckOutContent, CheckOutContent> getChannelProcessor(CheckOutContent channelDomain,
            FundChannel fundChannel) {
        return channelProcessor;
    }

    @Override
    protected ChannelFundResult toResponse(CheckOutContent channelDomain, CheckOutContent channelResponse) throws Exception {
        return null;
    }

    @Override
    protected ChannelFundResult toResponse(Exception e) {
    	return ResultUtils.toChannelFundResult(e);
    }

}
