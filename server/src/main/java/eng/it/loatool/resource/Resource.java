package eng.it.loatool.resource;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ACE_RES", catalog = "loa_evaluation_tool")
public class Resource implements java.io.Serializable {

    private TbAceResId id;
    private int fkTbAceSubProLev;
    private String name;
    private int loaPhysical;
    private int loaCognitive;
    private double lcNOperMachine;
    private double mcAMaintCosts;
    private float mcAMaintCostsPerc;
    private String rcInstSurface;
    private double rcCostsMMonth;
    private double idMacPurhValue;
    private double idMacSalesValue;
    private int idEcoUsefullLife;
    private float icInterRate;
    private int ecAEleConsumFun;
    private int ecAEleConsumSb;
    private Date createDate;
    private double ecElePrice;
    private Date udpateDate;

    public Resource() {
    }

    public Resource(
        TbAceResId id, int fkTbAceSubProLev, String name, int loaPhysical, int loaCognitive,
        double lcNOperMachine, double mcAMaintCosts, float mcAMaintCostsPerc, String rcInstSurface,
        double rcCostsMMonth, double idMacPurhValue, double idMacSalesValue, int idEcoUsefullLife,
        float icInterRate, int ecAEleConsumFun, int ecAEleConsumSb, Date createDate, double ecElePrice,
        Date udpateDate
    ) {
        this.id = id;
        this.fkTbAceSubProLev = fkTbAceSubProLev;
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
        this.createDate = createDate;
        this.ecElePrice = ecElePrice;
        this.udpateDate = udpateDate;
    }

    @EmbeddedId

    @AttributeOverrides({ @AttributeOverride(name = "pkTbId", column = @Column(name = "PK_TB_ID", nullable = false)),
        @AttributeOverride(name = "tbAceSubScenResPkTbId", column = @Column(name = "TB_ACE_SUB_SCEN_RES_PK_TB_ID", nullable = false)) })
    public TbAceResId getId() {
        return this.id;
    }

    public void setId(TbAceResId id) {
        this.id = id;
    }

    @Column(name = "FK_TB_ACE_SUB_PRO_LEV", nullable = false)
    public int getFkTbAceSubProLev() {
        return this.fkTbAceSubProLev;
    }

    public void setFkTbAceSubProLev(int fkTbAceSubProLev) {
        this.fkTbAceSubProLev = fkTbAceSubProLev;
    }

    @Column(name = "NAME", nullable = false, length = 500)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "LOA_PHYSICAL", nullable = false)
    public int getLoaPhysical() {
        return this.loaPhysical;
    }

    public void setLoaPhysical(int loaPhysical) {
        this.loaPhysical = loaPhysical;
    }

    @Column(name = "LOA_COGNITIVE", nullable = false)
    public int getLoaCognitive() {
        return this.loaCognitive;
    }

    public void setLoaCognitive(int loaCognitive) {
        this.loaCognitive = loaCognitive;
    }

    @Column(name = "LC_N_OPER_MACHINE", nullable = false, precision = 22, scale = 0)
    public double getLcNOperMachine() {
        return this.lcNOperMachine;
    }

    public void setLcNOperMachine(double lcNOperMachine) {
        this.lcNOperMachine = lcNOperMachine;
    }

    @Column(name = "MC_A_MAINT_COSTS", nullable = false, precision = 22, scale = 0)
    public double getMcAMaintCosts() {
        return this.mcAMaintCosts;
    }

    public void setMcAMaintCosts(double mcAMaintCosts) {
        this.mcAMaintCosts = mcAMaintCosts;
    }

    @Column(name = "MC_A_MAINT_COSTS_PERC", nullable = false, precision = 12, scale = 0)
    public float getMcAMaintCostsPerc() {
        return this.mcAMaintCostsPerc;
    }

    public void setMcAMaintCostsPerc(float mcAMaintCostsPerc) {
        this.mcAMaintCostsPerc = mcAMaintCostsPerc;
    }

    @Column(name = "RC_INST_SURFACE", nullable = false, length = 45)
    public String getRcInstSurface() {
        return this.rcInstSurface;
    }

    public void setRcInstSurface(String rcInstSurface) {
        this.rcInstSurface = rcInstSurface;
    }

    @Column(name = "RC_COSTS_M_MONTH", nullable = false, precision = 22, scale = 0)
    public double getRcCostsMMonth() {
        return this.rcCostsMMonth;
    }

    public void setRcCostsMMonth(double rcCostsMMonth) {
        this.rcCostsMMonth = rcCostsMMonth;
    }

    @Column(name = "ID_MAC_PURH_VALUE", nullable = false, precision = 22, scale = 0)
    public double getIdMacPurhValue() {
        return this.idMacPurhValue;
    }

    public void setIdMacPurhValue(double idMacPurhValue) {
        this.idMacPurhValue = idMacPurhValue;
    }

    @Column(name = "ID_MAC_SALES_VALUE", nullable = false, precision = 22, scale = 0)
    public double getIdMacSalesValue() {
        return this.idMacSalesValue;
    }

    public void setIdMacSalesValue(double idMacSalesValue) {
        this.idMacSalesValue = idMacSalesValue;
    }

    @Column(name = "ID_ECO_USEFULL_LIFE", nullable = false)
    public int getIdEcoUsefullLife() {
        return this.idEcoUsefullLife;
    }

    public void setIdEcoUsefullLife(int idEcoUsefullLife) {
        this.idEcoUsefullLife = idEcoUsefullLife;
    }

    @Column(name = "IC_INTER_RATE", nullable = false, precision = 12, scale = 0)
    public float getIcInterRate() {
        return this.icInterRate;
    }

    public void setIcInterRate(float icInterRate) {
        this.icInterRate = icInterRate;
    }

    @Column(name = "EC_A_ELE_CONSUM_FUN", nullable = false)
    public int getEcAEleConsumFun() {
        return this.ecAEleConsumFun;
    }

    public void setEcAEleConsumFun(int ecAEleConsumFun) {
        this.ecAEleConsumFun = ecAEleConsumFun;
    }

    @Column(name = "EC_A_ELE_CONSUM_SB", nullable = false)
    public int getEcAEleConsumSb() {
        return this.ecAEleConsumSb;
    }

    public void setEcAEleConsumSb(int ecAEleConsumSb) {
        this.ecAEleConsumSb = ecAEleConsumSb;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false, length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "EC_ELE_PRICE", nullable = false, precision = 22, scale = 0)
    public double getEcElePrice() {
        return this.ecElePrice;
    }

    public void setEcElePrice(double ecElePrice) {
        this.ecElePrice = ecElePrice;
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
