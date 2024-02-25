package figure;

public class Kraljica extends Figura {

	public Kraljica(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		// TODO Auto-generated constructor stub
	}

	public Kraljica(Figura figura) 
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
			return true;
		}
		if(Math.abs(this.i - i) == Math.abs(this.j - j))
		{
			if(tabla[i][j].oznaka == boja)
				return false;
			if(this.i > i)
			{
				if(this.j < j)
				{
					int pocetak = this.i-1;
					int kraj = i;
					int l = this.j+1;
					for(k = pocetak; k > kraj && l < j; k--, l++)
						if(tabla[k][l].oznaka != 0)
							return false;
				}
				else
				{
					int pocetak = i+1;
					int kraj = this.i;
					int l = j+1;
					for(k = pocetak; k < kraj && l < this.j; k++, l++)
					{
						if(tabla[k][l].oznaka != 0)
							return false;
					}
				}
			}
			else
			{
				if(this.j < j)
				{
					int pocetak = this.i+1;
					int kraj = i;
					int l = this.j + 1;
					for(k = pocetak; k < kraj && l < j; k++, l++)
					{
						if(tabla[k][l].oznaka != 0)
							return false;
					}
				}
				else
				{
					int pocetak = i - 1;
					int kraj = this.i;
					int l = j+1;
					for(k = pocetak; k > kraj && l < this.j; k--, l++)
						if(tabla[k][l].oznaka != 0)
							return false;
				}
			}
			return true;
		}
		return false;
	}

}
