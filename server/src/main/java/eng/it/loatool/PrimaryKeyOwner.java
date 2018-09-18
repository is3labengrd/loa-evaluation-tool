package eng.it.loatool;


public interface PrimaryKeyOwner<T> {

    T getPrimaryKey();
    void setPrimaryKey(T t);

}
