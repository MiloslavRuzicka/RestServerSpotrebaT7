package restserverspotreba.meric;

/**
 *
 * @author PC-Milos
 */
public class SpotrebaMesic {
    
    private int mesic;
    private int rok;
    private double spotreba;

    public SpotrebaMesic() {
    }

    public SpotrebaMesic(int mesic, int rok, double spotreba) {
        this.mesic = mesic;
        this.rok = rok;
        this.spotreba = spotreba;
    }

    public int getMesic() {
        return mesic;
    }

    public int getRok() {
        return rok;
    }

    public double getSpotreba() {
        return spotreba;
    }

    public void setMesic(int mesic) {
        this.mesic = mesic;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public void setSpotreba(double spotreba) {
        this.spotreba = spotreba;
    }
    
    public void AddSpotreba(double value){
        spotreba += value;
    }    
}
