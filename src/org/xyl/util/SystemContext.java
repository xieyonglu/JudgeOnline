package org.xyl.util;

public class SystemContext {

	private static ThreadLocal<Integer> pageSize=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageOffset=new ThreadLocal<Integer>();
	private static ThreadLocal<String> sort=new ThreadLocal<String>();
	private static ThreadLocal<String> order=new ThreadLocal<String>();
	private static ThreadLocal<String> realPath=new ThreadLocal<String>();
	
	public static String getRealPath(){
		return realPath.get();
	}
	
	public static void setRealPath(String _realPath){
		realPath.set(_realPath);
	}
	
	public static void removeRealPath(){
		realPath.remove();
	}
	
	/**
	 * ÿҳ��ʾ������
	 */
	public static int getPageSize(){
		return pageSize.get();
	}
	
	/**
	 * ����ÿҳ��ʾ��������Ϣ
	 * @param _pageSize
	 */
	public static void setPageSize(int _pageSize){
		pageSize.set(_pageSize);
	}
	
	/**
	 * �Ƴ�����
	 */
	public static void removePathSize(){
		pageSize.remove();
	}
	
	/**
	 * ��ȡlimit��start��ֵ
	 * @return
	 */
	public static int getPageOffset(){
		return pageOffset.get();
	}
	
	/**
	 * ����limit��start��ֵ
	 * @param _pageOffset
	 */
	public static void setPageOffset(int _pageOffset){
		pageOffset.set(_pageOffset);
	}
	public static void removePageOffset(){
		pageOffset.remove();
	}
	
	/**
	 * ��ȡ������ֶ�
	 * @return
	 */
	public static String getSort(){
		return sort.get();
	}
	
	/**
	 * ����������ֶ�
	 * @param _sort
	 */
	public static void setSort(String _sort){
		sort.set(_sort);
	}
	
	public static void removeSort(){
		sort.remove();
	}
	
	/**
	 * ��ȡ�����˳��asc����desc
	 * @return
	 */
	public static String getOrder(){
		return order.get();
	}
	
	/**
	 * ���������˳��
	 * @param _order
	 */
	public static void setOrder(String _order){
		order.set(_order);
	}
	
	public static void removeOrder(){
		order.remove();
	}
	
	

}



