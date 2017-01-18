package com.parsec.universal.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 * 
 * @author chenyong
 * 
 */
public class DateUtil
{
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 年，月， 日，小时，分，秒
	 */
	public static final String pattern_YMDHmS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 年，月，日
	 */
	public static final String pattern_YMD = "yyyy-MM-dd";
	
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 获取当前日期，默认是上一个工作日
	 * 
	 * @return
	 */
	public static String getNowDay()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0);    //得到当天
		Date date = calendar.getTime();
		String dateBefore = df.format(date);
		return dateBefore;
	}

	/**
	 * 获取后一天的
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getAfterDay(Date date) throws Exception
	{
		String afterDate = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(date);
		cal.setTime(sdf.parse(now));
		cal.add(Calendar.DAY_OF_MONTH, 1);
		afterDate = sdf.format(cal.getTime());
		return afterDate;
	}
	/**
	 * 获取前一天的
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getBeforceDay(String date) throws Exception
	{
		String beforceDate = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(sdf.parse(date));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		beforceDate = sdf.format(cal.getTime()); 
		return beforceDate;
	}
	/**
	 * 获取上一年的
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String getBeforceYear(String date) throws Exception
	{
		String beforceYear = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(sdf.parse(date));
		cal.add(Calendar.YEAR,-1);
		beforceYear = sdf.format(cal.getTime()); 
		return beforceYear;
	}
	
	/**
	 * 获取当前月，默认上一月份
	 * @return
	 */
	public static String getMonth()
	{
		String monthDate = "";
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		if(month <10)
		{
			monthDate = year+"-0"+month;
		}else
		{
			monthDate = year+"-"+month;
		}
		return monthDate;
	}
	public static String getBeforeMonth(String date) throws Exception
	{
		String monthDate = "";
		if("".equalsIgnoreCase(date) ||date == null )
		{
			date = getMonth();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date));
		calendar.add(Calendar.MONTH, -1);    
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		if(month <10)
		{
			monthDate = year+"-0"+month;
		}else
		{
			monthDate = year+"-"+month;
		}
		return monthDate;
	}
	/**
	 * 获得某月的第一天的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthStartTime(String date)
	{
		String date1 = "";
		if(date == null || "".equalsIgnoreCase(date))
		{
			date1 = getMonth();
		}else
		{
			date1 = date;
		}
		String month = date1.split("-")[1];
		String startTime = date1.split("-")[0] + "-" + month + "-01";
		return startTime;
	}
	/**
	 * 获得某月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthEndTime(String date)
	{
		String date1 = "";
		if(date == null || "".equalsIgnoreCase(date))
		{
			date1 = getMonth();
		}else
		{
			date1 = date;
		}
		int month = Integer.parseInt(date1.split("-")[1].trim());
		String endTime = "";
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			endTime = date1.split("-")[0] + "-"
					+ date1.split("-")[1] + "-" + "31";
		} else if (month == 2) {
			int year = Integer.parseInt(date1.split("-")[0]);
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				endTime = date1.split("-")[0] + "-"
						+ date1.split("-")[1] + "-" + "29";
			} else {
				endTime = date1.split("-")[0] + "-"
						+ date1.split("-")[1] + "-" + "28";
			}

		} else {
			endTime = date1.split("-")[0] + "-"
					+ date1.split("-")[1] + "-" + "30";
		}
		return endTime;
	}
	/**
	 * 根据某年获取某月的最后一天
	 * @param yearStr
	 * @param month
	 * @param Format 时间格式
	 * @return
	 */
	public static String lastDate(String yearStr,String month,String Format)
	{
		Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, Integer.parseInt(yearStr));     
        cal.set(Calendar.MONTH, Integer.parseInt(month)-1);     
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
        return  new   SimpleDateFormat(Format).format(cal.getTime());
	}
	/**
	 * 获取季度时间
	 * @param quarter
	 */
	public static String quarterStr(String year,String quarter)
	{
		String timeQuarter = "";
		if(year == null||"".equalsIgnoreCase(year) || quarter.equalsIgnoreCase("请选择..."))
		{
			Calendar calendar = Calendar.getInstance();
			int yearStr = calendar.get(Calendar.YEAR);
			timeQuarter = yearStr+"-01-01 00:00:00"+","+yearStr+"-03-31 23:59:59";
		}
		if("第一季度".equalsIgnoreCase(quarter)||"1".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-01-01 00:00:00"+","+year+"-03-31 23:59:59";
		}
		if("第二季度".equalsIgnoreCase(quarter)||"2".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-04-01 00:00:00"+","+year+"-06-30 23:59:59";
		}
		if("第三季度".equalsIgnoreCase(quarter)||"3".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-07-01 00:00:00"+","+year+"-09-30 23:59:59";
		}
		if("第四季度".equalsIgnoreCase(quarter)||"4".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-10-01 00:00:00"+","+year+"-12-31 23:59:59";
		}
		return timeQuarter;
	}
	/**
	 * 获取上一个季度
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static String beforceQuarter(String year,String quarter)
	{
		String timeQuarter = "";
		if("".equalsIgnoreCase(year) || year == null)
		{
			Calendar calendar = Calendar.getInstance();
			int yearStr = calendar.get(Calendar.YEAR);
			timeQuarter = (yearStr-1)+"-10-01 00:00:00"+","+(yearStr-1)+"-12-31 23:59:59";
		}
		if("第一季度".equalsIgnoreCase(quarter))
		{
			timeQuarter = (Integer.parseInt(year)-1)+"-10-01 00:00:00"+","+(Integer.parseInt(year)-1)+"-12-31 23:59:59";
		}
		if("第二季度".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-01-01 00:00:00"+","+year+"-03-31 23:59:59";
		}
		if("第三季度".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-04-01 00:00:00"+","+year+"-06-30 23:59:59";
		}
		if("第四季度".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-07-01 00:00:00"+","+year+"-09-30 23:59:59";
		}
		return timeQuarter;
	}
	
	public static String beforceQuarter2(String year,String quarter)
	{
		String timeQuarter = "";
		if("".equalsIgnoreCase(year) || year == null)
		{
			Calendar calendar = Calendar.getInstance();
			int yearStr = calendar.get(Calendar.YEAR);
			timeQuarter = (yearStr-1)+"-10-01 00:00:00"+","+(yearStr-1)+"-12-31 23:59:59";
		}
		if("1".equalsIgnoreCase(quarter))
		{
			timeQuarter = (Integer.parseInt(year)-1)+"-10-01 00:00:00"+","+(Integer.parseInt(year)-1)+"-12-31 23:59:59";
		}
		if("2".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-01-01 00:00:00"+","+year+"-03-31 23:59:59";
		}
		if("3".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-04-01 00:00:00"+","+year+"-06-30 23:59:59";
		}
		if("4".equalsIgnoreCase(quarter))
		{
			timeQuarter = year+"-07-01 00:00:00"+","+year+"-09-30 23:59:59";
		}
		return timeQuarter;
	}
	
	public static String getBeforeMonthDay(String date) throws Exception
	{
		String beforceYear = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(sdf.parse(date));
		cal.add(Calendar.MONTH,-1);
		beforceYear = sdf.format(cal.getTime()); 
		return beforceYear;
	}
	
	/**
	 * 获取每个月 第一周开始、结束；第二周开始、结束；第三周开始、结束；第四周开始、结束时间
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String[] getMonthWeek(String date)throws Exception
	{
		String[] times = new String[8];
		times[0] = getMonthStartTime(date);
		Calendar c = Calendar.getInstance();
		Date data = null;
		int num = 0;
		for (int i = 0; i < times.length-1; i++)
		{
			if(i%2==0)
				num = 6;
			else
				num = 1;
			data = new SimpleDateFormat("yyyy-MM-dd").parse(times[i]);
	    	c.setTime(data);
		    int day = c.get(Calendar.DATE);
		    c.set(Calendar.DATE,day+num);
		    data = c.getTime();
		    times[i+1] = new SimpleDateFormat("yyyy-MM-dd").format(data);
		} 
		times[7] = getMonthEndTime(date);
		return times;
	}
	/**
	 * 获取每个季度的第一个月开始、结束；第二个月开始、结束；第三个月开始、结束时间
	 * @param timeDay
	 * @param quarterType
	 * @return
	 * @throws Exception
	 */
	public static String[] getQuarterMonth(String timeDay,String quarterType)throws Exception
	{
		String[] times = new String[6];
		String startTime = DateUtil.quarterStr(timeDay, quarterType).split(",")[0];
		times[0] = getMonthStartTime(startTime);
		Calendar c = Calendar.getInstance();
		Date data = null;
		for (int i = 0; i < times.length-1; i++)
		{
			if(i%2==0)
			{
				times[i+1] = getMonthEndTime(times[i]);
			}else
			{
				data = new SimpleDateFormat("yyyy-MM-dd").parse(times[i]);
		    	c.setTime(data);
			    int day = c.get(Calendar.DATE);
			    c.set(Calendar.DATE,day+1);
			    data = c.getTime();
			    times[i+1] = new SimpleDateFormat("yyyy-MM-dd").format(data);
			}
		} 
		return times;
	}
	/**
	 * 获取每年的 1月开始、结束… 12开始、结束时间
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String[] getYearMonth(String date) throws Exception
	{
		date = date.split("-")[0];
		String[] times = new String[24];
		String startTime = getMonthStartTime(date+"-01-01");
		times[0] = getMonthStartTime(startTime);
		Calendar c = Calendar.getInstance();
		Date data = null;
		for (int i = 0; i < times.length-1; i++)
		{
			if(i%2==0)
			{
				times[i+1] = getMonthEndTime(times[i]);
			}else
			{
				data = new SimpleDateFormat("yyyy-MM-dd").parse(times[i]);
		    	c.setTime(data);
			    int day = c.get(Calendar.DATE);
			    c.set(Calendar.DATE,day+1);
			    data = c.getTime();
			    times[i+1] = new SimpleDateFormat("yyyy-MM-dd").format(data);
			}
		} 
		return times;
	}
	
	/**
	 * 根据传入字符串时间和格式返回对应时间
	 * @param date
	 * @param Format
	 * @return
	 * @throws Exception
	 */
	public static Date getDate(String date,String Format){
		try {
			return new SimpleDateFormat(Format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据传入时间和格式返回字时间符串
	 * @param date
	 * @param Format
	 * @return
	 * @throws Exception
	 */
	public static String getDate(Date date,String Format){
		return (new SimpleDateFormat(Format)).format(date);
	}
	
	/**
     * 获取当前日期，0小时，0分，0秒
     * @return
     */
    public static Date getCurrentDateOnlyYMD(){
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
    }
	
    public static void main(String [] args) throws Exception {
//		 Date date = null;
//		 String specifiedDay = "2013-03-01";
//		 date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
//		 Calendar c = Calendar.getInstance();
//		 c.setTime(date);    // 得到前一天
//		 int day = c.get(Calendar.DATE);
//		 c.set(Calendar.DATE, day - 1);
//	     System.out.println(getMonthStartTime("2013-02-21 23:59:59")+"///////////////////");
//	     System.out.println(DateUtil.getBeforceYear("2013-02-21 23:59:59"));
//	     System.out.println(getDate("1989-12-12", "yyyy-MM-dd"));
		String m = getMonth();
		System.out.println(m);
	}
}
