package Servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import DAO.etudao;

/**
 * Servlet implementation class Serchprofemplois
 */
public class Serchprofemplois extends HttpServlet {
	private static final long serialVersionUID = 1L;
	etudao et;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serchprofemplois() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		et = new etudao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ids = request.getParameter("idprof"); int idProf =0;HttpSession session = request.getSession();
		
		try {
		    idProf = Integer.parseInt(ids);
		    
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		session.setAttribute("id", idProf);
		
		 ResultSet rs = et.findemploiprf(idProf);
	        session.setAttribute("rs", rs);
	        Object idObject = session.getAttribute("id");
	        int idProf1 = Integer.parseInt(idObject.toString());
	        rs = et.matprf(idProf1);
	        session.setAttribute("rsm", rs);
	        
	        PrintWriter out = response.getWriter();

			
	        
	        response.sendRedirect("admprfemplsearch.jsp");
	
	}

}
