package storage.DTO;

import storage.DTO.BaseDTO;

import java.util.Date;

/**
 * Created by nebular on 09.01.2017.
 */
public class Record extends BaseDTO {
    private String name;
    private Integer size;
    private Double price;
    private Date date;

    public Record()
    {
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate()
    {
       return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
