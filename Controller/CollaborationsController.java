package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import pojo.Compositions;
import pojo.Composers;
import pojo.Collaborations;
import DAOImpl.CollaborationsDaoImpl;
import DAOImpl.HibernateUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

public class CollaborationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Collaborations collaboration = new Collaborations();
	CollaborationsDaoImpl collaborationDaoImpl = new CollaborationsDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listaCollaborations", collaborationDaoImpl.afiseazaCollaborations());
		request.getRequestDispatcher("tabela_Collaborations.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("adaugaCollaborations") != null) {
			String idCompParam = request.getParameter("idcomposition");
			String idConsParam = request.getParameter("idcomposer");
			String roleParam = request.getParameter("Role_composer");
			String dateParam = request.getParameter("Start_date");

			if (idCompParam == null || idCompParam.trim().isEmpty() || 
				idConsParam == null || idConsParam.trim().isEmpty() || 
				roleParam == null || roleParam.trim().isEmpty() || 
				dateParam == null || dateParam.trim().isEmpty()) {
				
				request.setAttribute("mesajEroare", "Please fill in all fields (Select IDs, Role and Date).");
				request.getRequestDispatcher("adauga_Collaborations.jsp").forward(request, response);
				return;
			}

			try {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Compositions composition = (Compositions) session.find(Compositions.class, Integer.parseInt(idCompParam));
				Composers composer = (Composers) session.find(Composers.class, Integer.parseInt(idConsParam));
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = df.parse(dateParam);
				
				Collaborations nouaColaborare = new Collaborations();
				nouaColaborare.setCompositions(composition);
				nouaColaborare.setComposers(composer);
				nouaColaborare.setRole_composer(roleParam);
				nouaColaborare.setStart_date(startDate);
				
				collaborationDaoImpl.adaugaCollaborations(nouaColaborare);
				request.setAttribute("mesajSucces", "The changes have been successfully made!");
				session.close();
			} catch (Exception e) {
				request.setAttribute("mesajEroare", "Error saving: " + e.getMessage());
			}
			request.getRequestDispatcher("adauga_Collaborations.jsp").forward(request, response);
			return;
		}

		if (request.getParameter("afiseazaCollaborations") != null) {
			List<Collaborations> listaCollaborations = new ArrayList<>();
			listaCollaborations = collaborationDaoImpl.afiseazaCollaborations();
			request.setAttribute("listaCollaborations", listaCollaborations);
			RequestDispatcher rd = request.getRequestDispatcher("tabela_Collaborations.jsp");
			rd.forward(request, response);
			return;
		}

		if (request.getParameter("modificaCollaborations") != null) {
			String idCollParam = request.getParameter("idcollaboration");
			String idCompParam = request.getParameter("idcomposition");
			String idConsParam = request.getParameter("idcomposer");
			String roleParam = request.getParameter("Role_composer");
			String dateParam = request.getParameter("Start_date");

			if (idCollParam == null || idCompParam == null || idConsParam == null || 
				roleParam.trim().isEmpty() || dateParam.trim().isEmpty()) {
				
				request.setAttribute("mesajEroare", "Please complete all fields for modification.");
				request.setAttribute("listaCollaborations", collaborationDaoImpl.afiseazaCollaborations());
				request.getRequestDispatcher("tabela_Collaborations.jsp").forward(request, response);
				return;
			}

			try {
				Integer id1 = Integer.parseInt(idCollParam);
				Session session = HibernateUtil.getSessionFactory().openSession();
				Compositions composition = (Compositions) session.find(Compositions.class, Integer.parseInt(idCompParam));
				Composers composer = (Composers) session.find(Composers.class, Integer.parseInt(idConsParam));
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = df.parse(dateParam);
				
				collaborationDaoImpl.modificaCollaborations(id1, composition, composer, roleParam, startDate);
				request.setAttribute("mesajSucces", "The changes have been successfully made!");
				session.close();
			} catch (Exception e) {
				request.setAttribute("mesajEroare", "Invalid data: " + e.getMessage());
			}
			request.setAttribute("listaCollaborations", collaborationDaoImpl.afiseazaCollaborations());
			request.getRequestDispatcher("tabela_Collaborations.jsp").forward(request, response);
			return;
		}

		if (request.getParameter("stergeCollaborations") != null) {
			String idParam = request.getParameter("idcollaboration");
			if (idParam != null && !idParam.isEmpty()) {
				try {
					Integer id2 = Integer.parseInt(idParam);
					Collaborations cSterge = new Collaborations();
					cSterge.setIdcollaboration(id2);
					collaborationDaoImpl.stergeCollaborations(cSterge);
					request.setAttribute("mesajSucces", "Record deleted successfully!");
				} catch (Exception e) {
					request.setAttribute("mesajEroare", "Error during deletion.");
				}
			}
			request.setAttribute("listaCollaborations", collaborationDaoImpl.afiseazaCollaborations());
			request.getRequestDispatcher("tabela_Collaborations.jsp").forward(request, response);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}