package mainPkg;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.TwitterException;
import cralwer.*;

/**
 * Servlet implementation class getUserProfile
 */
@WebServlet("/getUserProfile")
public class getUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserProfile() {
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
			crawler.getAcessto();
			String Screen_name = request.getParameter("name");
			long id = crawler.get_id(Screen_name);
			
			System.out.println("Execute SQL query.."+id);
			String sql = "select * from Users inner join User_Lable on Users.id = User_Lable.id where Users.screen_name = \""+Screen_name+"\";"; // replace Users to Userwithcategories 
			ResultSet result = getJson.getResultQuery(crawler.db, sql);
			if(result.isBeforeFirst()) {//if user exist in database
				JSONObject jsonObj = addToJsonObj(result);	
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonObj.toString());
			}else {// collect user info from Twitter API
				String message = crawler.collectFromAPI(Screen_name);
				System.out.println(message);
				if(message.equals("success")) {
					System.out.println("Execute SQL query.."+id);
					sql = "select * from Users where Users.id = "+id+";"; // replace Users to Userwithcategories
					result = getJson.getResultQuery(crawler.db, sql);

					JSONObject jsonObj = addToJsonObj(result);	
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonObj.toString());
				}else {					
					response.setContentType("application/json");
					response.getWriter().write(message);				
				}
			}
		}catch (Exception e) {
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
public JSONObject addToJsonObj(ResultSet result) throws SQLException {
		
		ResultSetMetaData rsmd = result.getMetaData();
		JSONObject jsonObj = new JSONObject();
		int numColumns = rsmd.getColumnCount();
		for (int i = 1; i < numColumns + 1; i++) {
			String column_name = rsmd.getColumnName(i);
			jsonObj.put(column_name, result.getObject(i));
		}
		System.out.println(jsonObj);
		return jsonObj;
	}

}
