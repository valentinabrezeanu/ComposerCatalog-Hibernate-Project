package DAO;

import java.util.List;
import pojo.Composers;

public interface ComposersDao {
	public void adaugaComposers(Composers composer);

	public List<Composers> afiseazaComposers();

	public void modificaComposers(int idcomposer, String Last_name, String First_name, int Age, int Nr_works);

	public void stergeComposers(Composers composer);
}