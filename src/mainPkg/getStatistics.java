package mainPkg;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import cralwer.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class getStatistics
 */
@WebServlet("/getStatistics")
public class getStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getStatistics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cralwer crawler = null;
		JSONObject jsonObj = new JSONObject();
		ResultSet result;
		String sql;
		try {
			// Open a connection
			crawler = new Cralwer();


// Execute SQL query
			sql = "SELECT count(id) FROM Users;";
			insertToJsonObject(sql, "users_count", jsonObj, crawler.db);

			sql = "SELECT count(is_Labled) FROM TrningSetTweets where is_Labled = \"1\";";
			insertToJsonObject(sql, "label_count", jsonObj, crawler.db);

			sql = "SELECT count(id) FROM Tweets;";
			insertToJsonObject(sql, "tweets_count", jsonObj, crawler.db);

			String regexp = "Saudi|Riyadh|السعودية|سعودي|Al Khobar|الرياض|Dammam|Jeddah|Buraydah|Bisha|Taif|Makkah Al Mukarrama|Abha|Asir|Khamis Mushait|Hail|Al Madinah Al Munawwarah|Jubail Industrial City|Tabuk|Ahsa|Unayzah|Al Bukairiyah|Al Majmah|Zulfi|Najran|Arar|Al Kharj|Hafr Al Batin|Sharurah|Rafha|Afif|Baqaiq|Raniah|Sakaka|Dawmat Al Jandal|Al Qurayat|Khafji|Yanbu|Baha|Jazan|Qassim|Qatif|KSA|ksa|Ksa|الدمام|جدة|المنورة|مكة|المكرمة|نجران|Kingdom of Saudi Arabia";
			int countFromKsa = sqlToRegex(crawler.db, "SELECT Users.location FROM Users", regexp);
			String countKsaString = Integer.toString(countFromKsa);
			jsonObj.put("fromKSA_count", countKsaString);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonObj.toString());

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
//			crawler.db.close();
		}

	}

	private void insertToJsonObject(String sql, String key, JSONObject jsonObj, connDB db) throws SQLException {
		try {
			Statement stmt = db.conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			jsonObj.put(key, result.getString(1));

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public int sqlToRegex(connDB db, String sql, String regex) throws SQLException {
		int count = 0;
		Statement stmt = db.conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);

		// Create a Pattern object
		Pattern r = Pattern.compile(regex);
		while (result.next()) {
			Matcher m = r.matcher(result.getString(1));
			if (m.find()) {
				count++;
			}
		}
		return count;
	}

}
