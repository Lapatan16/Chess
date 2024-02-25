package figure;

public class Kralj extends Figura 
{
	private boolean pomeren;
	
	public Kralj(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		pomeren = false;
		// TODO Auto-generated constructor stub
	}
	
	public Kralj(Figura figura) 
	{
		super(figura);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean pomeranje(int i, int j, Figura[][] tabla) 
	{
		if(tabla[i][j].boja == boja)
			return false;
		if(this.i == i)
		{
			if(this.j + 1 == j)
				return true;
			if(this.j - 1 == j)
				return true;
		}
		if(this.j == j)
		{
			if(this.i + 1 == i)
				return true;
			if(this.i - 1 == i)
				return true;
		}
		if(this.i + 1 == i)
		{
			if(this.j + 1 == j)
				return true;
			if(this.j - 1 == j)
				return true;
		}
		if(this.i - 1 == i)
		{
			if(this.j + 1 == j)
				return true;
			if(this.j - 1 == j)
				return true;
		}
		return false;
	}
	
	public boolean rokada(int i, int j, Figura[][] tabla)
	{
		if(pomeren)
			return false;
		if(this.boja == 1)
		{
			if(this.i == i && j == this.j + 2 && j == 6)
			{
				if(tabla[i][j].oznaka == 0 && tabla[i][this.j + 1].oznaka == 0 && tabla[i][7].oznaka == 'T' && ((Top)tabla[i][7]).isPomeren() == false)
					return true;
			}
			else if(this.i == i && j == this.j - 2 && j == 2)
			{
				if(tabla[i][j].oznaka == 0 && tabla[i][this.j - 1].oznaka == 0 && tabla[i][0].oznaka == 'T' && ((Top)tabla[i][0]).isPomeren() == false)
					return true;
			}
		}
		else
		{
			if(this.i == i && j == this.j + 2 && j == 6)
			{
				if(tabla[i][j].oznaka == 0 && tabla[i][this.j + 1].oznaka == 0 && tabla[i][7].oznaka == 'T' && ((Top)tabla[i][7]).isPomeren() == false)
					return true;
			}
			else if(this.i == i && j == this.j - 2 && j == 2)
			{
				if(tabla[i][j].oznaka == 0 && tabla[i][this.j - 1].oznaka == 0 && tabla[i][0].oznaka == 'T' && ((Top)tabla[i][0]).isPomeren() == false)
					return true;
			}
		}
		return false;
	}

	public void setPomeren(boolean pomeren) {
		this.pomeren = pomeren;
	}

	public boolean isPomeren() {
		return pomeren;
	}

}
