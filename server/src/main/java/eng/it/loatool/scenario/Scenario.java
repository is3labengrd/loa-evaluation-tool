package eng.it.loatool.scenario;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eng.it.loatool.PkTbPrimaryKeyOwner;

@Entity
@Table(name = "TB_ACE_SCENARIOS", catalog = "loa_evaluation_tool")
public class Scenario implements java.io.Serializable, PkTbPrimaryKeyOwner {

    private Integer pkTbId;
    private int fkTbAceProSeq;
    private Date createDate;
    private Date udpateDate;

    public Scenario() {
    }

    public Scenario(int fkTbAceProSeq, Date createDate, Date udpateDate) {
        this.fkTbAceProSeq = fkTbAceProSeq;
        this.createDate = createDate;
        this.udpateDate = udpateDate;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false, length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UDPATE_DATE", nullable = false, length = 19)
    public Date getUdpateDate() {
        return this.udpateDate;
    }

    public void setUdpateDate(Date udpateDate) {
        this.udpateDate = udpateDate;
    }

}
