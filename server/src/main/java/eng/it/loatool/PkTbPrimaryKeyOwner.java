package eng.it.loatool;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface PkTbPrimaryKeyOwner extends PrimaryKeyOwner<Integer> {

    default Integer getPrimaryKey() {
        return this.getPkTbId();
    }

    default void setPrimaryKey(Integer primaryKey) {
        this.setPkTbId(primaryKey);
    }

    @JsonIgnore public Integer getPkTbId();
    public void setPkTbId(Integer pkTbId);

}
