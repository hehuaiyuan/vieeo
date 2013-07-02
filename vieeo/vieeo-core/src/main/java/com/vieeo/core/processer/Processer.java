package com.vieeo.core.processer;

import com.vieeo.core.packet.Packet;

/**
 * 处理器接口
 * @author roy.he
 *
 */
public interface Processer {
	
	public Packet process(Packet packet);

}
