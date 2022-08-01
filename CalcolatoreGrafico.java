package breakeven.breakeven;
/**
 *
 * @author giorgia
 */
import java.awt.*;
import javax.swing.*;

public class CalcolatoreGrafico extends JPanel{ 
	
    //punti e coefficienti
    int p1;
    int p2;
    int p3;
    int p4;
    int m1;
    int m2;
    int q1;

    //Methods
    @Override
    public void paintComponent(Graphics g){ 
            super.paintComponent(g);

            //disegno assi cartesiani
            g.drawLine(425,520,925,520);
            g.drawLine(425,0,425,520);
            
            //disegno le righe di scala
            for(int i = 0; i <= 500; i+=100){
                    g.drawLine(i+425,0,i+425,520);
                    g.drawLine(425,520-i,925,520-i);
                    g.drawString(""+i,425+i,535);
                    if(i !=0 ){ //per non scrivere 2 volte lo 0
                            g.drawString(""+i,400,520-i);
                    }
                    for(int j = 20; j <= 500; j+=20){
                            g.drawLine(j+425,0,j+425,520);
                            g.drawLine(425,520-j,925,520-j);
                    }
            }
            //punti per disegnare le funzioni
            p1 = q1;
            p2 = m1*500+q1;
            p3 = 0;
            p4 = m2*500;

            //draw cost function
            g.setColor(Color.blue);
            g.drawLine(425,520-p1,925,520-p2);
            //draw revenue function
            g.setColor(Color.red);
            g.drawLine(425,520-p3,925,520-p4);
    }

    public void reset(){ 
            p1 = 0;
            p2 = 0;
            p3 = 0;
            p4 = 0;
            m1 = 0;
            m2 = 0;
            q1 = 0;
    }

    //Constructor
    public CalcolatoreGrafico(){
            super();
    }
}
