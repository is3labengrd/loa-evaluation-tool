package eng.it.loatool.minimal_satisfaction;
// Generated Dec 3, 2018 11:44:22 AM by Hibernate Tools 5.2.11.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import eng.it.loatool.PkTbPrimaryKeyOwner;

/**
 * TbAceMinSat generated by hbm2java
 */
@Entity
@Table(name = "TB_ACE_MIN_SAT", catalog = "loa_evaluation_tool")
public class MinimalSatisfaction implements java.io.Serializable, PkTbPrimaryKeyOwner {

    @JsonProperty private Integer pkTbId;
    private int fkTbAceSubProLev;
    private int fkTbAceProSeq;
    private int minTotalSat;
    private Date createDate;
    private Date updateDate;

    public MinimalSatisfaction() {}

    public MinimalSatisfaction(int fkTbAceProSeq, int minTotalSat, Date createDate, Date updateDate) {
        this.fkTbAceProSeq = fkTbAceProSeq;
        this.minTotalSat = minTotalSat;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    @Override
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "PK_TB_ID", nullable = false)
    public Integer getPkTbId() {
        return this.pkTbId;
    }

    @Override
    public void setPkTbId(Integer pkTbId) {
        this.pkTbId = pkTbId;
    }

    @Column(name = "FK_TB_ACE_SUB_PRO_LEV", nullable = false)
    public int getFkTbAceSubProLev() {
        return fkTbAceSubProLev;
    }

    public void setFkTbAceSubProLev(int fkTbAceSubProLev) {
        this.fkTbAceSubProLev = fkTbAceSubProLev;
    }

    @Column(name = "FK_TB_ACE_PRO_SEQ", nullable = false)
    public int getFkTbAceProSeq() {
        return this.fkTbAceProSeq;
    }

    public void setFkTbAceProSeq(int fkTbAceProSeq) {
        this.fkTbAceProSeq = fkTbAceProSeq;
    }

    @Column(name = "MIN_TOTAL_SAT", nullable = false)
    public int getMinTotalSat() {
        return this.minTotalSat;
    }

    public void setMinTotalSat(int minTotalSat) {
        this.minTotalSat = minTotalSat;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", updatable = false, nullable = false, length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE", nullable = false, length = 19)
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
