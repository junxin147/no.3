package dao;

/**
 * @author Alan
 */
public class FactoryDao
{
	public static UserDao getUserDao(){
		return new UserDao();
	}
	public static FrontUser getFrontUser(){
		return new FrontUser();
	}
	public static MenuDao getMenuDao(){
		return new MenuDao();
	}
	public static CalendarDao getCalendarDao(){
		return new CalendarDao();
	}
	public static SubjectDao getSubjectDao(){
		return new SubjectDao();
	}
	public static ReportDao getReportDao(){
		return new ReportDao();
	}
	public static MyCountDao getMyCountDao(){
		return new MyCountDao();
	}

}
