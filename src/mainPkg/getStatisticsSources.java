package mainPkg;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class getStatisticsSources
 */
@WebServlet("/getStatisticsSources")
public class getStatisticsSources extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getStatisticsSources() {
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
		String sql;
		try {
			// Open a connection
			crawler = new Cralwer();

			sql = "select count(Tweets.source) From Tweets where Tweets.source = \"<a href=\"\"http://twitter.com/download/iphone\"\" rel=\"\"nofollow\"\">Twitter for iPhone</a>\""
											 			  + " or Tweets.source = \"<a href=\"\"http://www.apple.com\"\" rel=\"\"nofollow\"\">iOS</a>\""
											 			  + " or Tweets.source = \"<a href=\"\"https://twitterrific.com/ios\"\" rel=\"\"nofollow\"\">Twitterrific for iOS</a>\""
											 			  + " or Tweets.source = \"<a href=\"\"http://tapbots.com/tweetbot\"\" rel=\"\"nofollow\"\">Tweetbot for iOS</a>\";";
			insertToJsonObject(sql, "iphone", jsonObj, crawler.db);

			sql = "select count(Tweets.source) From Tweets where Tweets.source = \"<a href=\"\"http://twitter.com/download/android\"\" rel=\"\"nofollow\"\">Twitter for Android</a>\";";
			insertToJsonObject(sql, "android", jsonObj, crawler.db);

			sql = "select count(Tweets.source) from Tweets where Tweets.source = \"<a href=\"\"http://twitter.com\"\" rel=\"\"nofollow\"\">Twitter Web Client</a>\";";
			insertToJsonObject(sql, "webClient", jsonObj, crawler.db);

			sql = "select count(Tweets.source) From Tweets ;";
			insertToJsonObject(sql, "all", jsonObj, crawler.db);

			sql = "select count(Tweets.source) as count, Tweets.source From Tweets group by Tweets.source ORDER BY Count(Tweets.source ) desc limit 5;";
			JSONArray jsonArr = getJson.convertToJsonArray(getJson.getResultQuery(crawler.db, sql));
			jsonObj.put("sources", jsonArr);

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
		ResultSet result;
		try {
			result = getJson.getResultQuery(db, sql);
			jsonObj.put(key, result.getString(1));

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

}
