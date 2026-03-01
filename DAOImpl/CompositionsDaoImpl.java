package DAOImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Compositions;
import DAO.CompositionsDao;

public class CompositionsDaoImpl implements CompositionsDao {
	public void adaugaCompositions(Compositions composition) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(composition);
		transaction.commit();
		session.close();
	}

	public List<Compositions> afiseazaCompositions() {
		List<Compositions> listaCompositions = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
	        listaCompositions = session.createQuery("from Compositions", Compositions.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close(); 
	    }
	    return listaCompositions;
	}

	public void modificaCompositions(int idcomposition, String Title, Date Release_date, int Duration, String Genre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Compositions detaliiCompositions = (Compositions) session.find(Compositions.class, idcomposition);
		detaliiCompositions.setTitle(Title);
		detaliiCompositions.setRelease_date(Release_date);
		detaliiCompositions.setDuration(Duration);
		detaliiCompositions.setGenre(Genre);
		session.merge(detaliiCompositions);

		transaction.commit();
		session.close();
	}

	public void stergeCompositions(Compositions composition) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.remove(composition);
		transaction.commit();
		session.close();
	}
}
