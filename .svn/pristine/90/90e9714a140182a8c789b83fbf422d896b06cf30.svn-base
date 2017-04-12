/**
 * 
 */
package com.fykj.platform.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author zhengzw
 *
 */
public class JDateUtils extends DateUtils {
	
	public static Date parseDate(final String str, final String... parsePatterns) {
		try{
			return parseDate(str, null, parsePatterns);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
    }
	
	public static Date parseDate(final String str) {
		final String parsePatterns = "yyyy-MM-dd";
		
		try{
			return parseDate(str, parsePatterns);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * calculate working days
	 * 
	 * @param begDate
	 * @param endDate
	 * @return
	 */
	public static int getWorkingDays(Date begDate, Date endDate) {
		if (begDate.after(endDate)) {
			throw new RuntimeException("日期范围非法");
		}
		// 总天数
		int days = (int) ((endDate.getTime() - begDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
		// 总周数，
		int weeks = days / 7;
		int rs = 0;
		// 整数周
		if (days % 7 == 0) {
			rs = days - 2 * weeks;
		} else {
			Calendar begCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			begCalendar.setTime(begDate);
			endCalendar.setTime(endDate);
			// 周日为1，周六为7
			int beg = begCalendar.get(Calendar.DAY_OF_WEEK);
			int end = endCalendar.get(Calendar.DAY_OF_WEEK);
			if (beg > end) {
				rs = days - 2 * (weeks + 1);
			} else if (beg < end) {
				if (end == 7) {
					rs = days - 2 * weeks - 1;
				} else {
					rs = days - 2 * weeks;
				}
			} else {
				if (beg == 1 || beg == 7) {
					rs = days - 2 * weeks - 1;
				} else {
					rs = days - 2 * weeks;
				}
			}
		}

		return rs;
	}
	
	/**
	 * 判断是否是工作日
	 * @param date
	 * @return
	 */
	public static boolean isWorkingDay(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY  
                && calendar.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY) {
			return true;
		}
		
		return false;
	}
	
	public static Map<String, BigDecimal> calcMonthlyRate(Date begDate, Date endDate) {
		
		Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
		
		if (begDate.after(endDate)) {
			throw new RuntimeException("日期范围非法, 开始时间不能大于结束时间!");
		}
		
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(begDate);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		
		if(startCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR) 
			&& startCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)) {
			
			int workdays = endCalendar.get(Calendar.DATE) - startCalendar.get(Calendar.DATE) + 1;
			int maxdays = startCalendar.getActualMaximum(Calendar.DATE);
			
			BigDecimal rate = new BigDecimal(workdays).divide(new BigDecimal(maxdays), 2, BigDecimal.ROUND_HALF_UP);
			rateMap.put(formatMonth(begDate), rate);
		} else {
			int startMax = startCalendar.getActualMaximum(Calendar.DATE);
			int startDays = startMax - startCalendar.get(Calendar.DATE) + 1;
			
			BigDecimal start_rate = new BigDecimal(startDays).divide(new BigDecimal(startMax), 2, BigDecimal.ROUND_HALF_UP);
			rateMap.put(formatMonth(begDate), start_rate);
			
			int endMax = endCalendar.getActualMaximum(Calendar.DATE);
			int endDay = endCalendar.get(Calendar.DATE);
			
			BigDecimal end_rate = new BigDecimal(endDay).divide(new BigDecimal(endMax), 2, BigDecimal.ROUND_HALF_UP);
			rateMap.put(formatMonth(endDate), end_rate);
			
			int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH) - 1;
			
			int length = 12 * year + month;
			
			for(int i = 0; i < length; i++) {
				Date date = DateUtils.addMonths(begDate, i + 1);
				
				rateMap.put(formatMonth(date), BigDecimal.ONE);
			}
		}
		
		
		
		
		
		return rateMap;
	}
	
	private static String formatMonth(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		
		int year = cl.get(Calendar.YEAR);
		
		int month = cl.get(Calendar.MONTH) + 1;
		
		if(month < 10) {
			return year + "-0" + month;
		}
		
		return year + "-" + month;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static Map<String, BigDecimal> calMonthlyRate(Date beginDate, Date endDate){
		Calendar calendar = Calendar.getInstance(); 
		Map<String, BigDecimal> returnMap = new HashMap<String, BigDecimal>();
		
		//1. calculate total months
		int montthDiff = endDate.getYear() - beginDate.getYear();
		montthDiff *= 12;
		montthDiff += endDate.getMonth() -beginDate.getMonth() + 1;

		// loop months
		Date tempDate;
		for(int i = 0 ; i < montthDiff; i++ ){
			tempDate = DateUtils.addMonths(beginDate, i);
			calendar.setTime(tempDate);
			if(tempDate.getYear() == endDate.getYear() && tempDate.getMonth() == beginDate.getMonth()){
				int maxDays = calendar.getActualMaximum(Calendar.DATE);
				int workDate = maxDays - calendar.get(Calendar.DATE) +1;
				BigDecimal rate = new BigDecimal(workDate).divide(new BigDecimal(maxDays), 2, RoundingMode.HALF_UP);
						
				returnMap.put(tempDate.getYear() +1900 +"-"+ (tempDate.getMonth()+1), rate);
			}
			else if(tempDate.getYear() == endDate.getYear() && tempDate.getMonth() == endDate.getMonth()){
				
				Calendar c = Calendar.getInstance();
				c.setTime(endDate);
				
				int maxDays = c.getActualMaximum(Calendar.DATE);
				int workDate = c.get(Calendar.DATE);
				
				BigDecimal rate = new BigDecimal(workDate).divide(new BigDecimal(maxDays), 2, RoundingMode.HALF_UP);
						
				returnMap.put(tempDate.getYear() +1900 +"-"+(tempDate.getMonth()+1), rate);
			}
			else if((tempDate.getYear() == endDate.getYear() && tempDate.getMonth() < endDate.getMonth())
					|| (tempDate.getYear() < endDate.getYear())){
				returnMap.put(tempDate.getYear() +1900 +"-"+(tempDate.getMonth()+1), BigDecimal.ONE);
			}
		}
		
		return returnMap; 
	}
	
	/**
	 * yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		if(date==null) return null;
		return DateFormatUtils.ISO_DATE_FORMAT.format(date);
	}
	
	
	
	
}
