package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Composers;
import DAOImpl.ComposersDaoImpl;

public class ComposersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Composers composer = new Composers();
    ComposersDaoImpl composersDaoImpl = new ComposersDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaComposers", composersDaoImpl.afiseazaComposers());
        request.getRequestDispatcher("tabela_Composers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("adaugaComposers") != null) {
            String lastNameParam = request.getParameter("Last_name");
            String firstNameParam = request.getParameter("First_name");
            String ageParam = request.getParameter("Age");
            String nrWorksParam = request.getParameter("Nr_works");

            if (lastNameParam == null || lastNameParam.trim().isEmpty() || 
                firstNameParam == null || firstNameParam.trim().isEmpty() || 
                ageParam == null || ageParam.trim().isEmpty() || 
                nrWorksParam == null || nrWorksParam.trim().isEmpty()) {
                
                request.setAttribute("mesajEroare", "Please fill in all fields.");
                request.getRequestDispatcher("adauga_Composers.jsp").forward(request, response);
                return;
            }

            try {
                Composers nouComposer = new Composers(); 
                nouComposer.setLast_name(lastNameParam);
                nouComposer.setFirst_name(firstNameParam);
                nouComposer.setAge(Integer.parseInt(ageParam));
                nouComposer.setNr_works(Integer.parseInt(nrWorksParam));
                
                composersDaoImpl.adaugaComposers(nouComposer);
                request.setAttribute("mesajSucces", "The changes have been successfully made!");
            } catch (Exception e) {
                request.setAttribute("mesajEroare", "Error saving: " + e.getMessage());
            }
            request.getRequestDispatcher("adauga_Composers.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("afiseazaComposers") != null) {
            request.setAttribute("listaComposers", composersDaoImpl.afiseazaComposers());
            request.getRequestDispatcher("tabela_Composers.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("modificaComposers") != null) {
            String lastNameParam = request.getParameter("Last_name");
            String firstNameParam = request.getParameter("First_name");
            String ageParam = request.getParameter("Age");
            String nrWorksParam = request.getParameter("Nr_works");
            String idParam = request.getParameter("idcomposer");
            
            if (lastNameParam == null || lastNameParam.trim().isEmpty() || 
                firstNameParam == null || firstNameParam.trim().isEmpty() ||
                ageParam == null || ageParam.isEmpty() ||
                nrWorksParam == null || nrWorksParam.isEmpty()) {
                
                request.setAttribute("mesajEroare", "Please fill in all fields before saving.");
                request.setAttribute("listaComposers", composersDaoImpl.afiseazaComposers());
                request.getRequestDispatcher("tabela_Composers.jsp").forward(request, response);
                return;
            }

            try {
                int id1 = Integer.parseInt(idParam);
                int age = Integer.parseInt(ageParam);
                int nrWorks = Integer.parseInt(nrWorksParam);
                
                composersDaoImpl.modificaComposers(id1, lastNameParam, firstNameParam, age, nrWorks);
                request.setAttribute("mesajSucces", "The changes have been successfully made!");
            } catch (NumberFormatException e) {
                request.setAttribute("mesajEroare", "Invalid number format for Age or Works.");
            } catch (Exception e) {
                request.setAttribute("mesajEroare", "Error: " + e.getMessage());
            }
            request.setAttribute("listaComposers", composersDaoImpl.afiseazaComposers());
            request.getRequestDispatcher("tabela_Composers.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("stergeComposers") != null) {
            try {
                String idParam = request.getParameter("idcomposer");
                if (idParam != null && !idParam.isEmpty()) {
                    int id2 = Integer.parseInt(idParam);
                    Composers cSterge = new Composers();
                    cSterge.setIdcomposer(id2);
                    composersDaoImpl.stergeComposers(cSterge);
                    request.setAttribute("mesajSucces", "Record deleted successfully!");
                }
            } catch (Exception e) {
                request.setAttribute("mesajEroare", "Error during deletion.");
            }
            request.setAttribute("listaComposers", composersDaoImpl.afiseazaComposers());
            request.getRequestDispatcher("tabela_Composers.jsp").forward(request, response);
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}