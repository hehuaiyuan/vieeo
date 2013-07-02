package com.vieeo.core.packet;


/**
 * 业务参数标示接口,定义泛型标示请求参数和响应参数
 * @author roy.he
 *
 * @param <R> 请求参数
 * @param <S> 响应参数
 */
public interface BizPacket<R,S> extends Packet{
	
	/**
	 * 得到请求参数
	 * @return
	 */
	public R getRequest();
	
	/**
	 * 得到响应参数
	 * @return
	 */
	public S getResponse();

}
