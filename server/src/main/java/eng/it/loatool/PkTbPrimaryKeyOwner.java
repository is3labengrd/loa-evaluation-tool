package eng.it.loatool;


public interface PkTbPrimaryKeyOwner extends PrimaryKeyOwner<Integer> {

    default Integer getPrimaryKey() {
        return this.getPkTbId();
    }

    default void setPrimaryKey(Integer primaryKey) {
        this.setPkTbId(primaryKey);
    }

    public Integer getPkTbId();
    public void setPkTbId(Integer pkTbId);

}
