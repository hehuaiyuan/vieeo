<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context-2.5.xsd"
    default-autowire="no">

    <context:annotation-config />
    <context:component-scan base-package="${(config.rootPackage)!}.${(config.rootDomain)!}.processor" />
    <context:component-scan base-package="${(config.rootPackage)!}.${(config.rootDomain)!}.service" />

    <!-- ${(config.rootDomain)!}银行配置 -->
    <!-- channelServiceFactory -->
    <bean id="${(config.rootDomain)!}ChannelServiceFactory" class="com.shengpay.fcf.core.domainservice.factory.impl.CodeMappingChannelServiceFactory" >
        <property name="channelServices">
            <map>
            	<!-- 鉴权 -->
                <entry key="${(channelCode)!}-AUTH" value-ref="${(config.rootDomain)!}AuthChannelService" />
                <!-- 签到 -->
                <entry key="${(channelCode)!}-CI" value-ref="${(config.rootDomain)!}CheckInChannelService" />
                <!-- 签退 -->
                <entry key="${(channelCode)!}-CO" value-ref="${(config.rootDomain)!}CheckOutChannelService" />
                <!-- 扣款 -->
                <entry key="${(channelCode)!}-DB" value-ref="${(config.rootDomain)!}DebitChannelService" />
                <!-- 扣款冲正 -->
                <entry key="${(channelCode)!}-DBR" value-ref="${(config.rootDomain)!}ReversalChannelService" />
                <!-- 退款 -->
                <entry key="${(channelCode)!}-SR" value-ref="${(config.rootDomain)!}RefundChannelService" />
                <!-- 查询 -->
                <entry key="${(channelCode)!}-SQ" value-ref="${(config.rootDomain)!}QueryChannelService" />
            </map>
        </property>
    </bean>

</beans>

