package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Compositions;
import DAOImpl.CompositionsDaoImpl;

public class CompositionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Compositions composition = new Compositions();
	CompositionsDaoImpl compositionDaoImpl = new CompositionsDaoImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    if (request.getParameter("adaugaCompositions") != null) {
	        String titleParam = request.getParameter("Title");
	        String dateParam = request.getParameter("Release_date");
	        String durationParam = request.getParameter("Duration");
	        String genreParam = request.getParameter("Genre");

	        if (titleParam == null || titleParam.trim().isEmpty() || 
	            dateParam == null || dateParam.trim().isEmpty() || 
	            durationParam == null || durationParam.trim().isEmpty() || 
	            genreParam == null || genreParam.trim().isEmpty()) {
	            
	            request.setAttribute("mesajEroare", "Please fill in all fields.");
	            request.getRequestDispatcher("adauga_Compositions.jsp").forward(request, response);
	            return;
	        }

	        try {
	            Compositions nouaCompozitie = new Compositions(); 
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            nouaCompozitie.setTitle(titleParam);
	            nouaCompozitie.setRelease_date(df.parse(dateParam));
	            nouaCompozitie.setDuration(Integer.parseInt(durationParam));
	            nouaCompozitie.setGenre(genreParam);
	            
	            compositionDaoImpl.adaugaCompositions(nouaCompozitie);
	            request.setAttribute("mesajSucces", "The changes have been successfully made!");
	        } catch (Exception e) {
	            request.setAttribute("mesajEroare", "Error saving: " + e.getMessage());
	        }
	        request.getRequestDispatcher("adauga_Compositions.jsp").forward(request, response);
	        return;
	    }

	    if (request.getParameter("afiseazaCompositions") != null) {
	        request.setAttribute("listaCompositions", compositionDaoImpl.afiseazaCompositions());
	        request.getRequestDispatcher("tabela_Compositions.jsp").forward(request, response);
	        return;
	    }

	    if (request.getParameter("modificaCompositions") != null) {
	        String titleParam = request.getParameter("Title");
	        String dateParam = request.getParameter("Release_date");
	        String durationParam = request.getParameter("Duration");
	        String genreParam = request.getParameter("Genre");
	        
	        if (durationParam == null || durationParam.trim().isEmpty() || titleParam.trim().isEmpty()) {
	            request.setAttribute("mesajEroare", "Please fill in all fields.");
	            request.setAttribute("listaCompositions", compositionDaoImpl.afiseazaCompositions());
	            request.getRequestDispatcher("tabela_Compositions.jsp").forward(request, response);
	            return;
	        }

	        try {
	            int id1 = Integer.parseInt(request.getParameter("idcomposition"));
	            int duration = Integer.parseInt(durationParam);
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            Date releaseDate = df.parse(dateParam);
	            
	            compositionDaoImpl.modificaCompositions(id1, titleParam, releaseDate, duration, genreParam);
	            request.setAttribute("mesajSucces", "The changes have been successfully made!");
	        } catch (Exception e) {
	            request.setAttribute("mesajEroare", "Invalid data: " + e.getMessage());
	        }
	        request.setAttribute("listaCompositions", compositionDaoImpl.afiseazaCompositions());
	        request.getRequestDispatcher("tabela_Compositions.jsp").forward(request, response);
	        return;
	    }

	    if (request.getParameter("stergeCompositions") != null) {
	        try {
	            int id2 = Integer.parseInt(request.getParameter("idcomposition"));
	            Compositions cSterge = new Compositions();
	            cSterge.setIdcomposition(id2);
	            compositionDaoImpl.stergeCompositions(cSterge);
	            request.setAttribute("mesajSucces", "Record deleted successfully!");
	        } catch (Exception e) {
	            request.setAttribute("mesajEroare", "Error during deletion.");
	        }
	        request.setAttribute("listaCompositions", compositionDaoImpl.afiseazaCompositions());
	        request.getRequestDispatcher("tabela_Compositions.jsp").forward(request, response);
	    }
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
