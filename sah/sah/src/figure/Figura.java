package figure;

public abstract class Figura 
{
	int boja;
	int oznaka;
	int i,j;
	String slika;
	public Figura(int i, int j, int boja, int oznaka, String slika)
	{
		this.i = i;
		this.j = j;
		this.boja = boja;
		this.oznaka = oznaka;
		this.slika = slika;
	}
	
	public Figura(Figura figura)
	{
		this(figura.i, figura.j, figura.boja, figura.oznaka, figura.slika);
	}
	
	public String getSlika() {
		return slika;
	}

	public int getBoja() {
		return boja;
	}

	public int getOznaka() {
		return oznaka;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public abstract boolean pomeranje(int i, int j, Figura[][] tabla);
}
