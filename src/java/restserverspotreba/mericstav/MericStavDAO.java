package restserverspotreba.mericstav;

import restserverspotreba.config.BaseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import evidencespotrebyclasses.mericstav.MericStavDB;
import evidencespotrebyclasses.mericstav.MericStavDBList;
import java.sql.Statement;

/**
 *
 * @author PC-Milos
 */
public class MericStavDAO extends BaseDAO{
    
    public MericStavDBList getAll(int aidMeric) throws SQLException{
                
        List<MericStavDB> zaznamy = new ArrayList<MericStavDB>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select * "+
                        " from TBLSTAVMERICE "+
                        " where id_Meric = ?"+
                        " order by Datum, pocatek ");
                ps.setInt(1, aidMeric);
                rs = ps.executeQuery();
                while (rs.next()){
                    MericStavDB obj = new MericStavDB();
                    obj.setId_stavMerice(rs.getInt("id_stavMerice"));
                    obj.setDatum(rs.getDate("datum").getTime());
                    obj.setStav(rs.getInt("stav"));
                    obj.setId_meric(rs.getInt("id_meric"));
                    obj.setPocatecniHodnota(rs.getBoolean("pocatek"));
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
        MericStavDBList result = new MericStavDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public MericStavDB updateInDB(MericStavDB aZaznam) throws SQLException{
                
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            
            ps = con.prepareStatement(" update TBLSTAVMERICE " +
                        " set datum = ?, stav = ?, pocatek = ? "+
                        " where id_StavMerice = ? ");
            ps.setDate(1, new java.sql.Date(aZaznam.getDatum()));
            ps.setInt(2, aZaznam.getStav());
            if (aZaznam.isPocatecniHodnota())
                ps.setInt(3, 1);
            else
                ps.setInt(3, 0);
             // where
            ps.setInt(4, aZaznam.getId_stavMerice());
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

    public MericStavDB insertInDB(MericStavDB aZaznam) throws SQLException{
        
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            ps = con.prepareStatement(" insert into TBLSTAVMERICE " +
                        " (Datum, Stav, pocatek, id_meric) "+
                        " values(?, ?, ?, ? ) ", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(aZaznam.getDatum()));
            ps.setInt(2, aZaznam.getStav());
            if (aZaznam.isPocatecniHodnota())
                ps.setInt(3, 1);
            else
                ps.setInt(3, 0);
            ps.setInt(4, aZaznam.getId_meric());
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null){
                if (rs.next()){
                    int id = rs.getInt(1);
                    aZaznam.setId_stavMerice(id);
                }
                rs.close();
            }
            return aZaznam;
        } finally {
            try {
                if (ps != null) ps.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean smazInDB(int aId_stavMerice) throws SQLException{
                
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" delete from TBLSTAVMERICE " +
                                          " where id_StavMerice = ? ");
                // where
                ps.setInt(1, aId_stavMerice);
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
