package figure;

public class Skakac extends Figura {

	public Skakac(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		// TODO Auto-generated constructor stub
	}

	public Skakac(Figura figura) 
	{
		super(figura);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean pomeranje(int i, int j, Figura[][] tabla) 
	{
		if(tabla[i][j].boja == boja)
			return false;
		if(this.i + 2 == i)
		{
			if(this.j + 1 == j)
				if(tabla[i][j].oznaka != boja)
					return true;
			if(this.j - 1 == j)
				if(tabla[i][j].oznaka != boja)
					return true;
		}
		else if(this.i - 2 == i)
		{
			if(this.j + 1 == j)
				if(tabla[i][j].oznaka != boja)
					return true;
			if(this.j - 1 == j)
				if(tabla[i][j].oznaka != boja)
					return true;
		}
		else if(this.j + 2 == j)
		{
			if(this.i + 1 == i)
				if(tabla[i][j].oznaka != boja)
					return true;
			if(this.i - 1 == i)
				if(tabla[i][j].oznaka != boja)
					return true;
		}
		else if(this.j - 2 == j)
		{
			if(this.i + 1 == i)
				if(tabla[i][j].oznaka != boja)
					return true;
			if(this.i - 1 == i)
				if(tabla[i][j].oznaka != boja)
					return true;
		}
		return false;
	}

}
