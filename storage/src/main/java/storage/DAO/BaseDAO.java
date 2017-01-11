package storage.DAO;

import storage.DTO.BaseDTO;
import storage.Jdbc.Jdbc_Template;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
/**
 * Created by nebular on 01.01.2017.
 */
public class BaseDAO implements Serializable {

    protected Jdbc_Template jdbcTemplate;

    @Resource
    public final void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new Jdbc_Template(dataSource);
    }

    protected <T extends BaseDTO> boolean isNew(T dto) {
        return dto.getId() == null;
    }
}
