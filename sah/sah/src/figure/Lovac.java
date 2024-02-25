package figure;

public class Lovac extends Figura {

	public Lovac(int i, int j, int boja, int oznaka, String slika) {
		super(i, j, boja, oznaka, slika);
		// TODO Auto-generated constructor stub
	}

	public Lovac(Figura figura) 
	{
		super(figura);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean pomeranje(int i, int j, Figura[][] tabla)
	{
		if(this.i == i && this.j == j)
			return false;
		if(tabla[i][j].boja == boja)
			return false;
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
					int k;
					int l = this.j+1;
					for(k = pocetak; k > kraj && l < j; k--, l++)
						if(tabla[k][l].oznaka != 0)
							return false;
				}
				else
				{
					int pocetak = i+1;
					int kraj = this.i;
					int k;
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
					int k;
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
					int k;
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
