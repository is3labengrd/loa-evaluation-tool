package eng.it.loatool.entities;
// Generated Sep 4, 2018 12:58:34 PM by Hibernate Tools 5.2.10.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbAceProPlan generated by hbm2java
 */
@Entity
@Table(name = "TB_ACE_PRO_PLAN", catalog = "loa_evaluation_tool")
public class TbAceProPlan implements java.io.Serializable {

    private Integer pkTbId;
    private int fkTbAceProSeq;
    private int NProdPiecePerHours;
    private Date createDate;
    private Date updateDate;

    public TbAceProPlan() {
    }

    public TbAceProPlan(int fkTbAceProSeq, int NProdPiecePerHours, Date createDate, Date updateDate) {
        this.fkTbAceProSeq = fkTbAceProSeq;
        this.NProdPiecePerHours = NProdPiecePerHours;
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

    @Column(name = "N_PROD_PIECE_PER_HOURS", nullable = false)
    public int getNProdPiecePerHours() {
        return this.NProdPiecePerHours;
    }

    public void setNProdPiecePerHours(int NProdPiecePerHours) {
        this.NProdPiecePerHours = NProdPiecePerHours;
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