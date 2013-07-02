package com.shengpay.hpsc.core.domainservice.ccb.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.exceptions.ChannelException;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;

import com.shengpay.hpsc.core.domainservice.ccb.bean.PreAuthRequest;
import com.shengpay.hpsc.core.domainservice.ccb.bean.PreAuthResponse;

/**
 * description:
 * @author
 * time : 2012-5-17 下午2:55:43
 */
@Component("ccbPreAuthProcessor")
public class PreAuthProcessor implements ChannelProcessor<PreAuthRequest, PreAuthResponse>{
    private static final Log logger = LogFactory.getLog(PreAuthProcessor.class);

    @Override
    public PreAuthResponse process(PreAuthRequest request, FundChannel fundChannel) throws ChannelException {
        return null;
    }
}
