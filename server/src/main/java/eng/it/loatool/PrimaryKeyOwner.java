package eng.it.loatool;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface PrimaryKeyOwner<T> {

    @JsonIgnore T getPrimaryKey();
    void setPrimaryKey(T t);

}
