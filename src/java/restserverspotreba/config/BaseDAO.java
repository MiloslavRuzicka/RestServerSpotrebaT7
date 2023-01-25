package restserverspotreba.config;

import javax.sql.DataSource;

/**
 *
 * @author PC-Milos
 */
public class BaseDAO {
    
    protected final DataSource ds;
    
    public BaseDAO() {
        ds = Config.getDataSource();
    }
}
