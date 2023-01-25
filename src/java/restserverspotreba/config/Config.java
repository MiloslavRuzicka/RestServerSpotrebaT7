package restserverspotreba.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author PC-Milos
 */
public class Config {
    
    private static final String DB_NAME = "java:/comp/env/jdbc/SpotrebaDB";
    
    public static DataSource getDataSource(){
        try {
            InitialContext ctx = null;
            ctx = new InitialContext();
            return (DataSource) ctx.lookup(DB_NAME);
        } catch (NamingException ex) {
            return null;
        }
    }
}
