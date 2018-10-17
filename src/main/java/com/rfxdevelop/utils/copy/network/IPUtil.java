package com.rfxdevelop.utils.copy.network;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *  copy from com.se.common.util;
 */
public class IPUtil {

	public static String localhost = "127.0.0.1";
	
	/**
	 * 获取本机IP(非127.0.0.1)
	 * @return
	 */
	public static String getLocalIp() {
		String localIp = null;
		InetAddress inetAddress = null;
		Enumeration<NetworkInterface> allNetInterfaces = null;
		
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
			return "GetIpError";
		}

		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			
			while (addresses.hasMoreElements()) {
				inetAddress = (InetAddress) addresses.nextElement();
				
				if (inetAddress != null && inetAddress instanceof Inet4Address) {
					String host = inetAddress.getHostAddress();
					
					if (localhost.equals(host)) {
						continue;
					}
					
					localIp = host;
				}
			}
		}

		return localIp;
	}
	
	/**
	 * 获取本机Host(非127.0.0.1)
	 * @return
	 */
	public static String getLocalHost() {
		try {  
			String host = InetAddress.getLocalHost().getHostName();
            return host;  
        } catch (UnknownHostException uhe) {  
            return "UnknownHost";  
        }  
	}
	
	public static void main(String[] args) {
		String localIp= IPUtil.getLocalIp();
		String localHost= IPUtil.getLocalHost();
		System.out.println("本机的IP = " + localIp);
		System.out.println("本机的Host = " + localHost);
	}
}

