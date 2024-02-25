package engine;

import figure.Figura;
import figure.Kralj;
import figure.Kraljica;
import figure.Lovac;
import figure.Pesak;
import figure.PraznoPolje;
import figure.Skakac;
import figure.Top;

public class Igra 
{
	private Figura[][] tabla;
	private int naPotezu;
	private Figura aktivna;
	private boolean izabrana;
	private Figura beliKralj;
	private Figura crniKralj;
	private Figura pesak;
	
	private int prosloI;
	private int novoI;
	private int prosloJ;
	private int novoJ;
	
	public Igra()
	{
		tabla = new Figura[8][8];
		inicijalizuj();
	}
	
	public void inicijalizuj()
	{
		prosloI = prosloJ = novoI = novoJ = -1;
		naPotezu = -1;
		aktivna = null;
		izabrana = false;
		int i,j;
		pesak = null;
		for(i = 1; i < 8; i += 5)
		{
			for(j = 0; j < 8; j++)
			{
				if(i == 1)
					tabla[i][j] = new Pesak(i, j, 1, 'P', "blackPAWN");
				else
					tabla[i][j] = new Pesak(i, j, -1, 'P', "whitePAWN");
			}
		}
		
		tabla[0][0] = new Top(0, 0, 1, 'T', "blackROOK");
		tabla[0][7] = new Top(0, 7, 1, 'T', "blackROOK");
		tabla[7][7] = new Top(7, 7, -1, 'T', "whiteROOK");
		tabla[7][0] = new Top(7, 0, -1, 'T', "whiteROOK");
		
		tabla[0][1] = new Skakac(0, 1, 1, 'S', "blackKNIGHT");
		tabla[0][6] = new Skakac(0, 6, 1, 'S', "blackKNIGHT");
		tabla[7][1] = new Skakac(7, 1, -1, 'S', "whiteKNIGHT");
		tabla[7][6] = new Skakac(7, 6, -1, 'S', "whiteKNIGHT");
		
		tabla[0][2] = new Lovac(0, 2, 1, 'L', "blackBISHOP");
		tabla[0][5] = new Lovac(0, 5, 1, 'L', "blackBISHOP");
		tabla[7][2] = new Lovac(7, 2, -1, 'L', "whiteBISHOP");
		tabla[7][5] = new Lovac(7, 5, -1, 'L', "whiteBISHOP");
		
		tabla[0][3] = new Kraljica(0, 3, 1, 'Q', "blackQUEEN");
		tabla[7][3] = new Kraljica(7, 3, -1, 'Q', "whiteQUEEN");
		
		tabla[0][4] = new Kralj(0, 4, 1, 'K', "blackKING");
		tabla[7][4] = new Kralj(7, 4, -1, 'K', "whiteKING");
		beliKralj = tabla[7][4];
		crniKralj = tabla[0][4];
		
		for(i = 2; i < 6; i++)
		{
			for(j = 0; j < 8; j++)
			{
				tabla[i][j] = new PraznoPolje(i, j, 0, 0, "");
			}
		}
		
	}
	
	public boolean isMoguce(int k, int l, Figura[][] tabla)
	{
		int i,j;
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(tabla[i][j].getBoja() != naPotezu && tabla[i][j].pomeranje(k, l, tabla))
					return false;
			}
		}
		return true;
	}
	
	public Figura isSah()
	{
		if(naPotezu == 1)
		{
			int t1 = crniKralj.getI();
			int t2 = crniKralj.getJ();
			if(!isMoguce(t1,t2,tabla))
				return crniKralj;
		}
		else
		{
			int t1 = beliKralj.getI();
			int t2 = beliKralj.getJ();
			if(!isMoguce(t1,t2,tabla))
				return beliKralj;
		}
			return null;
	}
	
	public boolean isPromocija(int i, int j)
	{
		if(aktivna instanceof Pesak)
		{
			if(tabla[aktivna.getI()][aktivna.getJ()].pomeranje(i, j, tabla))
			{
				if(naPotezu == -1)
				{
					if(i == 0)
						return true;
				}
				else
				{
					if(i == 7)
						return true;
				}
			}
		}
		return false;
	}
	
	public void setujPesake(Figura[][] tabla)
	{
		int i,j;
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(tabla[i][j] instanceof Pesak)
				{
					((Pesak)tabla[i][j]).setAnPasan(false);
				}
			}
		}
	}
	
	public boolean pomeri(int i, int j, Figura[][] tabla, int naPotezu, Figure figura, boolean anPasan)
	{
		Figura tren = tabla[i][j];
		tabla[i][j] = aktivna;
		Figura pom = null;
		if(anPasan)
		{
			if(naPotezu == -1)
			{
				pom = tabla[i+1][j];
				tabla[i+1][j] = new PraznoPolje(i, j, 0, 0, "");
			}
			else
			{
				pom = tabla[i-1][j];
				tabla[i-1][j] = new PraznoPolje(i, j, 0, 0, "");
			}
		}
		tabla[aktivna.getI()][aktivna.getJ()] = new PraznoPolje(i, j, 0, 0, "");
		
		if(naPotezu == 1)
		{
			if(figura == Figure.KRALJICA)
				tabla[i][j] = new Kraljica(0, 3, 1, 'Q', "blackQUEEN");
			else if(figura == Figure.TOP)
				tabla[i][j] = new Top(0, 0, 1, 'T', "blackROOK");
			else if(figura == Figure.LOVAC)
				tabla[i][j] = new Lovac(0, 2, 1, 'L', "blackBISHOP");
			else if(figura == Figure.SKAKAC)
				tabla[i][j] = new Skakac(0, 1, 1, 'S', "blackKNIGHT");
			
			int t1,t2;
			if(aktivna instanceof Kralj)
			{
				t1 = i;
				t2 = j;
			}
			else
			{
				t1 = crniKralj.getI();
				t2 = crniKralj.getJ();
			}
			
			if(isMoguce(t1, t2, tabla))
			{
				if(tabla == this.tabla)
				{
					novoI = i;
					novoJ = j;
					prosloI = aktivna.getI();
					prosloJ = aktivna.getJ();
				}
				if(tabla[i][j] instanceof Top)
					((Top)tabla[i][j]).setPomeren(true);
				else if(tabla[i][j] instanceof Kralj && ((Kralj)tabla[i][j]).isPomeren() == false)
				{
					Figura top = null;
					if(t1 == 0 && t2 == 2)
					{
						top = tabla[t1][0];
					}
					else if(t1 == 0 && t2 == 6)
					{
						top = tabla[t1][7];
					}
					else if(t1 == 7 && t2 == 2)
					{
						top = tabla[t1][0];
					}
					else if(t1 == 7 && t2 == 6)
					{
						top = tabla[t1][7];
					}
					if(top != null)
					{
						int l;
						if(top.getJ() < 3)
							l = 3;
						else
							l = 5;
						tabla[i][l] = top;
						tabla[top.getI()][top.getJ()] = new PraznoPolje(i, top.getJ(), 0, 0, "");
						tabla[i][l].setI(i);
						tabla[i][l].setJ(l);
						if(tabla[i][l] instanceof Top)
							((Top)(tabla[i][l])).setPomeren(true);
					}
					((Kralj)tabla[i][j]).setPomeren(true);
				}
				else if(tabla[i][j] instanceof Pesak)
				{
					if(aktivna.getI() + 2 == i)
					{
						int k = i;
						int l = aktivna.getJ();
						if(l - 1 >= 0 && tabla[k][l-1] instanceof Pesak && ((Pesak)tabla[k][l-1]).getBoja() != naPotezu)
						{
							pesak = tabla[i][j];
							((Pesak)tabla[k][l-1]).setAnPasan(true);
						}
						if(l + 1 < 8 && tabla[k][l+1] instanceof Pesak && ((Pesak)tabla[k][l+1]).getBoja() != naPotezu)
						{
							pesak = tabla[i][j];
							((Pesak)tabla[k][l+1]).setAnPasan(true);
						}
					}
				}
				naPotezu = -1;
				return true;
			}	
			else
			{
				tabla[i][j] = tren;
				if(anPasan)
					tabla[i+1][j] = pom;
					
				tabla[aktivna.getI()][aktivna.getJ()] = aktivna;
			}
		}
		else
		{
			if(figura == Figure.KRALJICA)
				tabla[i][j] = new Kraljica(0, 3, -1, 'Q', "whiteQUEEN");
			else if(figura == Figure.TOP)
				tabla[i][j] = new Top(0, 0, -1, 'T', "whiteROOK");
			else if(figura == Figure.LOVAC)
				tabla[i][j] = new Lovac(0, 2, -1, 'L', "whiteBISHOP");
			else if(figura == Figure.SKAKAC)
				tabla[i][j] = new Skakac(0, 1, -1, 'S', "whiteKNIGHT");
			
			int t1,t2;
			if(aktivna instanceof Kralj)
			{
				t1 = i;
				t2 = j;
			}
			else
			{
				t1 = beliKralj.getI();
				t2 = beliKralj.getJ();
			}
			
			if(isMoguce(t1, t2, tabla))
			{
				if(tabla == this.tabla)
				{
					novoI = i;
					novoJ = j;
					prosloI = aktivna.getI();
					prosloJ = aktivna.getJ();
				}
				if(tabla[i][j] instanceof Top)
					((Top)tabla[i][j]).setPomeren(true);
				else if(tabla[i][j] instanceof Kralj && ((Kralj)tabla[i][j]).isPomeren() == false)
				{
					Figura top = null;
					if(t1 == 0 && t2 == 2)
					{
						top = tabla[t1][0];
					}
					else if(t1 == 0 && t2 == 6)
					{
						top = tabla[0][7];
					}
					else if(t1 == 7 && t2 == 2)
					{
						top = tabla[t1][0];
					}
					else if(t1 == 7 && t2 == 6)
					{
						top = tabla[t1][7];
					}
					if(top != null)
					{
						int l;
						if(top.getJ() < 3)
							l = 3;
						else
							l = 5;
						tabla[i][l] = top;
						tabla[top.getI()][top.getJ()] = new PraznoPolje(i, top.getJ(), 0, 0, "");
						tabla[i][l].setI(i);
						tabla[i][l].setJ(l);
						if(tabla[i][l] instanceof Top)
							((Top)(tabla[i][l])).setPomeren(true);
					}
					((Kralj)tabla[i][j]).setPomeren(true);
				}
				else if(tabla[i][j] instanceof Pesak)
				{
					if(aktivna.getI() - 2 == i)
					{
						int k = i;
						int l = aktivna.getJ();
						if(l - 1 >= 0 && tabla[k][l-1] instanceof Pesak && ((Pesak)tabla[k][l-1]).getBoja() != naPotezu)
						{
							pesak = tabla[i][j];
							((Pesak)tabla[k][l-1]).setAnPasan(true);
						}
						if(l + 1 < 8 && tabla[k][l+1] instanceof Pesak && ((Pesak)tabla[k][l+1]).getBoja() != naPotezu)
						{
							pesak = tabla[i][j];
							((Pesak)tabla[k][l+1]).setAnPasan(true);
						}
					}
				}
				naPotezu = 1;
				return true;
			}
			else
			{
				tabla[i][j] = tren;
				if(anPasan)
					tabla[i+1][j] = pom;

				tabla[aktivna.getI()][aktivna.getJ()] = aktivna;
			}
		}
		return false;
	}
	
	public boolean odigraj(int i, int j, Figure figura)
	{
		if(tabla[aktivna.getI()][aktivna.getJ()].pomeranje(i, j, tabla))
		{
			setujPesake(tabla);
			this.pesak = null;
			if(pomeri(i, j, tabla, naPotezu, figura, false))
			{
				if(naPotezu == -1)
					naPotezu = 1;
				else
					naPotezu = -1;
				tabla[i][j].setI(i);
				tabla[i][j].setJ(j);
				aktivna = null;
				izabrana = false;
				return true;
			}
			
		}
		else if(aktivna instanceof Pesak)
		{
			Pesak pesak =(Pesak) tabla[aktivna.getI()][aktivna.getJ()];
			if(pesak.enPasant(i, j, tabla))
			{
				if(this.pesak.getJ() != j)
					return false;
				setujPesake(tabla);
				if(pomeri(i, j, tabla, naPotezu, figura, true))
				{
					if(naPotezu == -1)
						naPotezu = 1;
					else
						naPotezu = -1;
					tabla[i][j].setI(i);
					tabla[i][j].setJ(j);
					aktivna = null;
					izabrana = false;
					return true;
				}
			}
		}
		else if(aktivna instanceof Kralj)
		{
			Kralj kralj = (Kralj) tabla[aktivna.getI()][aktivna.getJ()];
			if(isSah() != null)
				return false;
			if(j > aktivna.getJ())
			{
				if(!isMoguce(i, j-1, tabla))
					return false;
			}
			else
			{
				if(!isMoguce(i, j+1, tabla))
					return false;
			}
			if(kralj.rokada(i, j, tabla)) 
			{
				if(pomeri(i, j, tabla, naPotezu, figura, false))
				{
					if(naPotezu == -1)
						naPotezu = 1;
					else
						naPotezu = -1;
					tabla[i][j].setI(i);
					tabla[i][j].setJ(j);
					aktivna = null;
					izabrana = false;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean uzmi(int i, int j)
	{
		if(tabla[i][j] instanceof PraznoPolje)
			return false;
		if(tabla[i][j].getBoja() == naPotezu)
		{
			int k,l;
			for(k = 0; k < 8; k++)
			{
				for(l = 0; l < 8; l++)
				{
					if(tabla[i][j].pomeranje(k, l, tabla))
					{
						aktivna = tabla[i][j];
						izabrana = true;
						return true;
					}
					else if(tabla[i][j] instanceof Pesak)
					{
						if(((Pesak)tabla[i][j]).enPasant(k, l, tabla))
						{
							aktivna = tabla[i][j];
							izabrana = true;
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void kopiraj(Figura[][] pom)
	{
		int i,j;
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(tabla[i][j] instanceof Pesak)
					pom[i][j] = new Pesak(tabla[i][j]);
				else if(tabla[i][j] instanceof Kralj)
				{
					pom[i][j] = new Kralj(tabla[i][j]);
					if(((Kralj)tabla[i][j]).isPomeren())
						((Kralj)pom[i][j]).setPomeren(true);
				}
				else if(tabla[i][j] instanceof Kraljica)
					pom[i][j] = new Kraljica(tabla[i][j]);
				else if(tabla[i][j] instanceof Lovac)
					pom[i][j] = new Lovac(tabla[i][j]);
				else if(tabla[i][j] instanceof PraznoPolje)
					pom[i][j] = new PraznoPolje(tabla[i][j]);
				else if(tabla[i][j] instanceof Skakac)
					pom[i][j] = new Skakac(tabla[i][j]);
				else if(tabla[i][j] instanceof Top)
				{
					pom[i][j] = new Top(tabla[i][j]);
					if(((Top)tabla[i][j]).isPomeren())
						((Top)pom[i][j]).setPomeren(true);
				}
			}
							
		}
	}
	
	public int kraj()
	{
		int i,j,l,k;
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				for(k = 0; k < 8; k++)
				{
					for(l = 0; l < 8; l++)
					{
						if(tabla[i][j].getBoja() == naPotezu && tabla[i][j].pomeranje(k, l, tabla))
						{
							Figura[][] pom = new Figura[8][8];
							kopiraj(pom);
							aktivna = pom[i][j];
							if(pomeri(k, l, pom, naPotezu, Figure.PRAZNO_POLJE, false))
							{
								aktivna = null;
								return 0;
							}
						}
					}
				}	
			}					
		}
		if(naPotezu == 1)
			if(!isMoguce(crniKralj.getI(), crniKralj.getJ(), tabla))
			{
				aktivna = null;
				return 1;
			}
		if(naPotezu == -1)
			if(!isMoguce(beliKralj.getI(), beliKralj.getJ(), tabla))
			{
				aktivna = null;
				return -1;
			}
		
		return 2;
	}
	
	public void ostavi()
	{
		aktivna = null;
		izabrana = false;
	}
	
	public String getIme(int i, int j)
	{
		return tabla[i][j].getSlika();
	}

	public boolean isIzabrana() {
		return izabrana;
	}

	public void setIzabrana(boolean izabrana) {
		this.izabrana = izabrana;
	}

	public Figura getAktivna() {
		return aktivna;
	}

	public void setAktivna(Figura aktivna) {
		this.aktivna = aktivna;
	}

	public int getNaPotezu() {
		return naPotezu;
	}

	public int getProsloI() {
		return prosloI;
	}

	public int getNovoI() {
		return novoI;
	}

	public int getProsloJ() {
		return prosloJ;
	}

	public int getNovoJ() {
		return novoJ;
	}

}
