package com.shengpay.hpsc.core.domainservice.ccb.bean.header;

import com.sdo.cmf.common.enums.FundChannelApiType;
import com.shengpay.fcf.core.domain.InnerRequest;
import com.shengpay.fcf.core.domain.InnerResponse;

/**
 * 功能描述：
 * @author
 * time : 2012-8-3 下午1:49:09
 */
public class TransactionHeader implements InnerRequest,InnerResponse{

	private FundChannelApiType apiType;

	private String fundChannelCode;

	@Override
    public FundChannelApiType getApiType() {
        return apiType;
    }

    @Override
    public String getFundChannelCode() {
        return fundChannelCode;
    }

}