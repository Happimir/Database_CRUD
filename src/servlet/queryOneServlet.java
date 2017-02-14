package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import connector.DbUtils;



/**
 * Servlet implementation class queryOneServlet
 */
@WebServlet("/queryOneServlet")
public class queryOneServlet extends HttpServlet {
	
	Connection conn = DbUtils.connect();
	PreparedStatement stmt = null; 
	
	String query = 
			"SELECT DISTINCT d.dept_name, count(e.emp_no), AVG(s.salary), ROUND(YEAR(e.birth_date), -1) AS birth_date" 
			+" FROM employees e, departments d, salaries s, dept_emp de" 
			+" WHERE de.emp_no = e.emp_no AND de.dept_no = d.dept_no" 
				+" AND e.emp_no = s.emp_no" 
				+" GROUP BY d.dept_name," 
				+" ROUND(YEAR(e.birth_date), -1)";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			System.out.println("query: " + query);
			stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			
			while(rs.next()) {
				String dept_name = rs.getString("dept_name");
				int count = rs.getInt("count(e.emp_no)");
				double salary = rs.getDouble("AVG(s.salary)");
				int birthDecade = rs.getInt("birth_date");
				
				System.out.println(dept_name + " " + count + " " + salary + " " + birthDecade);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

}
