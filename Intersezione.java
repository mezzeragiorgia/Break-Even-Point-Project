package breakeven.breakeven;
/**
 *
 * @author giorgia
 */
public class Intersezione{ 

    //coordinate del punto di intersezione
    public double X;
    public double Y;

    //coefficienti di pendenza delle rette
    public int m1 = 0;
    public int m2 = 0;

    //termini noti delle rette
    public int q1 = 0;
    public int q2 = 0;

    //calcolare la coordinata X conoscendo le due rette
    public double calcX(){
             double appoggio = (double)(q2 - q1) / (m1 - m2);
             X = Math.round(appoggio*100.0)/100.0;
             return X;
    }

    //calcolare la coordinata Y conoscendo le due rette
    public double calcY(){
            double appoggio = (double)m1*X + q1;
            Y = Math.round(appoggio*100.0)/100.0;
            return Y;
    }

    //determinare se le due rette sono parallele (hanno lo stesso coefficiente)
    public boolean parallelo(){
        return m1 == m2;
    }

    //resetta
    public void reset(){
            Y = 0;
            X = 0;
            m1 = 0;
            m2 = 0;
            q1 = 0;
            q2 = 0;
    }
	
}