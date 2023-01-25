package restserverspotreba.meric;

import evidencespotrebyclasses.mericstav.MericStavDB;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import restserverspotreba.mericstav.MericStavDAO;

/**
 *
 * @author PC-Milos
 */
@XmlRootElement
public class SpotrebaMesicList {

    private List<SpotrebaMesic> list;

    public SpotrebaMesicList() {        
    }    

    public List<SpotrebaMesic> getList() {
        return list;
    }

    public void setList(List<SpotrebaMesic> list) {
        this.list = list;
    }
    
    private double spotrebaNaDen(MericStavDB stav1, MericStavDB stav2){
        return (double) (stav2.getStav()-stav1.getStav()) / DAYS.between(stav1.getDatumAsLocalDate(), stav2.getDatumAsLocalDate());
    }
    
    public void load(int aIdMeric) throws SQLException{
        MericStavDAO msd = new MericStavDAO();
        List<MericStavDB> stavy = msd.getAll(aIdMeric).getList();
        
        LocalDate datum = stavy.get(0).getDatumAsLocalDate();
        SpotrebaMesic sm = new SpotrebaMesic();
        list = new ArrayList<>();
        list.add(sm);
        sm.setRok(datum.getYear());
        sm.setMesic(datum.getMonthValue());
        for (int i= 0; i+1< stavy.size(); i++){
            while(!datum.isAfter(stavy.get(i+1).getDatumAsLocalDate())){
                sm.AddSpotreba(spotrebaNaDen(stavy.get(i), stavy.get(i+1)));
                datum = datum.plusDays(1);
                if (datum.getDayOfMonth() == 1){
                    sm = new SpotrebaMesic();
                    list.add(sm);
                    sm.setRok(datum.getYear());
                    sm.setMesic(datum.getMonthValue());
                }
            }
        }
//        System.out.println(aIdMeric);
//        for (int i= 0; i< list.size(); i++){
//            System.out.print("rok: "+ String.valueOf(list.get(i).getRok()));
//            System.out.print(", mesic: "+ String.valueOf(list.get(i).getMesic()));
//            System.out.print(", spotreba: "+ String.valueOf(list.get(i).getSpotreba()));
//            System.out.println();
//        }
    }
}
