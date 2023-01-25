package restserverspotreba.meric;

import evidencespotrebyclasses.meric.MericDB;
import evidencespotrebyclasses.mericstav.MericStavDBList;

/**
 *
 * @author PC-Milos
 */
public class MericExDB extends MericDB{
    
    private MericStavDBList stavyMerice;

    public MericExDB() {    
        stavyMerice = null;
    }

    public MericStavDBList getStavyMerice() {
        return stavyMerice;
    }

    public void setStavyMerice(MericStavDBList stavyMerice) {
        this.stavyMerice = stavyMerice;
    }
}
