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
import com.sdo.schema.cmf.fc.channel.query.request.QueryRequest;
import com.sdo.schema.cmf.fc.channel.query.response.QueryResponse;
import com.sdo.cmf.common.enums.QueryType;

import com.shengpay.hpsc.core.domainservice.ccb.bean.QueryRequest;
import com.shengpay.hpsc.core.domainservice.ccb.bean.QueryResponse;

/**
 * 功能描述：查询服务实现
 * @author
 * time : 2012-8-8 上午10:50:50
 */
@Component("ccbQueryChannelService")
public class QueryChannelService extends
        AbstractChannelService<QueryRequest, QueryRequest, QueryRequest, QueryResponse, QueryResponse> {

    private static final Log logger = LogFactory.getLog(QueryChannelService.class);

    @Resource(name="ccbCheckInProcessor")
    private ChannelProcessor<QueryRequest, QueryResponse> channelProcessor;

   	@Override
	protected QueryRequest preProcess(QueryRequest request) throws Exception {
		return request;
	}

	@Override
	protected String getFundChannelCode(QueryRequest preProcessedRequest) {
		return preProcessedRequest.getFcCode();
	}

    @Override
	protected void validate(QueryRequest preProcessedRequest,FundChannel fundChannel) throws Exception {
        if (StringUtils.isEmpty(preProcessedRequest.getFcCode())) {
            throw new PreSendException(ErrorCode.REQUEST_VERIFY_FAILED, "资金渠道编码为空");
        }
        /////////////////////////////

        if (StringUtils.isBlank(preProcessedRequest.getInstOrderNo())) {
            throw new PreSendException(ErrorCode.REQUEST_VERIFY_FAILED, "机构订单号为空");
        }
        QueryType queryType = preProcessedRequest.getQueryType();
        if(queryType == null){
            throw new PreSendException(ErrorCode.REQUEST_VERIFY_FAILED, "查询类型为空");
        }
	}

    @Override
    protected QueryRequest convert(QueryRequest request, FundChannel fundChannel) throws Exception {
        return null;
    }

    @Override
    protected ChannelProcessor<QueryRequest, QueryResponse> getChannelProcessor(QueryRequest channelDomain,
            FundChannel fundChannel) {
        return channelProcessor;
    }

    @Override
    protected QueryResponse toResponse(QueryRequest channelDomain, QueryResponse channelResponse) throws Exception {
        return null;
    }

    @Override
    protected QueryResponse toResponse(Exception e) {
    	return ResultUtils.toQueryResponse(e);
    }

}
