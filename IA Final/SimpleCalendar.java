package iA_Work;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class SimpleCalendar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		System.out.println("Current Date");
		int dd=cl.get(Calendar.DATE);
		int mm=cl.get(Calendar.MONTH);
		
		++mm;
		
		int yy=cl.get(Calendar.YEAR);
		System.out.println(dd+"-"+mm+"-"+yy);
		
		System.out.println("Current Time");
		
		int h=cl.get(Calendar.HOUR );
		int m=cl.get(Calendar.MINUTE);
		int s=cl.get(Calendar.SECOND);
		
		System.out.println(h+":"+m+":"+s);
		
		LocalDate ld = LocalDate.of(yy, mm, dd );
		System.out.println(ld);
		LocalDate nextMonday = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		System.out.println(nextMonday);
		LocalDate prevMonday = ld.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
		System.out.println(prevMonday);
		ld = prevMonday;
		prevMonday = ld.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
		System.out.println(prevMonday);
	}

}
