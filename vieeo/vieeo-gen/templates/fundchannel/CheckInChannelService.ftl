package ${(config.rootPackage)!}.${(config.rootDomain)!}.service;

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

import ${(config.rootPackage)!}.${(config.rootDomain)!}.bean.${(domain.requestClassName)!};
import ${(config.rootPackage)!}.${(config.rootDomain)!}.bean.${(domain.responseClassName)!};

/**
 * 功能描述：登入服务实现
 * @author
 * time : 2012-8-8 上午10:50:50
 */
@Component("${(config.rootDomain)!}CheckInChannelService")
public class CheckInChannelService extends
        AbstractChannelService<String, ChannelFundRequest, ${(domain.requestClassName)!}, ${(domain.responseClassName)!}, ChannelFundResult> {

    private static final Log logger = LogFactory.getLog(CheckInChannelService.class);

    @Resource(name="${(config.rootDomain)!}CheckInProcessor")
    private ChannelProcessor<${(domain.requestClassName)!}, ${(domain.responseClassName)!}> channelProcessor;

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
    protected ${(domain.requestClassName)!} convert(ChannelFundRequest request, FundChannel fundChannel) throws Exception {
        return null;
    }

    @Override
    protected ChannelProcessor<${(domain.requestClassName)!}, ${(domain.responseClassName)!}> getChannelProcessor(${(domain.requestClassName)!} channelDomain,
            FundChannel fundChannel) {
        return channelProcessor;
    }

    @Override
    protected ChannelFundResult toResponse(${(domain.requestClassName)!} channelDomain, ${(domain.responseClassName)!} channelResponse) throws Exception {
        return null;
    }

    @Override
    protected ChannelFundResult toResponse(Exception e) {
    	return ResultUtils.toChannelFundResult(e);
    }

}
