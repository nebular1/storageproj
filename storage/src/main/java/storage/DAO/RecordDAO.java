package storage.DAO;

import storage.DTO.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by nebular on 09.01.2017.
 */
public interface RecordDAO {
    List<Record> getRecords();
    List<Record> getRecordsDate(String date1, String date2, String db);
    long postRecord(Record record, String db);
    void deleteRow(Long name, Date date);
    void updateRow(Record record, String db);
}
