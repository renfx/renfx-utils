package com.rfxdevelop.utils.copy.common;


import com.rfxdevelop.utils.copy.object.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * copy from com.wealthlake.common.util.date
 * @author badqiu
 */
public class DateConvertUtils {

    public static final String FORMAT_SECOND = "yyyy-MM-dd hh:mm:ss";
    public static final String FORMAT_NOT_BR = "yyyy-MM-dd_hh:mm:ss";
	public static Date parse(String dateString,String dateFormat) {
		return parse(dateString, dateFormat,Date.class);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Date> T parse(String dateString,String dateFormat,Class<T> targetResultType) {
		if(ObjectUtils.isNullOrEmptyString(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			long time = df.parse(dateString).getTime();
			Date t = targetResultType.getConstructor(long.class).newInstance(time);
			return (T)t;
		} catch (ParseException e) {
			String errorInfo = "cannot use dateformat:"+dateFormat+" parse datestring:"+dateString;
			throw new IllegalArgumentException(errorInfo,e);
		} catch (Exception e) {
			throw new IllegalArgumentException("error targetResultType:"+targetResultType.getName(),e);
		}
	}

	public static String format(Date date,String dateFormat) {
		 if(date == null)
			 return null;
		 return new SimpleDateFormat(dateFormat).format(date);
	}
	
	/**
	 * 获取当前时间的秒数
	 * @return 秒数 int
	 */
	public static int nowDateTime(){
		return (int)(System.currentTimeMillis()/1000);
	}
	
	/**
	 * 根据时间秒数得到date
	 * @param dateTime
	 * @return
	 */
	public static Date getDateTimeByDate(Integer dateTime){
		if(dateTime==null)
			return null;
		Date date = new Date(dateTime * 1000L);
		return date;
	}
	
	/**
	 * 根据date得到时间秒数
	 * @param dateStr  date字符串
	 * @param dateFormat  时间格式
	 * @return
	 */
	public static Date getDateByDateStr(String dateStr, String dateFormat){
		if(ObjectUtils.isNullOrEmptyString(dateStr))
			return null;
		if(ObjectUtils.isNullOrEmptyString(dateFormat))
			dateFormat = "yyyy-MM-dd";
		return parse(dateStr, dateFormat);
	}
	
	/**
	 * 根据date得到秒数
	 * @param date
	 * @return
	 */
	public static int getDateByDateTime(Date date){
		if(date==null)
			return 0;
		return (int) (date.getTime()/1000);
	}
	
	
	/**
	 * 根据date得到时间秒数
	 * @param dateStr  date字符串
	 * @param dateFormat  时间格式
	 * @return
	 */
	public static Integer getDateByDateTime(String dateStr, String dateFormat){
		if(ObjectUtils.isNullOrEmptyString(dateStr))
			return null;
		if(ObjectUtils.isNullOrEmptyString(dateFormat))
			dateFormat = "yyyy-MM-dd";
		Date date = parse(dateStr, dateFormat);
		return getDateByDateTime(date);
	}
	
	/**
	 * 获取指定时间向后退多少秒后的时间
	 * @param time 秒数
	 * @return
	 */
	public static Integer getDateByFirstTime(Integer time){
		if(time==null) return null;
		return getDateByFirstTime(nowDateTime(), time);
	}
	
	/**
	 * 获取指定时间向后退多少秒后的时间
	 * @param date 时间 秒数
	 * @param time 秒数
	 * @return
	 */
	public static Integer getDateByFirstTime(Integer date, Integer time){
		if(date==null) return null;
		if(time==null) return null;
		
		return date - time;
	}
	
	
	/**
	 * 判断当前时间秒数是否已过指定的时间秒数
	 * @param dateTime 时间  秒数
	 * @param delay 延时 秒数
	 * @return 是 true，否 false
	 */
	public static boolean checkTimeOut(int dateTime, int delay) {
		//发送的秒数+延时 秒数
		long sendTime = dateTime + delay;
		//当前时间的毫秒数
		int currentTime = nowDateTime();
		//当前时间大于sendTime则表示已过延时可以进行操作
		if (sendTime < currentTime) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查是否过期
	 * @param dateTime
	 * @param delay
	 * @return
	 */
	public static boolean checkTimeOut(long dateTime, int delay) {
		//发送的秒数+延时 秒数
		long sendTime = dateTime + delay;
		//当前时间的毫秒数
		int currentTime = nowDateTime();
		//当前时间大于sendTime则表示已过延时可以进行操作
		if (sendTime < currentTime) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断当前时间秒数是否已过指定的时间秒数
	 * @param date 时间
	 * @param delay 延时 秒数
	 * @return 是 true，否 false
	 */
	public static boolean checkTimeOut(Date date, int delay) {
		//发送的秒数+延时 秒数
		long sendTime = getDateByDateTime(date) + delay;
		//当前时间的毫秒数
		int currentTime = nowDateTime();
		//当前时间大于sendTime则表示已过延时可以进行操作
		if (sendTime < currentTime) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取当前时间的后几天时间，如：当前时间2015-10-10  后2天就为2015-10-12
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateAfterDay(int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的后几天时间，如：当前时间2015-10-10  后2天就为2015-10-12
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateAfterMin(int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的后几天时间，如：当前时间2015-10-10  后2天就为2015-10-12
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateAfterSecond(int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的后几小时时间，如：当前时间2015-10-10 12:00:00  后2小时天就为2015-10-10 14:00:00
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateAfterHour(int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间的后几天时间，如：当前时间2015-10-10  后2天就为2015-10-12
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateAfterDay(Date date, int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, delay);
		return calendar.getTime();
	}
	/**
	 * 获取当前时间的前几秒时间
	 * @param delay 秒数
	 * @return Date
	 */
	public static Date getByDateBeforeSecond(int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -delay);
		return calendar.getTime();
	}
	/**
	 * 获取当前时间的前几天时间，如：当前时间2015-10-10  前2天就为2015-10-08
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateBeforeDay(int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, - delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间的前几天时间，如：当前时间2015-10-10  前2天就为2015-10-08
	 * @param delay 天数
	 * @return Date
	 */
	public static Date getByDateBeforeDay(Date date, int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, - delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的后几月时间，如：当前时间2015-10-10  后2月就为2015-12-10
	 * @param delay 月数
	 * @return Date
	 */
	public static Date getByDateAfterMonth(Date date, int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间的前几月时间，如：当前时间2015-10-10  前2月就为2015-08-10
	 * @param delay 月数
	 * @return Date
	 */
	public static Date getByDateBeforeMonth(Date date, int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, - delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的后几年时间，如：当前时间2015-10-10  后2月就为2015-12-10
	 * @param delay 年数
	 * @return Date
	 */
	public static Date getByDateAfterYear(Date date, int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, delay);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间的前几年时间，如：当前时间2015-10-10  前1年就为2014-10-10
	 * @param delay 年数
	 * @return Date
	 */
	public static Date getByDateBeforeYear(Date date, int delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, - delay);
		return calendar.getTime();
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param dateBegin 较小的时间 
     * @param dateEnd  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date dateBegin, Date dateEnd) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateBegin = sdf.parse(sdf.format(dateBegin));
			dateEnd = sdf.parse(sdf.format(dateEnd));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}

        Calendar cal = Calendar.getInstance();    
        cal.setTime(dateBegin);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(dateEnd);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
        return Integer.parseInt(String.valueOf(between_days));           
    }    
     
    
	/** 
	* 计算两个字符串日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException
	*/  
    public static int daysBetween(String smdate, String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
        return Integer.parseInt(String.valueOf(between_days));     
    } 
    
    /**
     * 表时间戳执行
     * @return
     */
    public static String convertyyMM(){
    	return format(new Date(), "yyMM");
    }
    
    /**
     * 表时间戳执行
     * @return
     */
    public static String convertyyMMdd(){
    	return format(new Date(), "yyMMdd");
    }
    
    /**
     * 表时间戳执行
     * @return
     */
    public static String convertdd(){
    	return format(new Date(), "d");
    }
    
    /**
     * 表时间戳执行
     * @return
     */
    public static String convertyyyyMM(){
    	return format(new Date(), "yyyyMM");
    }
    
    /** 
     * 获取当天开始时间，如：2016-04-03 00:00:00
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getDayBeginTime(Date date){  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);//00:00:00
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /** 
     * 获取当天结束时间，如：2016-04-03 23:59:59
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getDayEndTime(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);//23:59:59
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
     
    /** 
     * 获取本周的第一天开始时间，如：2016-04-03 00:00:00
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getWeekFisrtDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);//00:00:00
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /** 
     * 获取本周的最后一天结束时间，如：2016-04-09 23:59:59
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getWeekLastDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);//23:59:59
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    
    /** 
	* 获取当前时间所属月份的第一天开始时间，如：2016-03-01 00:00:00
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getMonthFisrtDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
        cal.set(Calendar.HOUR_OF_DAY, 0);//00:00:00
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /** 
	* 获取当前时间所属月份的最后一天结算时间，如：2016-03-31 23:59:59
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getMonthLastDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);//23:59:59
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    } 
    
    /** 
     * 获取本季第一天开始时间，如：2016-01-01 00:00:00
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getQuarterFisrtDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3)
        	cal.set(Calendar.MONTH, 0);
        else if (currentMonth >= 4 && currentMonth <= 6)
        	cal.set(Calendar.MONTH, 3);
        else if (currentMonth >= 7 && currentMonth <= 9)
        	cal.set(Calendar.MONTH, 6);
        else if (currentMonth >= 10 && currentMonth <= 12)
        	cal.set(Calendar.MONTH, 9);
        
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);//00:00:00
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /** 
     * 获取本季最后一天结束时间，如：2016-03-31 23:59:59
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getQuarterLastDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3) {
        	cal.set(Calendar.MONTH, 2);
        	cal.set(Calendar.DATE, 31);
        } else if (currentMonth >= 4 && currentMonth <= 6) {
        	cal.set(Calendar.MONTH, 5);
        	cal.set(Calendar.DATE, 30);
        } else if (currentMonth >= 7 && currentMonth <= 9) {
        	cal.set(Calendar.MONTH, 8);
        	cal.set(Calendar.DATE, 30);
        } else if (currentMonth >= 10 && currentMonth <= 12) {
        	cal.set(Calendar.MONTH, 11);
        	cal.set(Calendar.DATE, 31);
        }
        
        cal.set(Calendar.HOUR_OF_DAY, 23);//23:59:59
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    /** 
     * 获取当前时间所属年份的第一天开始时间，如：2016-01-01 00:00:00
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getYearFisrtDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
        cal.set(Calendar.HOUR_OF_DAY, 0);//00:00:00
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /** 
	* 获取当前时间所属年份的最后一天结束时间，如：2016-12-31 23:59:59
     * @param date 当前时间
     * @throws ParseException
	*/  
    public static Date getYearLastDay(Date date){  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);//23:59:59
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
   /**
    * 判断时间是否是今天
    * @param date
    * @return true 是，false 否
    */
    public static boolean checkIsToday(Date date){
    	if(date==null) return false;
    	
		String dateStr = format(date, "yyyyMMdd");
		//今天
		String todayDateStr = format(new Date(), "yyyyMMdd");
		
		if(dateStr!=null && dateStr.equals(todayDateStr))
			return true;
		return false;
    }
    
	/**
	 * 判断时间是否是昨天
     * @param date
     * @return true 是，false 否
	*/
	public static boolean checkIsYesterday(Date date){
		if(date==null) return false;
     	
 		String dateStr = format(date, "yyyyMMdd");
 		//昨天
 		String todayDateStr = format(getByDateBeforeDay(1), "yyyyMMdd");
 		
 		if(dateStr!=null && dateStr.equals(todayDateStr))
 			return true;
 		return false;
	}
     
	/**
	 * 判断时间是否是前天
	 * @param date
	 * @return true 是，false 否
	 */
	public static boolean checkIsBeforeYesterday(Date date){
		if(date==null) return false;
      	
  		String dateStr = format(date, "yyyyMMdd");
  		//前天
  		String todayDateStr = format(getByDateBeforeDay(2), "yyyyMMdd");
  		
  		if(dateStr!=null && dateStr.equals(todayDateStr))
  			return true;
  		return false;
	}
     
    
    /** 
     * 时间转换  <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;时间是今天时返回时间的时分，如：12:00<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;反之返回时间的月日，如:6月16日
     * @param datetime 时间 yyyy-MM-dd HH:mm:ss
	*/  
	public static String dateConvertDateOrTime(String datetime){
		if(datetime==null || "".equals(datetime)) return null;
		Date date = parse(datetime, "yyyy-MM-dd HH:mm:ss");
		if(checkIsToday(date)){
			return format(date, "HH:mm");
		}else{
			return format(date, "MM月dd日");
		}
	}
	
	
	/** 
	 * 时间转换  <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;时间是今天时返回时间的时分，如：12:00<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;时间是昨天和前天时返回时间的时分，如：昨天12:00，前天12:00<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;反之返回年月日 时分，如：2016年6月17日 12:19<br>
	 * @param datetime 时间 yyyy-MM-dd HH:mm:ss
	*/  
	public static String dateConvert(String datetime){
		if(datetime==null || "".equals(datetime)) return null;
		Date date = parse(datetime, "yyyy-MM-dd HH:mm:ss");
		if(checkIsToday(date)){
			return format(date, "HH:mm");
		}else if(checkIsYesterday(date)){
			return "昨天 "+format(date, "HH:mm");
		}else if(checkIsBeforeYesterday(date)){
			return "前天 "+format(date, "HH:mm");
		}else{
			return format(date, "yyyy年MM月dd日 HH:mm");
		}
	}
	
    /**
     * 时分秒时间int类型转string,如：120000->120000， 70000->070000
     * @param time
     * @return
     */
	public static String formatTime2String(Long time) {
		if(time==null) return null;
		String bts = time.toString();
		for (int i = 0; i < 6 - bts.length();) {
			bts = "0" + bts;
		}
		return bts;
	}
	
	/**
     * 时间int类型转string,如：120000->00000000120000， 70000->00000000070000
     * @param time
     * @return
     */
	public static String formatDateTime2String(Long time) {
		if(time==null) return null;
		String bts = time.toString();
		for (int i = 0; i < 14 - bts.length();) {
			bts = "0" + bts;
		}
		return bts;
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式时间，统一使用如下格式『2015-11-11 23:23:23』，
	 * 如果部分数值取不到，显示00，例如：『2015-11-11 00:00:00』
	 * @param dateString 时间字符串
	 * @return Date 时间
	 */
	public static Date getDateTime(String dateString) {
		if(ObjectUtils.isNullOrEmptyString(dateString))
			return null;
		//替换多余的双引号
		dateString = dateString.replaceAll("\"", "");
		if(ObjectUtils.isNullOrEmptyString(dateString))
			return null;
		//替换标准时间分隔符“-”
		dateString = dateString.replaceAll("/", "-");
		
		String dateTimes[] = dateString.split(" ");
		if(dateTimes!=null){
			if(dateTimes.length==1){
				dateString = dateString+ " 00:00:00";
			}else if(dateTimes.length==2){
				String time = dateTimes[1];
				if(time!=null){
					String times[] = time.split(":");
					if(times!=null){
						if(times.length==1)
							dateString = dateString+ ":00:00";
						else if(times.length==2)
							dateString = dateString+ ":00";
					}
				}
			}
			return parse(dateString, "yyyy-MM-dd HH:mm:ss");
		}
		return null;
	}
	
	
	/**
	 * 获取当前小时的开始时间
	 *
	 */
	public static int getCurrentHoursBeginTime() {
		Calendar cal = Calendar.getInstance();    
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return getDateByDateTime(cal.getTime());
	}
	
	/**
	 * 获取当前小时的结束时间
	 * @return 是 true，否 false
	 */
	public static int getCurrentHoursEndTime() {
		Calendar cal = Calendar.getInstance();    
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return getDateByDateTime(cal.getTime());
	}
	
	public static void main(String[] args) {
//		System.out.println(new Date().getDate());
//		System.out.println(getDateTimeByDate(1441728000));
//		String date = format(getDateTimeByDate(1441728000), "yyyy-MM-dd");
//		System.out.println(date);
//		
//		System.out.println("=================");
//		System.out.println(getDateByFirstTime(10));
//		System.out.println(format(getDateTimeByDate(getDateByFirstTime(1 * 60)), "yyyy-MM-dd HH:mm:ss"));
//		
//		System.out.println("=================");
//		System.out.println(getDateByDateTime("2016-03-31", "yyyy-MM-dd"));
//		System.out.println("1111111111111=================3");
//		System.out.println(getDateByDateTime("2016-03-31 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(getDateByDateTime("2016-04-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//		System.out.println("=================");
//		Calendar c = Calendar.getInstance();
//		System.out.println(c.get(Calendar.MONTH));
//		c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
//		System.out.println(c.get(Calendar.MONTH));
//		System.out.println(format(c.getTime(),"yyyy-MM-dd"));
//		
//		System.out.println("=================");
//		System.out.println(format(getMonthFisrtDay(new Date()), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(format(getMonthLastDay(new Date()), "yyyy-MM-dd HH:mm:ss"));
//		
//		System.out.println("=================");
//		System.out.println(dateConvertDateOrTime("2016-06-15 12:12:12"));
//		
		Date date2 = getByDateAfterSecond(100);
		System.out.println(format(date2, "ss mm H dd M ? yyyy-yyyy"));
	}
	
	   /** 
	    * 判断当前日期是星期几 
	    *  
	    * @param pTime 修要判断的时间 
	    * @return dayForWeek 判断结果 
	    * @Exception 发生异常 
	    */  
	public static int dayForWeek(Date pTime) throws Exception {  
	 Calendar c = Calendar.getInstance();  
	 c.setTime(pTime);  
	 int dayForWeek = 0;  
	 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
	  dayForWeek = 7;  
	 }else{  
	  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
	 }  
	 return dayForWeek;  
	}
	
}
