package com.shengpay.hpsc.core.domainservice.ccb.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.sdo.cmf.common.enums.FundChannelApiType;
import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.enums.ErrorCode;
import com.shengpay.fcf.core.domain.exceptions.presend.PreSendException;
import com.shengpay.fcf.core.domain.utils.ResultUtils;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;
import com.shengpay.fcf.core.domainservice.template.AbstractChannelService;

import com.shengpay.hpsc.core.domainservice.ccb.bean.AuthenticateRequest;
import com.shengpay.hpsc.core.domainservice.ccb.bean.AuthenticateResponse;

/**
 * 功能描述：鉴权服务实现
 * @author hehuaiyuan.roy
 * time : 2012-8-8 上午10:50:50
 */
@Component("ccbAuthChannelService")
public class AuthChannelService extends
        AbstractChannelService<AuthenticateRequest, AuthenticateRequest, AuthenticateRequest, AuthenticateResponse, AuthenticateResponse>{

    private static final Log logger = LogFactory.getLog(AuthChannelService.class);

    @Resource(name="ccbAuthProcessor")
    private ChannelProcessor<AuthenticateRequest, AuthenticateResponse> channelProcessor;

    @Override
    protected AuthenticateRequest preProcess(AuthenticateRequest request) throws Exception {
        return request;
    }

    @Override
    protected String getFundChannelCode(AuthenticateRequest preProcessedRequest) {
        return preProcessedRequest.getFundChannelCode();
    }

    @Override
    protected void validate(AuthenticateRequest preProcessedRequest, FundChannel fundChannel) throws Exception {
    	if (preProcessedRequest.getApiType() == null) {
            throw new PreSendException(ErrorCode.REQUEST_VERIFY_FAILED, "apiType为空");
        }
    }

    @Override
    protected AuthenticateRequest convert(AuthenticateRequest request, FundChannel fundChannel) throws Exception {
        return null;
    }

    @Override
    protected ChannelProcessor<AuthenticateRequest, AuthenticateResponse> getChannelProcessor(AuthenticateRequest channelDomain,
            FundChannel fundChannel) {
        return channelProcessor;
    }

    @Override
    protected AuthenticateResponse toResponse(AuthenticateRequest channelDomain, AuthenticateResponse channelResponse) throws Exception {
        return null;
    }

    @Override
    protected AuthenticateResponse toResponse(Exception e) {
    	return ResultUtils.toAuthenticateResponse(e);
    }

}
