package com.vieeo.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *
 */
public class IpUtil {

	//回环地址
	private static String LOOP_BACK_ADDRESS = "127.0.0.1";

	/**
	 * 取本地IP地址(Linux环境下只能取到回环地址)
	 * @return
	 */
	public static String getLocalIp() {
        String localIp = LOOP_BACK_ADDRESS;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        	e.printStackTrace();
        }
        return localIp;
    }

	/**
	 * 取本地IP地址(通用)
	 * @return
	 */
	public static String getLocalTrueIp() {
        String localIp = LOOP_BACK_ADDRESS;
		try {
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
			outer:
			while (enumeration.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) enumeration.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (!ip.isLoopbackAddress() && !isMac(ip)) {
						localIp = ip.getHostAddress();
						break outer;
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
        return localIp;
    }

	private static boolean isMac(InetAddress ip) {
		return ip.getHostAddress().indexOf(":") >= 0;
	}

}
