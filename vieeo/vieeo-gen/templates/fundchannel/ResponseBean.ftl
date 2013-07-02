package ${(config.rootPackage)!}.${(config.rootDomain)!}.bean;

import com.shengpay.fcf.core.domain.InnerResponse;
import com.sdo.cmf.common.enums.FundChannelApiType;
import com.shengpay.fcf.core.domain.InnerRequest;

/**
 * 功能描述：
 * @author
 * time : 2012-8-3 下午1:49:09
 */
public class ${(domain.responseClassName)!} implements InnerRequest,InnerResponse{

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