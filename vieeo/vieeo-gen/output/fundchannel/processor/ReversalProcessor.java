package com.shengpay.hpsc.core.domainservice.ccb.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.exceptions.ChannelException;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;

import com.shengpay.hpsc.core.domainservice.ccb.bean.ReversalRequest;
import com.shengpay.hpsc.core.domainservice.ccb.bean.ReversalResponse;

/**
 * description:
 * @author
 * time : 2012-5-17 下午2:55:43
 */
@Component("ccbReversalProcessor")
public class ReversalProcessor implements ChannelProcessor<ReversalRequest, ReversalResponse>{
    private static final Log logger = LogFactory.getLog(ReversalProcessor.class);

    @Override
    public ReversalResponse process(ReversalRequest request, FundChannel fundChannel) throws ChannelException {
        return null;
    }
}
