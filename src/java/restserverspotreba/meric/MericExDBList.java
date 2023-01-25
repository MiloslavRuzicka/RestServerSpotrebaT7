package restserverspotreba.meric;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC-Milos
 */
@XmlRootElement
public class MericExDBList {
    
    private List<MericExDB> list;

    public MericExDBList() {        
    }    

    public List<MericExDB> getList() {
        return list;
    }

    public void setList(List<MericExDB> list) {
        this.list = list;
    }
}