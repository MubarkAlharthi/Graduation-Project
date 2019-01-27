
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
 * Servlet implementation class getTable_Real_Bot
 */
@WebServlet("/getTable_Real_Bot")
public class getTable_Real_Bot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTable_Real_Bot() {
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
			String sql = "SELECT Users.id, Users.name, Users.screen_name, Users.followers_count, TrningSetRealBot.Label, Tweets.source FROM Users  INNER JOIN TrningSetRealBot ON Users.id = TrningSetRealBot.id INNER JOIN Tweets ON Users.id = Tweets.users_id where TrningSetRealBot.Label != \"\" group by Users.id limit 50;";
			System.out.println(sql);
			
			System.out.println(System.currentTimeMillis()/1000);
			ResultSet result = getJson.getResultQuery(crawler.db, sql);
			System.out.println(System.currentTimeMillis()/1000);

			jsonArr = convertToJsonArray(result);

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
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
}
