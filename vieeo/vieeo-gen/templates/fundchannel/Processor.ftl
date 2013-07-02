package ${(config.rootPackage)!}.${(config.rootDomain)!}.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.shengpay.fcf.core.domain.channelconfig.FundChannel;
import com.shengpay.fcf.core.domain.exceptions.ChannelException;
import com.shengpay.fcf.core.domainservice.processor.ChannelProcessor;

import ${(config.rootPackage)!}.${(config.rootDomain)!}.bean.${(domain.requestClassName)!};
import ${(config.rootPackage)!}.${(config.rootDomain)!}.bean.${(domain.responseClassName)!};

/**
 * description:
 * @author
 * time : 2012-5-17 下午2:55:43
 */
@Component("${(config.rootDomain)!}${(opType)!}Processor")
public class ${(opType)!}Processor implements ChannelProcessor<${(domain.requestClassName)!}, ${(domain.responseClassName)!}>{
    private static final Log logger = LogFactory.getLog(${(opType)!}Processor.class);

    @Override
    public ${(domain.responseClassName)!} process(${(domain.requestClassName)!} request, FundChannel fundChannel) throws ChannelException {
        return null;
    }
}
