package ${(config.rootPackage)!}.${(config.rootDomain)!}.service;

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

import ${(config.rootPackage)!}.${(config.rootDomain)!}.bean.${(domain.requestClassName)!};
import ${(config.rootPackage)!}.${(config.rootDomain)!}.bean.${(domain.responseClassName)!};

/**
 * 功能描述：鉴权服务实现
 * @author hehuaiyuan.roy
 * time : 2012-8-8 上午10:50:50
 */
@Component("${(config.rootDomain)!}AuthChannelService")
public class AuthChannelService extends
        AbstractChannelService<AuthenticateRequest, AuthenticateRequest, ${(domain.requestClassName)!}, ${(domain.responseClassName)!}, AuthenticateResponse>{

    private static final Log logger = LogFactory.getLog(AuthChannelService.class);

    @Resource(name="${(config.rootDomain)!}AuthProcessor")
    private ChannelProcessor<${(domain.requestClassName)!}, ${(domain.responseClassName)!}> channelProcessor;

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
    protected ${(domain.requestClassName)!} convert(AuthenticateRequest request, FundChannel fundChannel) throws Exception {
        return null;
    }

    @Override
    protected ChannelProcessor<${(domain.requestClassName)!}, ${(domain.responseClassName)!}> getChannelProcessor(${(domain.requestClassName)!} channelDomain,
            FundChannel fundChannel) {
        return channelProcessor;
    }

    @Override
    protected AuthenticateResponse toResponse(${(domain.requestClassName)!} channelDomain, ${(domain.responseClassName)!} channelResponse) throws Exception {
        return null;
    }

    @Override
    protected AuthenticateResponse toResponse(Exception e) {
    	return ResultUtils.toAuthenticateResponse(e);
    }

}
