package pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Compositions implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idcomposition;
	private String Title;
	private Date Release_date;
	private int Duration;
	private String Genre;

	private Set<Collaborations>  Coll = new HashSet<>(0);

	public Compositions() {
	}

	public Compositions(String Title, Date Release_date, int Duration, String Genre, Set<Collaborations> Coll) {
		this.Title = Title;
		this.Release_date = Release_date;
		this.Duration = Duration;
		this.Genre = Genre;
		this.Coll = Coll;
	}

	public Integer getIdcomposition() {
		return this.idcomposition;
	}

	public void setIdcomposition(Integer idcomposition) {
		this.idcomposition = idcomposition;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public Date getRelease_date() {
		return this.Release_date;
	}

	public void setRelease_date(Date Release_date) {
		this.Release_date = Release_date;
	}

	public int getDuration() {
		return this.Duration;
	}

	public void setDuration(int Duration) {
		this.Duration = Duration;
	}
	
	public String getGenre() {
		return this.Genre;
	}

	public void setGenre(String Genre) {
		this.Genre = Genre;
	}

	public Set<Collaborations> getColl() {
		return this.Coll;
	}

	public void setColl(Set<Collaborations> Coll) {
		this.Coll = Coll;
	}
}