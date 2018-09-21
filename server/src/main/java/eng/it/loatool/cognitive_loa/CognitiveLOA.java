package eng.it.loatool.cognitive_loa;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ACE_COG_LOA", catalog = "loa_evaluation_tool")
public class CognitiveLOA implements java.io.Serializable {

    private Integer pkTbId;
    private int code;
    private String loa;

    public CognitiveLOA() {}

    public CognitiveLOA(int code, String loa) {
        this.code = code;
        this.loa = loa;
    }

    @Id @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PK_TB_ID", unique = true, nullable = false) public Integer getPkTbId() {
        return this.pkTbId;
    }

    public void setPkTbId(Integer pkTbId) {
        this.pkTbId = pkTbId;
    }

    @Column(name = "CODE", nullable = false) public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Column(name = "LOA", nullable = false, length = 500) public String getLoa() {
        return this.loa;
    }

    public void setLoa(String loa) {
        this.loa = loa;
    }

}
