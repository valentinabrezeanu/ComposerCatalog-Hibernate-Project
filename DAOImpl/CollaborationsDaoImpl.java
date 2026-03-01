package DAOImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Collaborations;
import DAO.CollaborationsDao;
import java.util.Date;
import pojo.Composers;
import pojo.Compositions;

public class CollaborationsDaoImpl implements CollaborationsDao {
	public void adaugaCollaborations(Collaborations collaboration) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(collaboration);
		transaction.commit();
		session.close();
	}

	public List<Collaborations> afiseazaCollaborations() {
		List<Collaborations> listaCollaborations = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
	        listaCollaborations = session.createQuery("from Collaborations", Collaborations.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close(); 
	    }
	    return listaCollaborations;
	}

	public void modificaCollaborations(Integer idcollaboration, Compositions composition, Composers composer, String Role_composer, Date Start_date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Collaborations detaliiCollaborations;
		detaliiCollaborations = (Collaborations) session.find(Collaborations.class, idcollaboration);
		detaliiCollaborations.setCompositions(composition);
		detaliiCollaborations.setComposers(composer);
		detaliiCollaborations.setRole_composer(Role_composer);
		detaliiCollaborations.setStart_date(Start_date);
	
		session.merge(detaliiCollaborations);
		transaction.commit();
		session.close();
	}

	public void stergeCollaborations(Collaborations collaboration) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.remove(collaboration);
		transaction.commit();
		session.close();
	}
}