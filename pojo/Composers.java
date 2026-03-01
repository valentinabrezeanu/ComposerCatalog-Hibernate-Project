package pojo;

import java.util.HashSet;
import java.util.Set;

public class Composers implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idcomposer;
	private String Last_name;
	private String First_name;
	private int Age;
	private int Nr_works;

	private Set<Collaborations>  Coll = new HashSet<>(0);

	public Composers() {
	}

	public Composers(String Last_name, String First_name, int Age,int Nr_works, Set<Collaborations> Coll) {
		this.Last_name = Last_name;
		this.First_name = First_name;
		this.Age = Age;
		this.Nr_works = Nr_works;
		this.Coll = Coll;
	}

	public Integer getIdcomposer() {
		return this.idcomposer;
	}

	public void setIdcomposer(Integer idcomposer) {
		this.idcomposer = idcomposer;
	}

	public String getLast_name() {
		return this.Last_name;
	}

	public void setLast_name(String Last_name) {
		this.Last_name = Last_name;
	}

	public String getFirst_name() {
		return this.First_name;
	}

	public void setFirst_name(String First_name) {
		this.First_name = First_name;
	}

	public int getAge() {
		return this.Age;
	}

	public void setAge(int Age) {
		this.Age = Age;
	}
	
	public int getNr_works() {
		return this.Nr_works;
	}

	public void setNr_works(int Nr_works) {
		this.Nr_works = Nr_works;
	}

	public Set<Collaborations> getColl() {
		return this.Coll;
	}

	public void setColl(Set<Collaborations> Coll) {
		this.Coll = Coll;
	}
}