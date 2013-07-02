package com.shengpay.hpsc.core.domainservice.ccb.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.exceptions.ChannelException;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;

import com.shengpay.hpsc.core.domainservice.ccb.bean.DebitRequest;
import com.shengpay.hpsc.core.domainservice.ccb.bean.DebitResponse;

/**
 * description:
 * @author
 * time : 2012-5-17 下午2:55:43
 */
@Component("ccbDebitProcessor")
public class DebitProcessor implements ChannelProcessor<DebitRequest, DebitResponse>{
    private static final Log logger = LogFactory.getLog(DebitProcessor.class);

    @Override
    public DebitResponse process(DebitRequest request, FundChannel fundChannel) throws ChannelException {
        return null;
    }
}
