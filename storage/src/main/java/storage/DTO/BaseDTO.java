package storage.DTO;

import java.io.Serializable;

/**
 * Created by nebular on 01.01.2017.
 */
public class BaseDTO implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
