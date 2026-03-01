package DAOImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Composers;
import DAO.ComposersDao;

public class ComposersDaoImpl implements ComposersDao {

	public void adaugaComposers(Composers composer) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(composer);
		transaction.commit();
		session.close();
	}

	public List<Composers> afiseazaComposers() {
		List<Composers> listaComposers = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
	        listaComposers = session.createQuery("from Composers", Composers.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close(); 
	    }
	    return listaComposers;
	}

	public void modificaComposers(int idcomposer, String Last_name, String First_name, int Age, int Nr_works) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Composers detaliiComposers = (Composers) session.find(Composers.class, idcomposer);
		detaliiComposers.setLast_name(Last_name);
		detaliiComposers.setFirst_name(First_name);
		detaliiComposers.setAge(Age);
		detaliiComposers.setNr_works(Nr_works);
		session.merge(detaliiComposers);
		transaction.commit();
		session.close();
	}

	public void stergeComposers(Composers composer) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.remove(composer);
		transaction.commit();
		session.close();
	}
}