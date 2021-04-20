package com.web.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";

    public static String getFormatedDT() {
        return getFormatedDT(DEFAULT_DATE_FORMAT);
    }

    public static String getFormatedDT(String sDateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getFormatedDTWithLocale(String sDateFormat, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat, locale);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getFormatedDT(Date date, String sDateFormat) {
    	if (date == null) {
    		return null;
    	}
        SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat);
        return sdf.format(date);
    }


    public static String getFormatedDTWithLocale(Date date, String sDateFormat, Locale locale) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat, locale);
        return sdf.format(date);
    }


    public static Date getDateFromStr( String sDate, String sDateFormat ) {
        if ( sDate == null || "".equals(sDate) ) {
            return null;
        }

        Date resDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat);
        try {
            resDate = sdf.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            resDate = Calendar.getInstance().getTime();
        }
        return resDate;
    }

    public static Calendar getCalendarFromStr( String sDate, String sDateFormat ) {
        Calendar calen = Calendar.getInstance();
        calen.setTime( getDateFromStr(sDate, sDateFormat) );
        return calen;
    }

    public static Calendar plusTime( Calendar calen, int calendarField, int plusMinusNum ) {
        if( calen == null ) {
            return null;
        }
        Calendar resCal = Calendar.getInstance();
        resCal.setTime(calen.getTime());
        resCal.add(calendarField, plusMinusNum);
        return resCal;
    }

    /**
     * 현재 날짜 기준으로 몇일 전인지 후인지를 long 형식으로 리턴한다.
     * 예를들어 오늘이 22일이면, targetDate가 23일 00시이면 1이 리턴되고,
     * 오늘이 22일이고, targetDate가 21일 23시59분59초이면 -1이 리턴된다.
     * 즉, 날짜차이가 발생하는 기준시간은 00시가 된다.
     * @param	targetDate 현재 날짜와 비교할 날짜 객체(Date객체)
     * @return	현재 날짜 기준으로 전날이면 날짜 차이만큼 음수가 리턴되고, 훗날이면 날짜 차이만큼 양수가 리턴된다.
     */
    public static long diffDayFromCurr( Date targetDate ) {
        Calendar curr = Calendar.getInstance();
        Calendar targ = Calendar.getInstance();
        targ.setTime(targetDate);
        long currTime = curr.getTimeInMillis();
        currTime = (currTime / 1000 / 60 / 60 + 9) / 24;	// 9를 더해주는 이유는 epoch가 1969-12-31일 15:00:00 이기 때문이다. 00시 기점으로 날짜가 변경되도록 하기 위해 9시간을 더해 줌.
        long targTime = targ.getTimeInMillis();
        targTime = (targTime / 1000 / 60 / 60 + 9) / 24;
        long diffDay = targTime - currTime;
        return diffDay;
    }

    /**
     * 날짜 차이를 한글로 표현하는 함수
     * @param	targetDate 현재 날짜와 비교할 날짜 객체(Date객체)
     * @return	"X일 후", "X일 전", "오늘"
     */
    public static String whatDaysAgoOrAfterToHangul( Date targetDate ) {
        String sRes = "";
        long diffDay = diffDayFromCurr(targetDate);
        if ( diffDay > 0  ) {
            sRes = diffDay + "일 후";
        } else if( diffDay < 0 ) {
            sRes = Math.abs(diffDay) + "일 전";
        } else {
            sRes = "오늘";
        }
        return sRes;
    }


    public static boolean validate(String szDate){
        return validate_yyyyMMdd(szDate);
    }

    public static boolean validate_yyyyMMdd(String szDate){
        String DATE_PATTERN = "([1-9]\\d\\d\\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";       // yyyyMMdd
        return validate(szDate, DATE_PATTERN);
    }

    public static boolean validate_yyyyMMddHHmmss(String szDate){
        String DATE_PATTERN = "([1-9]\\d\\d\\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])";       // yyyyMMddHHmmss
        return validate(szDate, DATE_PATTERN);
    }


    /**
     * Validate date format with regular expression
     * param : date date address for validation
     * @return true valid date fromat, false invalid date formatF
     */
    public static boolean validate(String szDate, String datePattern){

        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(szDate);

        if(matcher.matches()) {

            matcher.reset();

            if(matcher.find()) {

                String day = matcher.group(3);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(1));

                if (day.equals("31") &&
                        (
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 01,03,05,07,08,10,12 has 31 days
                } else if (month.equals("02")) {
                    //leap year
                    if(year % 4==0) {
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }




    public static String formattedDate(String date, String fromFormatString, String toFormatString){
        if(date == null || "".equals(date)) {
            return "";
        }
        SimpleDateFormat fromFormat =
                new SimpleDateFormat(fromFormatString);
        SimpleDateFormat toFormat =
                new SimpleDateFormat(toFormatString);
        Date fromDate = null;

        try
        {
            fromDate = fromFormat.parse(date);
        }
        catch(ParseException e)
        {
            fromDate = new Date();
        }

        return toFormat.format(fromDate);
    }


    //////////////// 아래 mehtod 들은 모두 하나로 합칠 수 있도록!! //////////////////////

    /**
     * 데이터 포맷에 맞춰서 어제일자 가져오기
     * @param format
     * @return
     */
    public static String getYesterdayByFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.DAY_OF_MONTH, -1);
        return sdf.format(nowCal.getTime());
    }

    /**
     * 특정 기준일에 해당되는 월의 처음 시작일
     * @param format
     * @param diff
     * @return
     */
    public static String getFirstDayOfDiffDayByFormat(String format, int diff) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.DAY_OF_MONTH, diff);
        nowCal.set(Calendar.DATE, 1);
        return sdf.format(nowCal.getTime());
    }

    /**
     * 현재 날짜 기준 하루 전일이 포함되는 월의 마지막 날짜를 가져오기
     * @param format
     * @return
     */
    public static String getLastDateOfMonthByFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        nowCal.set(Calendar.DAY_OF_MONTH, -1);
        return sdf.format(nowCal.getMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 금일 날짜를 포맷에 맞춰서 가져오기 (getYesterdayByFormat와 합치자)
     * @param format
     * @return
     */
    public static String getTodayByFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        return sdf.format(nowCal.getTime());
    }

    public static String getNextFirstDate(String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);

        return new SimpleDateFormat(format).format(cal.getTime());
    }


    /**
     * 현재 날짜를 기준으로 지난 달 가져오기
     * @param format
     * @return
     */
    public static String getLastMonthByFormat(String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.MONTH, -1);
        return sdf.format(nowCal.getTime());

    }

    /**
     * 현재 날짜를 기준으로 DAY +, - 가져오기 (지정한 포맷)
     * @param format
     * @param diffDay
     * @return
     */
    public static String getDateByFormatAndDiffDay(String format, int diffDay) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.DAY_OF_MONTH, diffDay);
        return sdf.format(nowCal.getTime());
    }

    /**
     * 입력 날짜 문자열을 기준으로 DAY +, - 가져오기 (지정한 포맷)
     */
    public static String getDateStringPlusDay(String inputDate, String formatString, int diffDay) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
        return LocalDate.parse(inputDate, format).plusDays(diffDay).format(format);
    }
    /**
     * 입력 날짜 문자열을 기준으로 month +, - 가져오기 (지정한 포맷)
     */
    public static String getDateStringPlusMonth(String inputDate, String formatString, int diffMonth) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
        return YearMonth.parse(inputDate, format).plusMonths(diffMonth).format(format);
    }
    /**
     * 현재 날짜를 기준으로 월 단위 연산에 의한 기준월 조회
     * @param format
     * @return
     */
    public static String getMonthOfDiffByFormat(String format, int diffMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.MONTH, diffMonth);
        return sdf.format(nowCal.getTime());
    }

    public static String getFormatedDayByStdMonth(String format, String month, int diff, boolean bLastDay) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(month.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(month.substring(4, 6)) - 1);
        calendar.add(Calendar.MONTH, diff);
        calendar.set(Calendar.DAY_OF_MONTH, bLastDay ? calendar.getActualMaximum(Calendar.DAY_OF_MONTH) : 1);

        return sdf.format(calendar.getTime());
    }


    /**
     * 만 나이 기준으로 연령대 구하기
     * @param birthday
     * @return
     */
    public static String calculateAgeGroup(String birthday) {

        int age = calculateAge(birthday); // 만 나이 구하기

        if(age < 10){
            return "";
        }
        else if(age >= 60){
            return "60";
        }

        return String.valueOf(age / 10 * 10);
    }

    /**
     * 만 나이 구하기
     * @param birthday
     * @return
     */
    public static int calculateAge(String birthday) {

        if (StringUtils.isEmpty(birthday) || birthday.length() != 8) {
            return -1;
        }

        Date dbDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            dbDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            return -1;
        }

        long timeDiff = System.currentTimeMillis() - dbDate.getTime();
        int age = (int) (timeDiff / 31558464000L);

        return age;
    }

    public static Timestamp getNowTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        return timestamp;
    }

    public static Timestamp getTimestampAfterHour(long hours) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now.plusHours(hours));
        return timestamp;
    }
    public static Timestamp getTimestampAfterMinutes(long minutes) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now.plusMinutes(minutes));
        return timestamp;
    }

    public static String timestampToString(Timestamp timestamp, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(timestamp);
        } catch (Exception ex) {
            throw new RuntimeException("timestampToString exception!");
        }
    }

    public static Timestamp stringToTimeStamp(String timeString, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return new Timestamp(  formatter.parse(timeString).getTime() ) ;
        } catch (Exception ex) {
            throw new RuntimeException("stringToTimeStamp exception!");
        }
    }



}
