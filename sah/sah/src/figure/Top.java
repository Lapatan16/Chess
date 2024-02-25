package figure;

public class Top extends Figura 
{
	private boolean pomeren;
	
	public Top(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		pomeren = false;
		// TODO Auto-generated constructor stub
	}

	public Top(Figura figura) 
	{
		super(figura);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean pomeranje(int i, int j, Figura[][] tabla) 
	{
		int k;
		if(this.i == i && this.j == j)
			return false;
		if(tabla[i][j].boja == boja)
			return false;
		if(this.i == i)
		{
			int ink;
			if(this.j > j)
				ink = -1;
			else
				ink = 1;
			k = this.j + ink;
			while(k != j)
			{
				if(tabla[i][k].oznaka != 0)
					return false;
				k += ink;
			}
			if(tabla[i][j].oznaka == boja)
				return false;
			return true;
		}
		if(this.j == j)
		{
			int ink;
			if(this.i > i)
				ink = -1;
			else
				ink = 1;
			k = this.i + ink;
			while(k != i)
			{
				if(tabla[k][j].oznaka != 0)
					return false;
				k += ink;
			}
			if(tabla[i][j].oznaka == boja)
				return false;
			return true;
		}
		return false;
	}

	public boolean isPomeren() {
		return pomeren;
	}

	public void setPomeren(boolean pomeren) {
		this.pomeren = pomeren;
	}

}
