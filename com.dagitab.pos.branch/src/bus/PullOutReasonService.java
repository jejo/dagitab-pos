package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import main.Main;

public class PullOutReasonService {
	public static Vector<String> fetchAllPullOutReasons() {
		Vector<String> reasons = new Vector<String>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT NAME FROM pull_out_reason_lu;");
		try {
			while(rs.next()) {
				try {
					reasons.add(rs.getString("NAME"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reasons;
	}
}
