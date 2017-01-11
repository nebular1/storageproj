package storage.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import storage.DTO.Record;

import java.util.Date;
import java.util.List;

@Service("recordDAO")
public class RecordDAOImpl extends BaseDAO implements RecordDAO {
    private static final RowMapper<Record> recordRowMapper = (rs, i) -> {
        Record record = new Record();

        record.setId(rs.getLong("id"));
        record.setName(rs.getString("product_name"));
        record.setSize(rs.getInt("product_size"));
        record.setPrice(rs.getDouble("product_price"));
        record.setDate(rs.getDate("product_date"));

        return record;
    };

    @Override
    @Transactional(readOnly = true)
    public List<Record> getRecords() {
        return jdbcTemplate.query("select * from products_v", recordRowMapper);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Record> getRecordsDate(String date1, String date2, String db) {
        return jdbcTemplate.query("select * from " + db + " where product_date between '" + date1 + "' and '" + date2 + "'", recordRowMapper);
    }

    @Override
    @Transactional
    public long postRecord(Record record, String db) {
       /* if (!isNew(record)) {
            throw new UnsupportedOperationException("Обновление данных не реализовано");
        }*/
        return jdbcTemplate.insertReturnId(
                "insert into " + db + "(product_name, product_size, product_price, product_date) values(?, ?, ?, ?)",
                record.getName(),
                record.getSize(),
                record.getPrice(),
                record.getDate()
        );
    }
    @Override
    @Transactional
    public void deleteRow(Long name, Date date) {
        jdbcTemplate.update("insert into outcome select * from products where id = '" + name +"'");
        jdbcTemplate.update("update outcome set product_date = '"+ date +"' where id = '" + name +"'");
        jdbcTemplate.update("delete from products where id = '" + name +"'");
    }
    @Override
    @Transactional
    public void updateRow(Record record, String db) {
        jdbcTemplate.update("update " + db + " set product_name = '"+ record.getName() +"', " +
                "product_size = '"+ record.getSize() +"', " +
                "product_price = '"+ record.getPrice() +"', " +
                "product_date = '"+ record.getDate() +"'" +
                "where id = '" + record.getId() +"'");
    }

}