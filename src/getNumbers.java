

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainPkg.getJson;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
import cralwer.*;

/**
 * Servlet implementation class getNumbers
 */
@WebServlet("/getNumbers")
public class getNumbers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getNumbers() {
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
		String sql = null;
		try {
			// Open a connection
			crawler = new Cralwer();
			// Execute SQL query
			sql = "select count(TrningSetTweets.Tweet_id) from TrningSetTweets;";
			insertToJsonObject(sql, "Tweets_are_labeled", jsonObj, crawler.db);

			sql = "select count(TrningSetTweets.Lable) from TrningSetTweets;";
			insertToJsonObject(sql, "Tweets_not_labeled", jsonObj, crawler.db);

			sql = "select count(TrningSetRealBot.Label) from TrningSetRealBot where TrningSetRealBot.Label = \"Bot\"";
			insertToJsonObject(sql, "number_of_bot", jsonObj, crawler.db);

			sql = "select count(TrningSetRealBot.Label) from TrningSetRealBot where TrningSetRealBot.Label = \"Real\"";
			insertToJsonObject(sql, "number_of_real", jsonObj, crawler.db);
			
			System.out.println(jsonObj.toString());

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
