package figure;

public class PraznoPolje extends Figura {

	public PraznoPolje(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		// TODO Auto-generated constructor stub
	}

	public PraznoPolje(Figura figura) 
	{
		super(figura);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean pomeranje(int i, int j, Figura[][] tabla) {
		// TODO Auto-generated method stub
		return false;
	}

}
