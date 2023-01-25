package restserverspotreba.mericcena;

import restserverspotreba.config.BaseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import evidencespotrebyclasses.mericcena.MericCenaDB;
import evidencespotrebyclasses.mericcena.MericCenaDBList;

/**
 *
 * @author Milos
 */
public class MericCenaDAO extends BaseDAO{
        
    public MericCenaDBList getAll(int aIdMeric) throws SQLException{
                
        List<MericCenaDB> zaznamy = new ArrayList<MericCenaDB>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select * "+
                                          " from TBLMERICCENA "+
                                          " where id_meric = ? "+
                                          " order by datumOd ");                
                ps.setInt(1, aIdMeric);
                rs = ps.executeQuery();
                while (rs.next()){
                    MericCenaDB zaznam = new MericCenaDB();
                    zaznam.setIdMericCena(rs.getInt("id_MericCena"));
                    zaznam.setIdMeric(rs.getInt("id_Meric"));
                    zaznam.setDatumOd((Date) rs.getObject("datumOd"));
                    zaznam.setCenaZaJednotku(rs.getDouble("cena"));
                    zaznamy.add(zaznam);
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
        MericCenaDBList result = new MericCenaDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public MericCenaDB updateInDB(MericCenaDB aZaznam) throws SQLException{
               
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            ps = con.prepareStatement(" update tblMericCena " +
                        " set id_Meric = ?, datumOd = ?, cena = ? "+
                        " where id_MericCena = ? ");
            ps.setInt(1, aZaznam.getIdMeric());
            ps.setDate(2, new java.sql.Date(aZaznam.getDatumOd()));
            ps.setDouble(3, aZaznam.getCenaZaJednotku());
            // where
            ps.setInt(4, aZaznam.getIdMericCena());
            ps.execute();
            return aZaznam;
        } finally {
            try {
                if (ps != null) ps.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public MericCenaDB insertInDB(MericCenaDB aZaznam) throws SQLException{
        
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            
            ps = con.prepareStatement(" insert into TBLMERICCENA " +
                                      " (ID_MERICCENA, ID_MERIC, DATUMOD, CENA) "+
                                      " values( null, ?, ?, ? ) ");
            ps.setInt(1, aZaznam.getIdMeric());
            ps.setDate(2, new java.sql.Date(aZaznam.getDatumOd()));
            ps.setDouble(3, aZaznam.getCenaZaJednotku());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int id = rs.getInt(1);
            aZaznam.setIdMericCena(id);
            return aZaznam;
        } finally {
            try {
                if (ps != null) ps.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean smazInDB(int aIdMericCena) throws SQLException{
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {                
                ps = con.prepareStatement(" delete from TBLMERICCENA " +
                                          " where ID_MERICCENA = ? ");
                // where
                ps.setInt(1, aIdMericCena);
                ps.execute();
                return true;
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
