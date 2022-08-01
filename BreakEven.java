
package breakeven.breakeven;
/**
 *
 * @author giorgia
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

public class BreakEven implements ActionListener, ChangeListener{ 
	
	JFrame frame;//frame
	CalcolatoreGrafico panel;//pannello
	Intersezione inters;//calcolatore di breakeven
	Timer timer; 
	JButton reset;
        
        //menu
	JMenu menu;
	JMenuBar mbar;
	JMenuItem aiuto;
	JMenuItem di;
	JMenuItem calcola;
        
        //slider
	JSlider vslider;//costi variabili
	JSlider fslider;//costi fissi
	JSlider pslider;//prezzo
	JLabel costivar;
	JLabel costifis;
	JLabel prezzo;
        
        //funzioni
	JLabel cfunz;//costi
	JLabel pfunz;//profitti
        
	JLabel unita;//Unità per raggiungere il BEP
	JLabel breakeven;
	JLabel quantita;
	JLabel negativo;//BEP negativo
	JLabel senzaprofitto;//prodotto che non da profitto

	public void actionPerformed(ActionEvent evt){
		
		if(evt.getSource() == timer){ 
			panel.repaint();
		}else if(evt.getSource() == aiuto){ 
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/mezzeragiorgia/Break-Even-Point-Ptoject"));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(BreakEven.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BreakEven.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}else if(evt.getSource() == di){ 
			JOptionPane.showMessageDialog(null,"Progetto di Economia di Mezzera Giorgia (matricola 1080308)","About",JOptionPane.PLAIN_MESSAGE);  
		}else if(evt.getSource() == calcola){ 
			frame.setContentPane(panel);
			frame.pack();
			frame.repaint();
		}else if(evt.getSource() == reset){ 
			inters.reset();
			panel.reset();
			vslider.setValue(0);
			fslider.setValue(0);
			pslider.setValue(0);
			negativo.setVisible(false);
			senzaprofitto.setVisible(false);
		}
	}
	
	public void stateChanged(ChangeEvent e){ 
		if(e.getSource() == vslider){
			costivar.setText("COSTI VARIABILI € :" + vslider.getValue());
			cfunz.setText("Funzione dei costi totali:"+vslider.getValue() + "x + " + fslider.getValue());
		}else if(e.getSource() == fslider){
			costifis.setText("COSTI FISSI €:" + fslider.getValue());
			cfunz.setText("Funzione dei costi totali:"+vslider.getValue() + "x + " + fslider.getValue());
		}else if(e.getSource() == pslider){
			prezzo.setText("PREZZO UNITARIO €:" + pslider.getValue());
			pfunz.setText("Funzione dei profitti:" + pslider.getValue() + "x");
		}
                
		inters.m1 = vslider.getValue();
		inters.q1 = fslider.getValue();
		inters.m2 = pslider.getValue();
                inters.q2 = 0; //funzione dei profitti non ha termine noto
		
		//proprietà del pannello con i valori degli slider per disegnare le funzioni
		panel.m1 = vslider.getValue();
		panel.q1 = fslider.getValue();
		panel.m2 = pslider.getValue();
		
                //se le rette non sono parallele, calcola le coordinate del punto di intersezione
		if(inters.parallelo() == false){ 
			double X = inters.calcX();
			double Y = inters.calcY();
			breakeven.setText("Break Even Point:(" + X + "," + Y + ")");
			unita.setText("Unità per raggiungere il BEP: "+X);
			
			if(X < 0 || Y < 0){ //se il punto di intersezione è negativo
				negativo.setVisible(true);
				senzaprofitto.setVisible(true);
			}else{
				negativo.setVisible(false);
				senzaprofitto.setVisible(false);
			}
		}else{ //se le rette sono parallele
			breakeven.setText("Break Even Point: LE FUNZIONI SONO PARALLELE");
			unita.setText("Unità per raggiungere il BEP: IMPOSSIBILE DA CALCOLARE");
			negativo.setVisible(false);
			senzaprofitto.setVisible(false);
		}
	}
	
	//costruttore
	public BreakEven(){
		
		inters = new Intersezione(); 
		frame = new JFrame("Break Even Calculator"); 
		panel = new CalcolatoreGrafico(); 
		panel.setPreferredSize(new Dimension(960,540));
		panel.setLayout(null);
		
		quantita = new JLabel("QUANTITA'");
		quantita.setLocation(655,520);
		quantita.setSize(new Dimension(80,20));
		panel.add(quantita);
		
		costifis = new JLabel("COSTI FISSI €"); 
		costifis.setLocation(40,30);
		costifis.setSize(new Dimension(300,20));
		panel.add(costifis);
		
		fslider = new JSlider(0,500,0);
		fslider.setValue(0);
		fslider.setLocation(30,50);
		fslider.setSize(new Dimension(300,40));
		fslider.setMajorTickSpacing(100);
		fslider.setMinorTickSpacing(50);
		fslider.setPaintTicks(true);
		fslider.setPaintLabels(true);
		fslider.addChangeListener(this);
		panel.add(fslider);
		
		costivar = new JLabel("COSTI VARIABILI € :");
		costivar.setLocation(40,120);
		costivar.setSize(new Dimension(300,20));
		panel.add(costivar);
		
		vslider = new JSlider(0,500,0); 
		vslider.setValue(0);
		vslider.setLocation(30,140);
		vslider.setSize(new Dimension(300,40));
		vslider.setMajorTickSpacing(100);
		vslider.setMinorTickSpacing(50);
		vslider.setPaintTicks(true);
		vslider.setPaintLabels(true);
		vslider.addChangeListener(this);
		panel.add(vslider);
		
		prezzo = new JLabel("PREZZO UNITARIO €");
		prezzo.setLocation(40,210);
		prezzo.setSize(new Dimension(200,20));
		panel.add(prezzo);
		
		pslider = new JSlider(0,500,0); 
		pslider.setValue(0);
		pslider.setLocation(30,230);
		pslider.setSize(new Dimension(300,40));
		pslider.setMajorTickSpacing(100);
		pslider.setMinorTickSpacing(50);
		pslider.setPaintTicks(true);
		pslider.setPaintLabels(true);
		pslider.addChangeListener(this);
		panel.add(pslider);
		
		cfunz = new JLabel("Funzione dei costi totali:"); 
		cfunz.setLocation(40,300);
		cfunz.setSize(new Dimension(300,20));
		cfunz.setForeground(Color.blue);
		panel.add(cfunz);
		
		pfunz = new JLabel("Funzione dei profitti:");
		pfunz.setLocation(40,330);
		pfunz.setSize(new Dimension(300,20));
		pfunz.setForeground(Color.red);
		panel.add(pfunz);
		
		unita = new JLabel("Unità per raggiungere il BEP:");
		unita.setLocation(40,360);
		unita.setSize(new Dimension(300,20));
		panel.add(unita);
		
		breakeven = new JLabel("Break Even Point:"); 
		breakeven.setLocation(40,390);
		breakeven.setSize(new Dimension(300,20));
		panel.add(breakeven);
		
		reset = new JButton("RESET"); 
		reset.setLocation(40,430);
		reset.setSize(new Dimension(100,40));
		reset.addActionListener(this);
		panel.add(reset);
		
		negativo = new JLabel("Il punto di intersezione è negativo!"); 
		negativo.setLocation(40,480);
		negativo.setSize(new Dimension(300,20));
		negativo.setVisible(false);
		panel.add(negativo);
		
		senzaprofitto = new JLabel("Le vendite non creano profitto!");
		senzaprofitto.setLocation(40,500);
		senzaprofitto.setSize(new Dimension(300,20));
		senzaprofitto.setVisible(false);
		panel.add(senzaprofitto);
		
                
		mbar = new JMenuBar();
		menu = new JMenu("Menu");
		aiuto = new JMenuItem("Aiuto");
		di = new JMenuItem("Di");
		calcola = new JMenuItem("Calcola");

		aiuto.addActionListener(this);
		di.addActionListener(this);
		calcola.addActionListener(this);
		
		menu.add(aiuto);
		menu.add(di);
		menu.add(calcola);
		mbar.add(menu);
		frame.setJMenuBar(mbar);

		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		timer = new Timer(1000/48,this); 
		timer.start();
	}
	
	//main
	public static void main(String[] args){
		new BreakEven();
	}
}

