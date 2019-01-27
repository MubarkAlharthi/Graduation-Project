import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cralwer.Cralwer;
import cralwer.connDB;
import mainPkg.getJson;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

/**
 * Servlet implementation class getUserWithCategories
 */
@WebServlet("/getUserWithCategories")
public class getUserWithCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserWithCategories() {
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
			String Screen_name = request.getParameter("name");
			
			JSONArray jsonArr = crawler.getInfo(Screen_name);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println("jsonArr in getUserWithCategories = "+jsonArr.toString());
			response.getWriter().write(jsonArr.toString());

		} catch (Exception e) {
			// Handle errors for Class.forName
			String str = "This user doesn't have 4 categories";
			response.getWriter().write(str);
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
