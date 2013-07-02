package com.vieeo.core.processer.support;

import com.vieeo.core.packet.BizPacket;
import com.vieeo.core.processer.BizProcesser;

/**
 * 抽象业务处理器,此类吧处理方法拆分成3个方法,分别是准备,处理,结束方法
 * 方便代码阅读和处理
 * @author roy.he
 *
 * @param <R>
 * @param <S>
 */
public abstract class DefaultAbstractBizProcesser<R,S> implements BizProcesser<R,S> {

	@Override
	public BizPacket<R, S> process(BizPacket<R, S> packet) {
		packet = before(packet);
		packet = processBiz(packet);
		packet = after(packet);
		return packet;
	}
	
	/**
	 * 准备方法
	 * @param packet
	 * @return
	 */
	public abstract BizPacket<R, S> before(BizPacket<R, S> packet);
	
	/**
	 * 实际业务处理方法
	 * @param packet
	 * @return
	 */
	public abstract BizPacket<R, S> processBiz(BizPacket<R, S> packet);
	
	/**
	 * 结束方法
	 * @param packet
	 * @return
	 */
	public abstract BizPacket<R, S> after(BizPacket<R, S> packet);

}
