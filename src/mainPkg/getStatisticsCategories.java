package mainPkg;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.JSONObject;
import cralwer.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getStatisticsCategories
 */
@WebServlet("/getStatisticsCategories")
public class getStatisticsCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getStatisticsCategories() {
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
			// Statistics categories
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"Entertainment\";";
			insertToJsonObject(sql, "Entertainment", jsonObj, crawler.db);

			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"advertisement\";";
			insertToJsonObject(sql, "advertisement", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"economy\";";
			insertToJsonObject(sql, "economy", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"health\";";
			insertToJsonObject(sql, "health", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"news\";";
			insertToJsonObject(sql, "news", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"politics\";";
			insertToJsonObject(sql, "politics", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"religion\";";
			insertToJsonObject(sql, "religion", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"social\";";
			insertToJsonObject(sql, "social", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"sport\";";
			insertToJsonObject(sql, "sport", jsonObj, crawler.db);
	
			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets where TrningSetTweets.Lable = \"technology\";";
			insertToJsonObject(sql, "technology", jsonObj, crawler.db);
			
			sql = "select count(TrningSetTweets.Tweet_id) from TrningSetTweets;";
			insertToJsonObject(sql, "tweets_labeled", jsonObj, crawler.db);

			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets;";
			insertToJsonObject(sql, "Tweets_not_labeled", jsonObj, crawler.db);
			

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonObj.toString());

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			crawler.db.close();
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
}
