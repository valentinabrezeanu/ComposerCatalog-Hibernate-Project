package DAO;

import java.util.Date;
import java.util.List;
import pojo.Compositions;

public interface CompositionsDao {
	public void adaugaCompositions(Compositions composition);

	public List<Compositions> afiseazaCompositions();

	public void modificaCompositions(int idcomposition, String Title, Date Release_date, int Duration, String Genre);

	public void stergeCompositions(Compositions composition);
}