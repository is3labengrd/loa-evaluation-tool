package eng.it.loatool.resource;
// Generated Sep 24, 2018 11:43:01 AM by Hibernate Tools 5.2.11.Final

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
 * TbAceRes generated by hbm2java
 */
@Entity @Table(name = "TB_ACE_RES", catalog = "loa_evaluation_tool")
public class Resource implements java.io.Serializable, PkTbPrimaryKeyOwner {

    @JsonProperty private Integer pkTbId;
    private String name;
    private Integer loaPhysical;
    private Integer loaCognitive;
    private Double lcNOperMachine;
    private Double mcAMaintCosts;
    private Float mcAMaintCostsPerc;
    private Double rcInstSurface;
    private Double rcCostsMMonth;
    private Double idMacPurhValue;
    private Double idMacSalesValue;
    private Integer idEcoUsefullLife;
    private Float icInterRate;
    private Integer ecAEleConsumFun;
    private Integer ecAEleConsumSb;
    private Double ecElePrice;
    private Boolean varRes;
    private Date createDate;
    private Date updateDate;

    public Resource() {}

    public Resource(String name, Integer loaPhysical, Integer loaCognitive, Double lcNOperMachine, Double mcAMaintCosts, Float mcAMaintCostsPerc, Double rcInstSurface, Double rcCostsMMonth, Double idMacPurhValue, Double idMacSalesValue, Integer idEcoUsefullLife, Float icInterRate, Integer ecAEleConsumFun, Integer ecAEleConsumSb, Double ecElePrice, Date createDate, Date updateDate) {
        this.name = name;
        this.loaPhysical = loaPhysical;
        this.loaCognitive = loaCognitive;
        this.lcNOperMachine = lcNOperMachine;
        this.mcAMaintCosts = mcAMaintCosts;
        this.mcAMaintCostsPerc = mcAMaintCostsPerc;
        this.rcInstSurface = rcInstSurface;
        this.rcCostsMMonth = rcCostsMMonth;
        this.idMacPurhValue = idMacPurhValue;
        this.idMacSalesValue = idMacSalesValue;
        this.idEcoUsefullLife = idEcoUsefullLife;
        this.icInterRate = icInterRate;
        this.ecAEleConsumFun = ecAEleConsumFun;
        this.ecAEleConsumSb = ecAEleConsumSb;
        this.ecElePrice = ecElePrice;
        this.varRes = false;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Resource(String name, Integer loaPhysical, Integer loaCognitive, Double lcNOperMachine, Double mcAMaintCosts, Float mcAMaintCostsPerc, Double rcInstSurface, Double rcCostsMMonth, Double idMacPurhValue, Double idMacSalesValue, Integer idEcoUsefullLife, Float icInterRate, Integer ecAEleConsumFun, Integer ecAEleConsumSb, Double ecElePrice, Boolean varRes, Date createDate, Date updateDate) {
        this.name = name;
        this.loaPhysical = loaPhysical;
        this.loaCognitive = loaCognitive;
        this.lcNOperMachine = lcNOperMachine;
        this.mcAMaintCosts = mcAMaintCosts;
        this.mcAMaintCostsPerc = mcAMaintCostsPerc;
        this.rcInstSurface = rcInstSurface;
        this.rcCostsMMonth = rcCostsMMonth;
        this.idMacPurhValue = idMacPurhValue;
        this.idMacSalesValue = idMacSalesValue;
        this.idEcoUsefullLife = idEcoUsefullLife;
        this.icInterRate = icInterRate;
        this.ecAEleConsumFun = ecAEleConsumFun;
        this.ecAEleConsumSb = ecAEleConsumSb;
        this.ecElePrice = ecElePrice;
        this.varRes = varRes;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    @Override
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PK_TB_ID", unique = true, nullable = false) public Integer getPkTbId() {
        return this.pkTbId;
    }

    @Override
    public void setPkTbId(Integer pkTbId) {
        this.pkTbId = pkTbId;
    }

    @Column(name = "NAME", nullable = false, length = 500) public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "LOA_PHYSICAL", nullable = false) public Integer getLoaPhysical() {
        return this.loaPhysical;
    }

    public void setLoaPhysical(Integer loaPhysical) {
        this.loaPhysical = loaPhysical;
    }

    @Column(name = "LOA_COGNITIVE", nullable = false) public Integer getLoaCognitive() {
        return this.loaCognitive;
    }

    public void setLoaCognitive(Integer loaCognitive) {
        this.loaCognitive = loaCognitive;
    }

    @Column(name = "LC_N_OPER_MACHINE", nullable = false, precision = 22, scale = 0) public Double getLcNOperMachine() {
        return this.lcNOperMachine;
    }

    public void setLcNOperMachine(Double lcNOperMachine) {
        this.lcNOperMachine = lcNOperMachine;
    }

    @Column(name = "MC_A_MAINT_COSTS", nullable = false, precision = 22, scale = 0) public Double getMcAMaintCosts() {
        return this.mcAMaintCosts;
    }

    public void setMcAMaintCosts(Double mcAMaintCosts) {
        this.mcAMaintCosts = mcAMaintCosts;
    }

    @Column(name = "MC_A_MAINT_COSTS_PERC", nullable = false, precision = 12, scale = 0) public Float getMcAMaintCostsPerc() {
        return this.mcAMaintCostsPerc;
    }

    public void setMcAMaintCostsPerc(Float mcAMaintCostsPerc) {
        this.mcAMaintCostsPerc = mcAMaintCostsPerc;
    }

    @Column(name = "RC_INST_SURFACE", nullable = false, length = 45) public Double getRcInstSurface() {
        return this.rcInstSurface;
    }

    public void setRcInstSurface(Double rcInstSurface) {
        this.rcInstSurface = rcInstSurface;
    }

    @Column(name = "RC_COSTS_M_MONTH", nullable = false, precision = 22, scale = 0) public Double getRcCostsMMonth() {
        return this.rcCostsMMonth;
    }

    public void setRcCostsMMonth(Double rcCostsMMonth) {
        this.rcCostsMMonth = rcCostsMMonth;
    }

    @Column(name = "ID_MAC_PURH_VALUE", nullable = false, precision = 22, scale = 0) public Double getIdMacPurhValue() {
        return this.idMacPurhValue;
    }

    public void setIdMacPurhValue(Double idMacPurhValue) {
        this.idMacPurhValue = idMacPurhValue;
    }

    @Column(name = "ID_MAC_SALES_VALUE", nullable = false, precision = 22, scale = 0) public Double getIdMacSalesValue() {
        return this.idMacSalesValue;
    }

    public void setIdMacSalesValue(Double idMacSalesValue) {
        this.idMacSalesValue = idMacSalesValue;
    }

    @Column(name = "ID_ECO_USEFULL_LIFE", nullable = false) public Integer getIdEcoUsefullLife() {
        return this.idEcoUsefullLife;
    }

    public void setIdEcoUsefullLife(Integer idEcoUsefullLife) {
        this.idEcoUsefullLife = idEcoUsefullLife;
    }

    @Column(name = "IC_INTER_RATE", nullable = false, precision = 12, scale = 0) public Float getIcInterRate() {
        return this.icInterRate;
    }

    public void setIcInterRate(Float icInterRate) {
        this.icInterRate = icInterRate;
    }

    @Column(name = "EC_A_ELE_CONSUM_FUN", nullable = false) public Integer getEcAEleConsumFun() {
        return this.ecAEleConsumFun;
    }

    public void setEcAEleConsumFun(Integer ecAEleConsumFun) {
        this.ecAEleConsumFun = ecAEleConsumFun;
    }

    @Column(name = "EC_A_ELE_CONSUM_SB", nullable = false) public Integer getEcAEleConsumSb() {
        return this.ecAEleConsumSb;
    }

    public void setEcAEleConsumSb(Integer ecAEleConsumSb) {
        this.ecAEleConsumSb = ecAEleConsumSb;
    }

    @Column(name = "EC_ELE_PRICE", nullable = false, precision = 22, scale = 0) public Double getEcElePrice() {
        return this.ecElePrice;
    }

    public void setEcElePrice(Double ecElePrice) {
        this.ecElePrice = ecElePrice;
    }

    @Column(name = "VAR_RES", nullable = false) public Boolean getVarRes() {
        return varRes;
    }

    public void setVarRes(Boolean varRes) {
        this.varRes = varRes;
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
