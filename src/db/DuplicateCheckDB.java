package db;

import java.sql.ResultSet;
import java.sql.Statement;

public class DuplicateCheckDB {
	Datakobling DB = new Datakobling();

	public boolean brugerCheckDuplicate(String CreateUsername) {
		try {
			Statement stmt = DB.connection.createStatement();
			String sql = "Select * from bilsaelger where saelgerbrugernavn='" + CreateUsername + "' ";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {

				return true;

			}
			rs.close();

		} catch (Exception e) {
			System.out.println("Got exception from createUserCheckDuplicate in OpretBruger");
			System.out.print(e);
		}
		return false;
	}

	public boolean kundeCheckDuplicate(long cpr, int tlf) {

		try {
			Statement stmt = DB.connection.createStatement();
			String sql = "Select * FROM kunde WHERE telefonnummer='" + tlf + "' OR cpr_nummer='" + cpr + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;

			}
			rs.close();

		} catch (Exception e) {
			System.out.println("Got exception from kundeCheckDuplicate in OpretLaanDB");
			System.out.print(e);
		}
		return false;
	}
}
