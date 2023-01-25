package restserverspotreba.meric;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author PC-Milos
 */
@Path("MericEx")
public class MericExRes {
    
    private MericExDAO dao = new MericExDAO();    
    
    @GET
    @Path("{idOdberneMisto}")
    @Produces({ MediaType.APPLICATION_JSON})
    public MericExDBList getAll(@PathParam("idOdberneMisto") String idOdberneMisto){
        try {
            return dao.getAll(Integer.valueOf(idOdberneMisto));
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @GET
    @Path("{idOdberneMisto}/{idKomodita}")
    @Produces({ MediaType.APPLICATION_JSON})
    public MericExDB getAll(@PathParam("idOdberneMisto") String idOdberneMisto,
                            @PathParam("idKomodita") String idKomodita){
        try {
            return dao.get(Integer.valueOf(idOdberneMisto),
                           Integer.valueOf(idKomodita));
        } catch (SQLException ex) {
            return null;
        }
    }
}
