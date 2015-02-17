/**
 * 
 */
package dd.kalan.container;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;

/**
 * @author sissoko
 * @date 16 févr. 2015 01:37:39
 */
public class LoggingInterceptor implements Interceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dd.kalan.container.Interceptor#methodCalled_callback(dd.kalan.container
	 * .Context)
	 */
	@Override
	public boolean methodCalled_callback(Context ctx) {
		GregorianCalendar calendar = new GregorianCalendar();
		String msg = "Enter : " + calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.MONTH) + "/"
				+ calendar.get(Calendar.YEAR) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND) + " - "
				+ ctx.getParamValue(Container.METHOD_PARAM_NAME) + " - "
				+ ctx.getParamValue(Container.PARAMETERS_PARAM_NAME);
		Level level = (Level) ctx.getParamValue(Container.LOG_LEVEL);
		if (level == null) {
			level = Level.INFO;
		}
		logger.log(level, msg);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dd.kalan.container.Interceptor#methodReturned_callback(dd.kalan.container
	 * .Context)
	 */
	@Override
	public boolean methodReturned_callback(Context ctx) {
		GregorianCalendar calendar = new GregorianCalendar();
		String msg = "Leave : " + calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.MONTH) + "/"
				+ calendar.get(Calendar.YEAR) + " "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND) + " - "
				+ ctx.getParamValue(Container.METHOD_PARAM_NAME) + " - "
				+ ctx.getParamValue(Container.PARAMETERS_PARAM_NAME);
		Level level = (Level) ctx.getParamValue(Container.LOG_LEVEL);
		if (level == null) {
			level = Level.INFO;
		}
		String failed = (String) ctx.getParamValue(Container.LOGIN_FAILED);
		if (failed != null) {
			msg += "\n" + failed;
			ctx.removeParam(Container.LOGIN_FAILED);
		}
		logger.log(level, msg);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.container.Interceptor#log(java.lang.Throwable)
	 */
	@Override
	public void log(Context ctx) {
		Throwable throwable = (Throwable) ctx.getParamValue(EXCEPTION_NAME);
		logger.log(Level.SEVERE, throwable.getMessage(), throwable);
	}
}
