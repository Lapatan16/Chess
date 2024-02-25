package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import engine.Figure;
import engine.Igra;
import engine.Sat;
import figure.Figura;

import javax.sound.sampled.*;
@SuppressWarnings("serial")
public class GUI extends JFrame 
{
	JPanel tabla;
	Dugme[][] dugmici;
	Igra igra;
	JLabel naPotezuLabela;
	JPanel sever;
	JPanel opcije;
	Figure figura;
	JLabel naPotezuSlika;
	JPanel istok;
	JLabel satBeli;
	JLabel satCrni;
	int vremeBeli;
	int vremeCrni;	
	private Sat sat;
	private String[] vremena = {"Rapid", "Blitz", "Bullet"};
	
	public GUI()
	{
		setIconImage((new ImageIcon("Pieces//blackPawn.png")).getImage());
		setTitle("Sah");
		setVisible(true);
		setBounds(400, 0, 400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Igrica();
	}
	
	public void Igrica()
	{
		tabla = new JPanel(new GridLayout(8,8));
		Border tablaBorder = BorderFactory.createLineBorder(Color.lightGray, 1);
		tabla.setBorder(tablaBorder);
		getContentPane().add(tabla, BorderLayout.CENTER);
		dugmici = new Dugme[8][8];
		sever = new JPanel(new FlowLayout());
		getContentPane().add(sever, BorderLayout.NORTH);
		naPotezuLabela = new JLabel();
		naPotezuSlika = new JLabel();
		sever.add(naPotezuLabela);
		sever.add(naPotezuSlika);
		igra = new Igra();
		int i,j;
		
		satCrni = new JLabel();
		satCrni.setForeground(Color.RED);
		satCrni.setBackground(Color.DARK_GRAY);
		satCrni.setOpaque(true);
		satCrni.setHorizontalTextPosition(JLabel.CENTER);
		satCrni.setVerticalTextPosition(JLabel.CENTER);
		satCrni.setFont(new Font(getName(), 0, 50));
		
		satBeli = new JLabel();
		satBeli.setForeground(Color.RED);
		satBeli.setFont(new Font(getName(), 0, 50));
		satBeli.setBackground(Color.DARK_GRAY);
		satBeli.setOpaque(true);
		satBeli.setHorizontalTextPosition(JLabel.CENTER);
		satBeli.setVerticalTextPosition(JLabel.CENTER);
		Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
		Border b = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		satBeli.setBorder(border);
		satCrni.setBorder(b);
		
		istok = new JPanel(new BorderLayout());
		JPanel panelSat = new JPanel();
		panelSat.setLayout(new BoxLayout(panelSat, BoxLayout.Y_AXIS));
		panelSat.add(Box.createVerticalGlue());
		panelSat.add(satCrni);
		panelSat.add(satBeli);
		panelSat.add(Box.createVerticalGlue());
		panelSat.setOpaque(true);
		istok.add(panelSat, BorderLayout.CENTER);
		getContentPane().add(istok, BorderLayout.EAST);
		
		ActionListener akcija = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Dugme dugme = (Dugme) e.getSource();
				if(igra.isIzabrana())
				{
					int provera;
					figura = Figure.PRAZNO_POLJE;
					if(igra.isPromocija(dugme.i, dugme.j))
					{
						
						opcije = new JPanel(new GridLayout(1,4));
						ImageIcon[] slikeZaPromociju = new ImageIcon[4];
						ImageIcon slika;
						Image image;
						if(igra.getNaPotezu() == 1)
							slika = new ImageIcon("Pieces//blackQUEEN.png");
						else
							slika = new ImageIcon("Pieces//whiteQUEEN.png");
						image = slika.getImage();
						image = image.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
						slika = new ImageIcon(image);
						slikeZaPromociju[0] = slika;
						if(igra.getNaPotezu() == 1)
							slika = new ImageIcon("Pieces//blackROOK.png");
						else
							slika = new ImageIcon("Pieces//whiteROOK.png");
						image = slika.getImage();
						image = image.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
						slika = new ImageIcon(image);
						slikeZaPromociju[1] = slika;
						if(igra.getNaPotezu() == 1)
							slika = new ImageIcon("Pieces//blackBISHOP.png");
						else
							slika = new ImageIcon("Pieces//whiteBISHOP.png");
						image = slika.getImage();
						image = image.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
						slika = new ImageIcon(image);
						slikeZaPromociju[2] = slika;
						if(igra.getNaPotezu() == 1)
							slika = new ImageIcon("Pieces//blackKNIGHT.png");
						else
							slika = new ImageIcon("Pieces//whiteKNIGHT.png");
						image = slika.getImage();
						image = image.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
						slika = new ImageIcon(image);
						slikeZaPromociju[3] = slika;
						int i;
						for(i = 0; i < 4; i++)
						{
							JButton labela = new JButton(slikeZaPromociju[i]);
							labela.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									JButton izabran = (JButton) e.getSource();
							        ImageIcon izabranaIkona = (ImageIcon) izabran.getIcon();
							        
							        if(izabranaIkona.equals(slikeZaPromociju[0])) 
							        {
							            figura = Figure.KRALJICA;
							        }
							        else if(izabranaIkona.equals(slikeZaPromociju[1])) 
							        {
							            figura = Figure.TOP;
							        } 
							        else if(izabranaIkona.equals(slikeZaPromociju[2])) 
							        {
							            figura = Figure.LOVAC;
							        } 
							        else if(izabranaIkona.equals(slikeZaPromociju[3])) 
							        {
							            figura = Figure.SKAKAC;
							        }
							        SwingUtilities.getWindowAncestor(izabran).dispose();
								}
							});
							opcije.add(labela);
						} 
						JOptionPane.showMessageDialog(dugme, opcije, "Promocija", JOptionPane.PLAIN_MESSAGE);
						if(figura == Figure.PRAZNO_POLJE)
						{
							igra.ostavi();
							osvezi();
							return;
						}
					}
					if(!igra.odigraj(dugme.i, dugme.j, figura))
					{
						igra.ostavi();
						if(!igra.uzmi(dugme.i, dugme.j))
						{
							String putanja = "Sound//illegal.wav";
							File audioFile = new File(putanja);
							playAudio(audioFile);
						}
							
					}
					else if((provera = igra.kraj()) != 0)
					{
						String putanja = "Sound//Checkmate_Win.WAV";
						File audioFile = new File(putanja);
						playAudio(audioFile);
						osvezi();
						String poruka;
						if(provera == 2)
							poruka = "Nereseno!";
						else if(provera == 1)
							poruka = "Beli je pobedio!";
						else
							poruka = "Crni je pobedio!";
						
						Kraj(poruka,dugme);
					}
					else
					{
						String putanja = "Sound//move-self.wav";
						File audioFile = new File(putanja);
						playAudio(audioFile);
						if(igra.isSah() != null)
						{
							putanja = "Sound//Check.WAV";
							audioFile = new File(putanja);
							playAudio(audioFile);
						}
					}
				}
				else
				{
					igra.uzmi(dugme.i, dugme.j);
				}
				osvezi();
			}
		};
		
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				Dugme dugme = new Dugme(i, j);
				tabla.add(dugme);
				dugmici[i][j] = dugme;
				dugme.addActionListener(akcija);
			}
		}
		osvezi();
		satCrni.setText(0 + ":0" + 0);
		satBeli.setText(0 + ":0" + 0);
		pack();
		int n = -1;
		n = JOptionPane.showOptionDialog(null, "Odaberite vreme igre!", "Pocetak igre!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, vremena, vremena[0]);
		if(n == 0)
			n = 10;
		else if(n == 1)
			n = 5;
		else if(n == 2)
			n = 1;
		else
			System.exit(1);
		vremeBeli = n * 60;
		vremeCrni = n * 60;
		if(n != 10)
		{
			satCrni.setText(vremeCrni/60 + ":0" + vremeCrni%60);
			satBeli.setText(vremeBeli/60 + ":0" + vremeBeli%60);
		}
		else
		{
			satCrni.setText(vremeCrni/60 + ":" + vremeCrni%60);
			satBeli.setText(vremeBeli/60 + ":" + vremeBeli%60);
		}
		sat = new Sat(this);
		sat.start();
		osvezi();
		pack();
	}
	
	public void osvezi()
	{
		int i,j;
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(i % 2 == 0)
				{
					if(j % 2 == 0)
						dugmici[i][j].setBackground(Color.WHITE);
					else
						dugmici[i][j].setBackground(Color.DARK_GRAY);
				}	
				else
					if(j % 2 == 0)
						dugmici[i][j].setBackground(Color.DARK_GRAY);
					else
						dugmici[i][j].setBackground(Color.WHITE);
				if(igra.getAktivna() != null)
					dugmici[igra.getAktivna().getI()][igra.getAktivna().getJ()].setBackground(new Color(235, 119, 19));
				if( igra.getIme(i, j) != "")
				{
					ImageIcon slika = new ImageIcon("Pieces//" + igra.getIme(i, j) + ".png");
					Image image = slika.getImage();
					image = image.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
					slika = new ImageIcon(image);
					dugmici[i][j].setIcon(slika);
				}
				else
					dugmici[i][j].setIcon(null);
				naPotezuLabela.setForeground(Color.RED);
				naPotezuLabela.setText("Na potezu je:");
				naPotezuLabela.setFont(new Font(getName(), 0, 15));
				if(igra.getNaPotezu() == -1)
				{
					ImageIcon slika = new ImageIcon("Pieces//whitePAWN.png");
					Image image = slika.getImage();
					image = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
					slika = new ImageIcon(image);
					naPotezuSlika.setIcon(slika);
				}
				else
				{
					ImageIcon slika = new ImageIcon("Pieces//blackPAWN.png");
					Image image = slika.getImage();
					image = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
					slika = new ImageIcon(image);
					naPotezuSlika.setIcon(slika);
					naPotezuSlika.setIcon(slika);
				}
				
				if(igra.getNovoI() == i && igra.getNovoJ() == j)
					dugmici[i][j].setBackground(new Color(255,205,116));
				if(igra.getProsloI() == i && igra.getProsloJ() == j)
					dugmici[i][j].setBackground(new Color(255,237,191));
				Figura pom;
				if((pom = igra.isSah()) != null)
				{
					if(i == pom.getI() && j == pom.getJ())
					{
						dugmici[i][j].setBackground(Color.RED);
					}
				}
			}
		}
	}
	
	private void playAudio(File putanja)
	{
	    try 
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(putanja));
	        clip.start();
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	public synchronized void Clock()
	{
		
		if(igra.getNaPotezu() == 1)
		{
			vremeCrni = vremeCrni - 1;
			if(vremeCrni%60 < 10 && vremeCrni/60 < 10)
				satCrni.setText(vremeCrni/60 + ":0" + vremeCrni%60);
			else
				satCrni.setText(vremeCrni/60 + ":" + vremeCrni%60);
			if(vremeCrni == 0)
			{
				String putanja = "Sound//Checkmate_Lose.WAV";
				File audiFile = new File(putanja);
				playAudio(audiFile);
				Kraj("Beli je pobedio!", null);
			}
		}
		else
		{
			vremeBeli = vremeBeli - 1;
			if(vremeBeli%60 < 10 && vremeBeli/60 < 10)
				satBeli.setText(vremeBeli/60 + ":0" + vremeBeli%60);
			else
				satBeli.setText(vremeBeli/60 + ":" + vremeBeli%60);
			if(vremeBeli == 0)
			{
				String putanja = "Sound//Checkmate_Lose.WAV";
				File audiFile = new File(putanja);
				playAudio(audiFile);
				Kraj("Crni je pobedio!", null);
			}
		}
	}
	
	public synchronized void Kraj(String poruka, Component dugme)
	{
		
		int panel = JOptionPane.showConfirmDialog(dugme, "Nova igra?", poruka, JOptionPane.YES_NO_OPTION);
		if(panel == JOptionPane.YES_OPTION)
		{
			igra.inicijalizuj();
			osvezi();
			int n = -1;
			n = JOptionPane.showOptionDialog(null, "Odaberite vreme igre!", "Pocetak igre!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, vremena, vremena[0]);
			if(n == 0)
				n = 10;
			else if(n == 1)
				n = 5;
			else if(n == 2)
				n = 1;
			else
				System.exit(1);
			vremeBeli = n * 60;
			vremeCrni = n * 60;
			if(n != 10)
			{
				satCrni.setText(vremeCrni/60 + ":0" + vremeCrni%60);
				satBeli.setText(vremeBeli/60 + ":0" + vremeBeli%60);
			}
			else
			{
				satCrni.setText(vremeCrni/60 + ":" + vremeCrni%60);
				satBeli.setText(vremeBeli/60 + ":" + vremeBeli%60);
			}
			osvezi();
		}
		else
			System.exit(1);
	}
	
}
