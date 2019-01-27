package mainPkg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.JSONObject;

import twitter4j.JSONArray;
import cralwer.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getTable
 */
@WebServlet("/getTable")
public class getTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTable() {
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
		JSONArray jsonArr = new JSONArray();
		try {
			// Open a connection
			crawler = new Cralwer();
			
			//get parameters
//			int draw = Integer.parseInt(request.getParameter("draw"));
//			jsonObj.put("draw", draw++);
//			int start = Integer.parseInt(request.getParameter("start"));
//			int length = Integer.parseInt(request.getParameter("length"));
//			String search = request.getParameter("search");
//			System.out.println(draw+"="+start+"="+length+"="+search);
			
			// Execute SQL query
			String sql = "SELECT Users.id, Users.name, Users.screen_name, Users.followers_count, TrningSetTweets.Lable FROM Users  INNER JOIN TrningSetTweets ON Users.id = TrningSetTweets.Tweet_user_id INNER JOIN Tweets ON Users.id = Tweets.users_id where TrningSetTweets.Lable != \"\" group by Users.id limit 50;";
			System.out.println(sql);			
			System.out.println(System.currentTimeMillis()/1000);
			ResultSet result = getJson.getResultQuery(crawler.db, sql);
			System.out.println(System.currentTimeMillis()/1000);
			jsonArr = convertToJsonArray(result);

			
//			sql = "select max(countSources) as mostSource from (select Users.id,count(Tweets.source) as countSources,Tweets.source\n" + 
//					"from Users inner join Tweets on Users.id = Tweets.users_id\n" + 
//					"where Users.screen_name = \"DonatLuffy\"\n" + 
//					"group by Tweets.source) ";
//			result = getJson.getResultQuery(crawler.db, sql);
//			getJson.appendToJsonArray(result, jsonArr);
//			
			
//			int recordsTotal = jsonArr.length();
//			jsonObj.put("recordsTotal", recordsTotal);
//			jsonObj.put("recordsFiltered", recordsTotal);
			
//			jsonObj.put("data", jsonArr.toString());
			System.out.println(jsonArr.toString());
			
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(jsonArr.toString());

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			jsonObj.put("error", e.getMessage());

		} finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonArr.toString());
			System.out.println("finally");
			crawler.db.close();
		}
	}
	private JSONArray convertToJsonArray(ResultSet result) throws SQLException {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
