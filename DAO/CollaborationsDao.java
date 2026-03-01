package DAO;

import java.util.List;
import pojo.Collaborations;
import java.util.Date;
import pojo.Compositions;
import pojo.Composers;

public interface CollaborationsDao {

	public void adaugaCollaborations(Collaborations collaboration);

	public List<Collaborations> afiseazaCollaborations();

	public void modificaCollaborations(Integer idcollaboration, Compositions compositions, Composers composers, String Role_composer, Date Start_date);

	public void stergeCollaborations(Collaborations collaboration);
}