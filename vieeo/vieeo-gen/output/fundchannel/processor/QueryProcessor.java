package com.shengpay.hpsc.core.domainservice.ccb.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.exceptions.ChannelException;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;

import com.shengpay.hpsc.core.domainservice.ccb.bean.QueryRequest;
import com.shengpay.hpsc.core.domainservice.ccb.bean.QueryResponse;

/**
 * description:
 * @author
 * time : 2012-5-17 下午2:55:43
 */
@Component("ccbQueryProcessor")
public class QueryProcessor implements ChannelProcessor<QueryRequest, QueryResponse>{
    private static final Log logger = LogFactory.getLog(QueryProcessor.class);

    @Override
    public QueryResponse process(QueryRequest request, FundChannel fundChannel) throws ChannelException {
        return null;
    }
}
