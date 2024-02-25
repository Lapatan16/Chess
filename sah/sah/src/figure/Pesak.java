package figure;

public class Pesak extends Figura
{
	private boolean anPasan;
	public Pesak(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		anPasan = false;
		// TODO Auto-generated constructor stub
	}


	public Pesak(Figura figura) 
	{
		super(figura);
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean pomeranje(int i, int j, Figura[][] tabla )
	{
		if(tabla[i][j].boja == boja)
			return false;
		if(this.i + this.boja == i && this.j == j && tabla[i][j].oznaka == 0)
			return true;
		if(this.i + this.boja == i && this.j - 1 == j && tabla[i][j].oznaka != 0 && tabla[i][j].oznaka != this.boja )
			return true;
		if(this.i + this.boja == i && this.j + 1 == j && tabla[i][j].oznaka != 0 && tabla[i][j].oznaka != this.boja )
			return true;
		if(this.boja == 1 && this.i == 1 && this.i + this.boja * 2 == i && this.j == j && tabla[i][j].oznaka == 0)
			return true;
		if(this.boja == -1 && this.i == 6 && this.i + this.boja * 2 == i && this.j == j && tabla[i][j].oznaka == 0)
			return true;
		return false;
	}
	
	public boolean enPasant(int i, int j, Figura[][] tabla)
	{
		if(this.i + this.boja == i && this.j - 1 == j && tabla[i][j].oznaka == 0 && anPasan )
			return true;
		if(this.i + this.boja == i && this.j + 1 == j && tabla[i][j].oznaka == 0 && anPasan )
			return true;
		return false;
	}


	public void setAnPasan(boolean anPasan) {
		this.anPasan = anPasan;
	}


	public boolean isAnPasan() {
		return anPasan;
	}
	
}
