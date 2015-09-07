package br.com.involves.test.pojos;

public class FakePOJO {

	private int id;
	private String name;
	private TruePOJO complex;

	public FakePOJO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TruePOJO getComplex() {
		return complex;
	}

	public void setComplex(TruePOJO complex) {
		this.complex = complex;
	}

}
