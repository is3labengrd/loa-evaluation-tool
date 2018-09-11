package eng.it.loatool.entities;
// Generated Sep 4, 2018 12:58:34 PM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbAcePhyLoa generated by hbm2java
 */
@Entity
@Table(name = "TB_ACE_PHY_LOA", catalog = "loa_evaluation_tool")
public class PhysicalLOA implements java.io.Serializable {

    private Integer pkTbId;
    private int code;
    private String loa;

    public PhysicalLOA() {
    }

    public PhysicalLOA(int code, String loa) {
        this.code = code;
        this.loa = loa;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PK_TB_ID", unique = true, nullable = false)
    public Integer getPkTbId() {
        return this.pkTbId;
    }

    public void setPkTbId(Integer pkTbId) {
        this.pkTbId = pkTbId;
    }

    @Column(name = "CODE", nullable = false)
    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Column(name = "LOA", nullable = false, length = 500)
    public String getLoa() {
        return this.loa;
    }

    public void setLoa(String loa) {
        this.loa = loa;
    }

}