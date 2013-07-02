package com.shengpay.hpsc.core.domainservice.ccb.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.exceptions.ChannelException;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;

import com.shengpay.hpsc.core.domainservice.ccb.bean.CheckInContent;
import com.shengpay.hpsc.core.domainservice.ccb.bean.CheckInContent;

/**
 * description:
 * @author
 * time : 2012-5-17 下午2:55:43
 */
@Component("ccbCheckInProcessor")
public class CheckInProcessor implements ChannelProcessor<CheckInContent, CheckInContent>{
    private static final Log logger = LogFactory.getLog(CheckInProcessor.class);

    @Override
    public CheckInContent process(CheckInContent request, FundChannel fundChannel) throws ChannelException {
        return null;
    }
}
