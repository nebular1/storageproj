package storage.Jdbc;

import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.PreparedStatement;

public class Jdbc_Template extends JdbcTemplate implements Serializable {
    public Jdbc_Template(DataSource dataSource) {
        super(dataSource);
    }

    public Long insertReturnId(String sql, Object... args) {
        PreparedStatementSetter preparedStatementSetter = new ArgumentPreparedStatementSetter(args);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{ "id" });
            preparedStatementSetter.setValues(ps);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
