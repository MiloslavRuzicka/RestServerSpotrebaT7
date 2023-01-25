package restserverspotreba.meric;

import restserverspotreba.config.BaseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restserverspotreba.mericstav.MericStavDAO;

/**
 *
 * @author PC-Milos
 */
public class MericExDAO extends BaseDAO{
    
    public MericExDBList getAll(int aidOdberneMisto) throws SQLException{

        List<MericExDB> zaznamy = new ArrayList<MericExDB>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select m.* "+
                    " from TBLMERIC m "+
                    " join TBLKOMODITA k on k.id_komodita = m.id_komodita "+
                    " where m.id_odberneMisto = ?"+
                    " order by k.nazev ");
                ps.setInt(1, aidOdberneMisto);
                rs = ps.executeQuery();
                while (rs.next()){
                    MericExDB obj = new MericExDB();
                    obj.setId_meric(rs.getInt("id_Meric"));
                    obj.setPopis(rs.getString("popis"));
                    obj.setId_odberneMisto(rs.getInt("id_odberneMisto"));
                    obj.setId_komodita(rs.getInt("id_komodita"));
                    if (rs.getObject("barva") == null)
                        obj.setBarva(0);
                    else
                        obj.setBarva(rs.getInt("barva"));
                    
                    MericStavDAO msd = new MericStavDAO();
                    obj.setStavyMerice(msd.getAll(obj.getId_meric()));
                    
                    zaznamy.add(obj);
                }
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        MericExDBList result = new MericExDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public MericExDB get(int aidOdberneMisto, int aidKomodita) throws SQLException{

        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select m.* "+
                    " from TBLMERIC m "+
                    " join TBLKOMODITA k on k.id_komodita = m.id_komodita "+
                    " where m.id_odberneMisto = ? and k.id_komodita = ?"+
                    " order by k.nazev ");
                ps.setInt(1, aidOdberneMisto);
                ps.setInt(2, aidKomodita);
                rs = ps.executeQuery();
                if (rs.next()){
                    MericExDB obj = new MericExDB();
                    obj.setId_meric(rs.getInt("id_Meric"));
                    obj.setPopis(rs.getString("popis"));
                    obj.setId_odberneMisto(rs.getInt("id_odberneMisto"));
                    obj.setId_komodita(rs.getInt("id_komodita"));
                    if (rs.getObject("barva") == null)
                        obj.setBarva(0);
                    else
                        obj.setBarva(rs.getInt("barva"));
                                        
                    MericStavDAO msd = new MericStavDAO();
                    obj.setStavyMerice(msd.getAll(obj.getId_meric()));
                    
                    return obj;
                }
                return null;
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
