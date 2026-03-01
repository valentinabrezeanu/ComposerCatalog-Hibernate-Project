package pojo;

import java.util.Date;

public class Collaborations implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idcollaboration;
	private Compositions compositions;
	private Composers composers;
	private String Role_composer;
	private Date Start_date;
	
	public Collaborations() {
	}

	public Collaborations(Compositions compositions, Composers composers, String Role_composer, Date Start_date) {
		this.compositions = compositions;
		this.composers = composers;
		this.Role_composer = Role_composer;
		this.Start_date = Start_date;

	}

	public Integer getIdcollaboration() {
		return this.idcollaboration;
	}

	public void setIdcollaboration(Integer idcollaboration) {
		this.idcollaboration = idcollaboration;
	}

	public Compositions getCompositions() {
		return this.compositions;
	}

	public void setCompositions(Compositions compositions) {
		this.compositions = compositions;
	}

	public Composers getComposers() {
		return this.composers;
	}

	public void setComposers(Composers composers) {
		this.composers = composers;
	}
	
	public String getRole_composer() {
		return this.Role_composer;
	}

	public void setRole_composer(String Role_composer) {
		this.Role_composer = Role_composer;
	}

	public Date getStart_date() {
		return this.Start_date;
	}

	public void setStart_date(Date Start_date) {
		this.Start_date = Start_date;
	}

}