package eng.it.loatool.subscenario;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ACE_SUB_SCENARIOS", catalog = "loa_evaluation_tool")
public class SubScenario implements java.io.Serializable {

    private Integer pkTbId;
    private int fkTbAceProSeq;
    private String bestLoaRange;
    private Date createDate;
    private Date updateDate;

    public SubScenario() {
    }

    public SubScenario(int fkTbAceProSeq, String bestLoaRange, Date createDate, Date updateDate) {
        this.fkTbAceProSeq = fkTbAceProSeq;
        this.bestLoaRange = bestLoaRange;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    @Column(name = "FK_TB_ACE_PRO_SEQ", nullable = false)
    public int getFkTbAceProSeq() {
        return this.fkTbAceProSeq;
    }

    public void setFkTbAceProSeq(int fkTbAceProSeq) {
        this.fkTbAceProSeq = fkTbAceProSeq;
    }

    @Column(name = "BEST_LOA_RANGE", nullable = false, length = 45)
    public String getBestLoaRange() {
        return this.bestLoaRange;
    }

    public void setBestLoaRange(String bestLoaRange) {
        this.bestLoaRange = bestLoaRange;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false, length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE", nullable = false, length = 19)
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
