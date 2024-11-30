package restservlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("password", password);

        // URL de l'endpoint de l'API REST
        String apiUrl = "http://localhost:8080/Soa_projet/services/login?email=" + email + "&password=" + password;

        try {
            // Créer l'objet URL
            URL url = new URL(apiUrl);

            // Ouvrir la connexion
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Définir la méthode de requête
            conn.setRequestMethod("GET");

            // Obtenir le code de réponse
            int responseCode = conn.getResponseCode();

            // Vérifier si la requête a réussi
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lire la réponse
                Jsonb jsonb = JsonbBuilder.create();
                ResultSet rs = jsonb.fromJson(conn.getInputStream(), ResultSet.class);

                // Traiter la réponse
                if (rs != null) {
                    while (rs.next()) {
                        String pwd = rs.getString("mdp");
                        String em = rs.getString("mail");
                        String rl = rs.getString("role");
                        int idf = rs.getInt("id");

                        String opwd = (String) session.getAttribute("password");
                        String oem = (String) session.getAttribute("email");

                        if (opwd.equals(pwd) && em.equals(oem)) {
                            session.setAttribute("fullid", idf);
                            session.setAttribute("fullnm", rs.getString("nom"));
                            session.setAttribute("fullpn", rs.getString("prenom"));
                            session.setAttribute("fullmail", rs.getString("mail"));
                            session.setAttribute("fullrl", rs.getString("role"));

                            if (rl.equals("etudiant")) {
                                response.sendRedirect("premiereetudin.jsp");
                            } else if (rl.equals("Prof")) {
                                response.sendRedirect("premiereprofs.jsp");
                            } else if (rl.equals("admin")) {
                                response.sendRedirect("Dashboard.jsp");
                            }
                        } else {
                            response.sendRedirect("form/Login.jsp");
                        }
                    }
                } else {
                    // Gérer le cas où le ResultSet est null
                    out.println("ResultSet is null");
                }
            } else {
                // Gérer la réponse d'erreur
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve data from the REST API!</h3>");
            }
        } catch (SQLException e) {
            // Gérer les exceptions
            e.printStackTrace();
            out.println("<h3> Failed to connect to the REST API! </h3>");
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
