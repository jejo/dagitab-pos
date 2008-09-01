package main;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;
import java.util.Calendar;

public class ResourceManager {

	private static HashMap resources = new HashMap();
	private static Vector users = new Vector();
	private static ResourceManager instance = new ResourceManager();
	private static Calendar c = Calendar.getInstance();
//	private static Color labelColor = ResourceManager.getColor()
	private static DisposeListener disposeListener = new DisposeListener() {
		public void widgetDisposed(DisposeEvent e) {
			users.remove(e.getSource());
			if (users.size() == 0)
				dispose();
		}
	};

	public static void registerResourceUser(Widget widget) {
		if (users.contains(widget))
			return;
		users.add(widget);
		widget.addDisposeListener(disposeListener);
	}

	public static void dispose() {
		Iterator it = resources.keySet().iterator();
		while (it.hasNext()) {
			Object resource = resources.get(it.next());
			if (resource instanceof Font)
				((Font) resource).dispose();
			else if (resource instanceof Color)
				((Color) resource).dispose();
			else if (resource instanceof Image)
				((Image) resource).dispose();
			else if (resource instanceof Cursor)
				((Cursor) resource).dispose();
		}
		resources.clear();
	}

	public static Font getFont(String name, int size, int style) {
		return getFont(name, size, style, false, false);
	}

	public static Font getFont(String name, int size, int style,
			boolean strikeout, boolean underline) {
		String fontName = name + "|" + size + "|" + style + "|" + strikeout + "|"
				+ underline;
		if (resources.containsKey(fontName))
			return (Font) resources.get(fontName);
		FontData fd = new FontData(name, size, style);
		if (strikeout || underline) {
			try {
				Class lfCls = Class
						.forName("org.eclipse.swt.internal.win32.LOGFONT");
				Object lf = FontData.class.getField("data").get(fd);
				if (lf != null && lfCls != null) {
					if (strikeout)
						lfCls.getField("lfStrikeOut").set(lf, new Byte((byte) 1));
					if (underline)
						lfCls.getField("lfUnderline").set(lf, new Byte((byte) 1));
				}
			} catch (Throwable e) {
				System.err.println("Unable to set underline or strikeout"
						+ " (probably on a non-Windows platform). " + e);
			}
		}
		Font font = new Font(Display.getDefault(), fd);
		resources.put(fontName, font);
		return font;
	}

	public static Image getImage(String url, Control widget) {
		Image img = getImage(url);
		if (img != null && widget != null)
			img.setBackground(widget.getBackground());
		return img;
	}

	public static Image getImage(String url) {
		try {
			Image img;
			url = url.replace('\\', '/');
			if (url.startsWith("/")) {
				url = url.substring(1);
				img = new Image(Display.getDefault(), instance.getClass()
						.getClassLoader().getResourceAsStream(url));
			}
			else {
				img = new Image(Display.getDefault(), url);
			}

			if (resources.containsKey(url))
				return (Image) resources.get(url);

			if (img != null)
				resources.put(url, img);
			return img;
		} catch (Exception e) {
			System.err.println("SWTResourceManager.getImage: Error getting image "
					+ url + ", " + e);
			return null;
		}
	}

	public static Image getImage(String url, int width, int height) {
		try {
			ImageData imgData;
			url = url.replace('\\', '/');
			if (url.startsWith("/")) {
				url = url.substring(1);

			}
			imgData = new ImageData(Thread.currentThread().getContextClassLoader().getResourceAsStream(url));
			
			if (resources.containsKey(url))
				return (Image) resources.get(url);

			Image img = new Image(Display.getDefault(), imgData.scaledTo(width,
					height));
			if (img != null) {
				resources.put(url, img);
			}
			return img;
		} catch (Exception e) {
			System.err.println("ResourceManager.getImage: Error getting image "
					+ url + ", " + e);
			return null;
		}
	}

	public static Image getImage(ImageData data, int width, int height) {

		return new Image(Display.getDefault(), data.scaledTo(width, height));
	}

	public static Color getColor(int red, int green, int blue) {
		String name = "COLOR:" + red + "," + green + "," + blue;
		if (resources.containsKey(name))
			return (Color) resources.get(name);
		Color color = new Color(Display.getDefault(), red, green, blue);
		resources.put(name, color);
		return color;
	}

	public static Cursor getCursor(int type) {
		String name = "CURSOR:" + type;
		if (resources.containsKey(name))
			return (Cursor) resources.get(name);
		Cursor cursor = new Cursor(Display.getDefault(), type);
		resources.put(name, cursor);
		return cursor;
	}

	public static void setGradient(final CLabel label1) {
		label1.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Display display = Display.getDefault();
				e.gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_DARK_BLUE));
				e.gc.fillGradientRectangle(0, 0, label1.getSize().x, label1.getSize().y, true);
				// e.gc.dispose ();
			}
		});
	}

	public static void fillCombo(Combo c, String[] s) {
		for (int i = 0; i < s.length; i++) {
			c.add(s[i]);
		}
	}

	public static void fillYears(Combo c) {
		int y = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 70; i >= 0; i--) {
			c.add(y - i + "");
		}
	}
	
	public static void fillYears(Combo c, int past, int future) {
		int y = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = past; y - i <= y + future; i--) {
			c.add(y - i + "");
		}
	}

	public static void fillDays(Combo c) {
		for (int i = 1; i < 32; i++) {
			c.add(i + "");
		}
	}

	public static void fillMonths(Combo c) {
		ResourceManager.fillCombo(c, new String[] { "Jan", "Feb", "Mar", "Apr",
				"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" });
	}

	public static int getDay(Date d) {
		c.setTime(d);
		return c.get(Calendar.DATE);
	}

	public static int getYear(Date d) {
		c.setTime(d);
		return c.get(Calendar.YEAR);
	}
	
	public static int getMonthInt(Date d) {
		c.setTime(d);
		return c.get(Calendar.MONTH);
	}
	
	public static String getMonth(Date d) {
		SimpleDateFormat sd = new SimpleDateFormat("MMM");
		return sd.format(d);
	}

	public static Date getDate(String month, String day, String year)
			throws Exception {
		SimpleDateFormat sd = new SimpleDateFormat("MMM d yyyy");
		return new Date(sd.parse(month+" " +day+" "+year).getTime());

	}
	
	public static String dateFormat(Date d, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(d);
	}
	
	public static String dateFormat(Timestamp d, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(d);
	}
	
	
	public static String pad(int num,int length) {
		String s = num+"";
		
		for(int i=s.length();i<length;i++) {
			s = "0" + s;
		}
		return s;
	}
	
	
//
//	public static void fillWithFields(Combo combo2, String string, String[] strings) {
//		
//		String fields="";
//		for(int i=0;i<strings.length;i++) {
//			fields += strings[i];
//			if(i != strings.length -1) fields += ",";
//		}
//		
//		ResultSet rs = Main.getDBManager().query("SELECT "+fields+ " FROM " + string);
//		
//		try {
//			while(rs.next()) {
//				String s="";
//				for(int i=1;i<strings.length+1;i++) {
//					s+=rs.getString(i)+" ";
//				}
//				combo2.add(s.trim());
//			}
//		} catch (SQLException e) {
//			LoggerUtility.getInstance().logStackTrace(e);
//		}
//		
//	}
//	
//	public static void fillWithFieldsAndConstraint(Combo combo2, String table, String[] displayCols,String[] whereCols, String[] constraints) {
//		ResultSet rs = Main.getDBManager().query(
//				displayCols,table,whereCols,constraints,true);
//		
//		try {
//			while(rs.next()) {
//				String s="";
//				for(int i=1;i<displayCols.length+1;i++) {
//					s+=rs.getString(i)+" ";
//				}
//				combo2.add(s.trim());
//			}
//		} catch (SQLException e) {
//			LoggerUtility.getInstance().logStackTrace(e);
//		}
//		
//	}

	public static String formatAmount(double amount) {
		DecimalFormat f = new DecimalFormat("0.00");
		
		return f.format(amount);
	}

	public static String getElapsed(long runTime) {		
		String s="";
		long mins = (long)(runTime / 60000);
		s+= (mins / 60) +" hr " + (mins % 60) + " min";
		return s;
	}



}
