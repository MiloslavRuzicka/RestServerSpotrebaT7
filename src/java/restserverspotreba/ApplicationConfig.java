package restserverspotreba;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Milos
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(restserverspotreba.komodita.KomoditaRes.class);
        resources.add(restserverspotreba.meric.MericExRes.class);        
        resources.add(restserverspotreba.meric.MericRes.class);
        resources.add(restserverspotreba.mericcena.MericCenaRes.class);
        resources.add(restserverspotreba.mericstav.MericStavRes.class);
        resources.add(restserverspotreba.odbernemisto.OdberneMistoRes.class);
        resources.add(restserverspotreba.udalost.UdalostRes.class);
    }
    
}
