package com.vieeo.core.processer;

import com.vieeo.core.exception.BizProcesserException;
import com.vieeo.core.packet.BizPacket;

/**
 * 业务处理器接口
 * @author roy.he
 *
 */
public interface BizProcesser<R,S> {
	
	/**
	 * 根据业务参数标识处理业务
	 * @param <R>
	 * @param <S>
	 * @param packet
	 * @return
	 */
	public BizPacket<R,S> process(BizPacket<R,S> packet)throws BizProcesserException;

}
