package mainPkg;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
 * Servlet implementation class getJson
 */
@WebServlet("/getJson")
public class getJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cralwer crawler = null;
		try {
			// Open a connection
			crawler = new Cralwer();
			// Execute SQL query
			String sql = "SELECT * FROM Users limit 1;";
			Statement stmt = crawler.db.conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			JSONObject jsonObj = getJson.addToJsonObj(result);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonObj.toString());

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			crawler.db.close();
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static ResultSet getResultQuery(connDB db, String sql) throws SQLException {
		System.out.println("Are locked?");
		Statement stmt = db.conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);

		return result;
	}

	public static JSONArray convertToJsonArray(ResultSet result) throws SQLException {
		JSONArray jsonArr = new JSONArray();
		ResultSetMetaData rsmd = result.getMetaData();

		while (result.next()) {
			int numColumns = rsmd.getColumnCount();
			JSONObject jsonObj = new JSONObject();

			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = rsmd.getColumnName(i);
				jsonObj.put(column_name, result.getObject(i));
			}

			jsonArr.put(jsonObj);
		}
		return jsonArr;
	}
	public static JSONArray appendToJsonArray(ResultSet result, JSONArray jsonArr) throws SQLException {
		
		ResultSetMetaData rsmd = result.getMetaData();

		while (result.next()) {
			int numColumns = rsmd.getColumnCount();
			JSONObject jsonObj = new JSONObject();

			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = rsmd.getColumnName(i);
				jsonObj.put(column_name, result.getObject(i));
			}

			jsonArr.put(jsonObj);
		}
		return jsonArr;
	}
	public static JSONObject addToJsonObj(ResultSet result) throws SQLException {
		
		ResultSetMetaData rsmd = result.getMetaData();
		JSONObject jsonObj = new JSONObject();
		int numColumns = rsmd.getColumnCount();
		for (int i = 1; i < numColumns + 1; i++) {
			String column_name = rsmd.getColumnName(i);
			jsonObj.put(column_name, result.getObject(i));
		}
		return jsonObj;
	}

}
