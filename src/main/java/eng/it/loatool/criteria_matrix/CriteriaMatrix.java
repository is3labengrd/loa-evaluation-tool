package eng.it.loatool.criteria_matrix;

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
@Table(name = "TB_ACE_LOA_CRI_MAT", catalog = "loa_evaluation_tool")
public class CriteriaMatrix implements java.io.Serializable {

    private Integer pkTbId;
    private int dsStable;
    private Integer dsReducedStability;
    private Integer dsHandlyStable;
    private Integer dsUnstable;
    private Integer stInsensitive;
    private Integer stHardlySensitive;
    private Integer stSensitive;
    private Integer stVerySensitive;
    private Integer grExGripSurface;
    private Integer grIntGripSurface;
    private Integer grMagneticGripper;
    private Integer grFabClosureGripper;
    private Integer nvNoFurtherVariants;
    private Integer nvOneFurtherVariants;
    private Integer nvTwoFurtherVariants;
    private Integer nvMoreFurtherVariants;
    private Integer spUp;
    private Integer spMore;
    private Integer spStableUnstable;
    private Integer spOnlyUnstable;
    private Integer smRotSymmetrical;
    private Integer smArSymmetry;
    private Integer smMaAsymmetrical;
    private Integer smSeSymmetrical;
    private Integer haNone;
    private Integer haStiJamPossible;
    private Integer haComPenPossibile;
    private Integer haSeeSymmetrical;
    private Integer fjNever;
    private Integer fjOcasionally;
    private Integer fjRarely;
    private Integer fjOften;
    private Integer acVeryGood;
    private Integer acGood;
    private Integer acSatisfactory;
    private Integer acSufficient;
    private Integer orNoAxes;
    private Integer orOneAxis;
    private Integer orTwoAxes;
    private Integer orThreeAxes;
    private Integer jmLinear;
    private Integer jmRotation;
    private Integer jmLinearRotatory;
    private Integer jmPathMovement;
    private Integer jfNone;
    private Integer jfLow;
    private Integer jfMedium;
    private Integer jfHigh;
    private Integer jaJoinBasicComp;
    private Integer jaJoinComp;
    private Integer jaBasicComp;
    private Integer jaMoreComp;
    private Integer ncOneBasicComp;
    private Integer ncTwoBasicComp;
    private Integer ncThreeBasicComp;
    private Integer ncMoreComp;
    private Integer jsSecAllDirection;
    private Integer jsGravityFit;
    private Integer jsGravityRubbing;
    private Integer jsAdditionalSec;
    private Integer soNone;
    private Integer soOne;
    private Integer soTwo;
    private Integer soMore;
    private int ecAEleConsumFun;
    private Date createDate;
    private Date updateDate;
    private int fkTbAceSubProLev;

    public CriteriaMatrix() {
    }

    public CriteriaMatrix(int dsStable, int ecAEleConsumFun, Date createDate, Date updateDate, int fkTbAceSubProLev) {
        this.dsStable = dsStable;
        this.ecAEleConsumFun = ecAEleConsumFun;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.fkTbAceSubProLev = fkTbAceSubProLev;
    }

    public CriteriaMatrix(
        int dsStable, Integer dsReducedStability, Integer dsHandlyStable, Integer dsUnstable,
        Integer stInsensitive, Integer stHardlySensitive, Integer stSensitive, Integer stVerySensitive,
        Integer grExGripSurface, Integer grIntGripSurface, Integer grMagneticGripper, Integer grFabClosureGripper,
        Integer nvNoFurtherVariants, Integer nvOneFurtherVariants, Integer nvTwoFurtherVariants,
        Integer nvMoreFurtherVariants, Integer spUp, Integer spMore, Integer spStableUnstable,
        Integer spOnlyUnstable, Integer smRotSymmetrical, Integer smArSymmetry, Integer smMaAsymmetrical,
        Integer smSeSymmetrical, Integer haNone, Integer haStiJamPossible, Integer haComPenPossibile,
        Integer haSeeSymmetrical, Integer fjNever, Integer fjOcasionally, Integer fjRarely, Integer fjOften,
        Integer acVeryGood, Integer acGood, Integer acSatisfactory, Integer acSufficient, Integer orNoAxes,
        Integer orOneAxis, Integer orTwoAxes, Integer orThreeAxes, Integer jmLinear, Integer jmRotation,
        Integer jmLinearRotatory, Integer jmPathMovement, Integer jfNone, Integer jfLow, Integer jfMedium,
        Integer jfHigh, Integer jaJoinBasicComp, Integer jaJoinComp, Integer jaBasicComp, Integer jaMoreComp,
        Integer ncOneBasicComp, Integer ncTwoBasicComp, Integer ncThreeBasicComp, Integer ncMoreComp,
        Integer jsSecAllDirection, Integer jsGravityFit, Integer jsGravityRubbing, Integer jsAdditionalSec,
        Integer soNone, Integer soOne, Integer soTwo, Integer soMore, int ecAEleConsumFun, Date createDate,
        Date updateDate, int fkTbAceSubProLev
    ) {
        this.dsStable = dsStable;
        this.dsReducedStability = dsReducedStability;
        this.dsHandlyStable = dsHandlyStable;
        this.dsUnstable = dsUnstable;
        this.stInsensitive = stInsensitive;
        this.stHardlySensitive = stHardlySensitive;
        this.stSensitive = stSensitive;
        this.stVerySensitive = stVerySensitive;
        this.grExGripSurface = grExGripSurface;
        this.grIntGripSurface = grIntGripSurface;
        this.grMagneticGripper = grMagneticGripper;
        this.grFabClosureGripper = grFabClosureGripper;
        this.nvNoFurtherVariants = nvNoFurtherVariants;
        this.nvOneFurtherVariants = nvOneFurtherVariants;
        this.nvTwoFurtherVariants = nvTwoFurtherVariants;
        this.nvMoreFurtherVariants = nvMoreFurtherVariants;
        this.spUp = spUp;
        this.spMore = spMore;
        this.spStableUnstable = spStableUnstable;
        this.spOnlyUnstable = spOnlyUnstable;
        this.smRotSymmetrical = smRotSymmetrical;
        this.smArSymmetry = smArSymmetry;
        this.smMaAsymmetrical = smMaAsymmetrical;
        this.smSeSymmetrical = smSeSymmetrical;
        this.haNone = haNone;
        this.haStiJamPossible = haStiJamPossible;
        this.haComPenPossibile = haComPenPossibile;
        this.haSeeSymmetrical = haSeeSymmetrical;
        this.fjNever = fjNever;
        this.fjOcasionally = fjOcasionally;
        this.fjRarely = fjRarely;
        this.fjOften = fjOften;
        this.acVeryGood = acVeryGood;
        this.acGood = acGood;
        this.acSatisfactory = acSatisfactory;
        this.acSufficient = acSufficient;
        this.orNoAxes = orNoAxes;
        this.orOneAxis = orOneAxis;
        this.orTwoAxes = orTwoAxes;
        this.orThreeAxes = orThreeAxes;
        this.jmLinear = jmLinear;
        this.jmRotation = jmRotation;
        this.jmLinearRotatory = jmLinearRotatory;
        this.jmPathMovement = jmPathMovement;
        this.jfNone = jfNone;
        this.jfLow = jfLow;
        this.jfMedium = jfMedium;
        this.jfHigh = jfHigh;
        this.jaJoinBasicComp = jaJoinBasicComp;
        this.jaJoinComp = jaJoinComp;
        this.jaBasicComp = jaBasicComp;
        this.jaMoreComp = jaMoreComp;
        this.ncOneBasicComp = ncOneBasicComp;
        this.ncTwoBasicComp = ncTwoBasicComp;
        this.ncThreeBasicComp = ncThreeBasicComp;
        this.ncMoreComp = ncMoreComp;
        this.jsSecAllDirection = jsSecAllDirection;
        this.jsGravityFit = jsGravityFit;
        this.jsGravityRubbing = jsGravityRubbing;
        this.jsAdditionalSec = jsAdditionalSec;
        this.soNone = soNone;
        this.soOne = soOne;
        this.soTwo = soTwo;
        this.soMore = soMore;
        this.ecAEleConsumFun = ecAEleConsumFun;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.fkTbAceSubProLev = fkTbAceSubProLev;
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

    @Column(name = "DS_STABLE", nullable = false)
    public int getDsStable() {
        return this.dsStable;
    }

    public void setDsStable(int dsStable) {
        this.dsStable = dsStable;
    }

    @Column(name = "DS_REDUCED_STABILITY")
    public Integer getDsReducedStability() {
        return this.dsReducedStability;
    }

    public void setDsReducedStability(Integer dsReducedStability) {
        this.dsReducedStability = dsReducedStability;
    }

    @Column(name = "DS_HANDLY_STABLE")
    public Integer getDsHandlyStable() {
        return this.dsHandlyStable;
    }

    public void setDsHandlyStable(Integer dsHandlyStable) {
        this.dsHandlyStable = dsHandlyStable;
    }

    @Column(name = "DS_UNSTABLE")
    public Integer getDsUnstable() {
        return this.dsUnstable;
    }

    public void setDsUnstable(Integer dsUnstable) {
        this.dsUnstable = dsUnstable;
    }

    @Column(name = "ST_INSENSITIVE")
    public Integer getStInsensitive() {
        return this.stInsensitive;
    }

    public void setStInsensitive(Integer stInsensitive) {
        this.stInsensitive = stInsensitive;
    }

    @Column(name = "ST_HARDLY_SENSITIVE")
    public Integer getStHardlySensitive() {
        return this.stHardlySensitive;
    }

    public void setStHardlySensitive(Integer stHardlySensitive) {
        this.stHardlySensitive = stHardlySensitive;
    }

    @Column(name = "ST_SENSITIVE")
    public Integer getStSensitive() {
        return this.stSensitive;
    }

    public void setStSensitive(Integer stSensitive) {
        this.stSensitive = stSensitive;
    }

    @Column(name = "ST_VERY_SENSITIVE")
    public Integer getStVerySensitive() {
        return this.stVerySensitive;
    }

    public void setStVerySensitive(Integer stVerySensitive) {
        this.stVerySensitive = stVerySensitive;
    }

    @Column(name = "GR_EX_GRIP_SURFACE")
    public Integer getGrExGripSurface() {
        return this.grExGripSurface;
    }

    public void setGrExGripSurface(Integer grExGripSurface) {
        this.grExGripSurface = grExGripSurface;
    }

    @Column(name = "GR_INT_GRIP_SURFACE")
    public Integer getGrIntGripSurface() {
        return this.grIntGripSurface;
    }

    public void setGrIntGripSurface(Integer grIntGripSurface) {
        this.grIntGripSurface = grIntGripSurface;
    }

    @Column(name = "GR_MAGNETIC_GRIPPER")
    public Integer getGrMagneticGripper() {
        return this.grMagneticGripper;
    }

    public void setGrMagneticGripper(Integer grMagneticGripper) {
        this.grMagneticGripper = grMagneticGripper;
    }

    @Column(name = "GR_FAB_CLOSURE_GRIPPER")
    public Integer getGrFabClosureGripper() {
        return this.grFabClosureGripper;
    }

    public void setGrFabClosureGripper(Integer grFabClosureGripper) {
        this.grFabClosureGripper = grFabClosureGripper;
    }

    @Column(name = "NV_NO_FURTHER_VARIANTS")
    public Integer getNvNoFurtherVariants() {
        return this.nvNoFurtherVariants;
    }

    public void setNvNoFurtherVariants(Integer nvNoFurtherVariants) {
        this.nvNoFurtherVariants = nvNoFurtherVariants;
    }

    @Column(name = "NV_ONE_FURTHER_VARIANTS")
    public Integer getNvOneFurtherVariants() {
        return this.nvOneFurtherVariants;
    }

    public void setNvOneFurtherVariants(Integer nvOneFurtherVariants) {
        this.nvOneFurtherVariants = nvOneFurtherVariants;
    }

    @Column(name = "NV_TWO_FURTHER_VARIANTS")
    public Integer getNvTwoFurtherVariants() {
        return this.nvTwoFurtherVariants;
    }

    public void setNvTwoFurtherVariants(Integer nvTwoFurtherVariants) {
        this.nvTwoFurtherVariants = nvTwoFurtherVariants;
    }

    @Column(name = "NV_MORE_FURTHER_VARIANTS")
    public Integer getNvMoreFurtherVariants() {
        return this.nvMoreFurtherVariants;
    }

    public void setNvMoreFurtherVariants(Integer nvMoreFurtherVariants) {
        this.nvMoreFurtherVariants = nvMoreFurtherVariants;
    }

    @Column(name = "SP_UP")
    public Integer getSpUp() {
        return this.spUp;
    }

    public void setSpUp(Integer spUp) {
        this.spUp = spUp;
    }

    @Column(name = "SP_MORE")
    public Integer getSpMore() {
        return this.spMore;
    }

    public void setSpMore(Integer spMore) {
        this.spMore = spMore;
    }

    @Column(name = "SP_STABLE_UNSTABLE")
    public Integer getSpStableUnstable() {
        return this.spStableUnstable;
    }

    public void setSpStableUnstable(Integer spStableUnstable) {
        this.spStableUnstable = spStableUnstable;
    }

    @Column(name = "SP_ONLY_UNSTABLE")
    public Integer getSpOnlyUnstable() {
        return this.spOnlyUnstable;
    }

    public void setSpOnlyUnstable(Integer spOnlyUnstable) {
        this.spOnlyUnstable = spOnlyUnstable;
    }

    @Column(name = "SM_ROT_SYMMETRICAL")
    public Integer getSmRotSymmetrical() {
        return this.smRotSymmetrical;
    }

    public void setSmRotSymmetrical(Integer smRotSymmetrical) {
        this.smRotSymmetrical = smRotSymmetrical;
    }

    @Column(name = "SM_AR_SYMMETRY")
    public Integer getSmArSymmetry() {
        return this.smArSymmetry;
    }

    public void setSmArSymmetry(Integer smArSymmetry) {
        this.smArSymmetry = smArSymmetry;
    }

    @Column(name = "SM_MA_ASYMMETRICAL")
    public Integer getSmMaAsymmetrical() {
        return this.smMaAsymmetrical;
    }

    public void setSmMaAsymmetrical(Integer smMaAsymmetrical) {
        this.smMaAsymmetrical = smMaAsymmetrical;
    }

    @Column(name = "SM_SE_SYMMETRICAL")
    public Integer getSmSeSymmetrical() {
        return this.smSeSymmetrical;
    }

    public void setSmSeSymmetrical(Integer smSeSymmetrical) {
        this.smSeSymmetrical = smSeSymmetrical;
    }

    @Column(name = "HA_NONE")
    public Integer getHaNone() {
        return this.haNone;
    }

    public void setHaNone(Integer haNone) {
        this.haNone = haNone;
    }

    @Column(name = "HA_STI_JAM_POSSIBLE")
    public Integer getHaStiJamPossible() {
        return this.haStiJamPossible;
    }

    public void setHaStiJamPossible(Integer haStiJamPossible) {
        this.haStiJamPossible = haStiJamPossible;
    }

    @Column(name = "HA_COM_PEN_POSSIBILE")
    public Integer getHaComPenPossibile() {
        return this.haComPenPossibile;
    }

    public void setHaComPenPossibile(Integer haComPenPossibile) {
        this.haComPenPossibile = haComPenPossibile;
    }

    @Column(name = "HA_SEE_SYMMETRICAL")
    public Integer getHaSeeSymmetrical() {
        return this.haSeeSymmetrical;
    }

    public void setHaSeeSymmetrical(Integer haSeeSymmetrical) {
        this.haSeeSymmetrical = haSeeSymmetrical;
    }

    @Column(name = "FJ_NEVER")
    public Integer getFjNever() {
        return this.fjNever;
    }

    public void setFjNever(Integer fjNever) {
        this.fjNever = fjNever;
    }

    @Column(name = "FJ_OCASIONALLY")
    public Integer getFjOcasionally() {
        return this.fjOcasionally;
    }

    public void setFjOcasionally(Integer fjOcasionally) {
        this.fjOcasionally = fjOcasionally;
    }

    @Column(name = "FJ_RARELY")
    public Integer getFjRarely() {
        return this.fjRarely;
    }

    public void setFjRarely(Integer fjRarely) {
        this.fjRarely = fjRarely;
    }

    @Column(name = "FJ_OFTEN")
    public Integer getFjOften() {
        return this.fjOften;
    }

    public void setFjOften(Integer fjOften) {
        this.fjOften = fjOften;
    }

    @Column(name = "AC_VERY_GOOD")
    public Integer getAcVeryGood() {
        return this.acVeryGood;
    }

    public void setAcVeryGood(Integer acVeryGood) {
        this.acVeryGood = acVeryGood;
    }

    @Column(name = "AC_GOOD")
    public Integer getAcGood() {
        return this.acGood;
    }

    public void setAcGood(Integer acGood) {
        this.acGood = acGood;
    }

    @Column(name = "AC_SATISFACTORY")
    public Integer getAcSatisfactory() {
        return this.acSatisfactory;
    }

    public void setAcSatisfactory(Integer acSatisfactory) {
        this.acSatisfactory = acSatisfactory;
    }

    @Column(name = "AC_SUFFICIENT")
    public Integer getAcSufficient() {
        return this.acSufficient;
    }

    public void setAcSufficient(Integer acSufficient) {
        this.acSufficient = acSufficient;
    }

    @Column(name = "OR_NO_AXES")
    public Integer getOrNoAxes() {
        return this.orNoAxes;
    }

    public void setOrNoAxes(Integer orNoAxes) {
        this.orNoAxes = orNoAxes;
    }

    @Column(name = "OR_ONE_AXIS")
    public Integer getOrOneAxis() {
        return this.orOneAxis;
    }

    public void setOrOneAxis(Integer orOneAxis) {
        this.orOneAxis = orOneAxis;
    }

    @Column(name = "OR_TWO_AXES")
    public Integer getOrTwoAxes() {
        return this.orTwoAxes;
    }

    public void setOrTwoAxes(Integer orTwoAxes) {
        this.orTwoAxes = orTwoAxes;
    }

    @Column(name = "OR_THREE_AXES")
    public Integer getOrThreeAxes() {
        return this.orThreeAxes;
    }

    public void setOrThreeAxes(Integer orThreeAxes) {
        this.orThreeAxes = orThreeAxes;
    }

    @Column(name = "JM_LINEAR")
    public Integer getJmLinear() {
        return this.jmLinear;
    }

    public void setJmLinear(Integer jmLinear) {
        this.jmLinear = jmLinear;
    }

    @Column(name = "JM_ROTATION")
    public Integer getJmRotation() {
        return this.jmRotation;
    }

    public void setJmRotation(Integer jmRotation) {
        this.jmRotation = jmRotation;
    }

    @Column(name = "JM_LINEAR_ROTATORY")
    public Integer getJmLinearRotatory() {
        return this.jmLinearRotatory;
    }

    public void setJmLinearRotatory(Integer jmLinearRotatory) {
        this.jmLinearRotatory = jmLinearRotatory;
    }

    @Column(name = "JM_PATH_MOVEMENT")
    public Integer getJmPathMovement() {
        return this.jmPathMovement;
    }

    public void setJmPathMovement(Integer jmPathMovement) {
        this.jmPathMovement = jmPathMovement;
    }

    @Column(name = "JF_NONE")
    public Integer getJfNone() {
        return this.jfNone;
    }

    public void setJfNone(Integer jfNone) {
        this.jfNone = jfNone;
    }

    @Column(name = "JF_LOW")
    public Integer getJfLow() {
        return this.jfLow;
    }

    public void setJfLow(Integer jfLow) {
        this.jfLow = jfLow;
    }

    @Column(name = "JF_MEDIUM")
    public Integer getJfMedium() {
        return this.jfMedium;
    }

    public void setJfMedium(Integer jfMedium) {
        this.jfMedium = jfMedium;
    }

    @Column(name = "JF_HIGH")
    public Integer getJfHigh() {
        return this.jfHigh;
    }

    public void setJfHigh(Integer jfHigh) {
        this.jfHigh = jfHigh;
    }

    @Column(name = "JA_JOIN_BASIC_COMP")
    public Integer getJaJoinBasicComp() {
        return this.jaJoinBasicComp;
    }

    public void setJaJoinBasicComp(Integer jaJoinBasicComp) {
        this.jaJoinBasicComp = jaJoinBasicComp;
    }

    @Column(name = "JA_JOIN_COMP")
    public Integer getJaJoinComp() {
        return this.jaJoinComp;
    }

    public void setJaJoinComp(Integer jaJoinComp) {
        this.jaJoinComp = jaJoinComp;
    }

    @Column(name = "JA_BASIC_COMP")
    public Integer getJaBasicComp() {
        return this.jaBasicComp;
    }

    public void setJaBasicComp(Integer jaBasicComp) {
        this.jaBasicComp = jaBasicComp;
    }

    @Column(name = "JA_MORE_COMP")
    public Integer getJaMoreComp() {
        return this.jaMoreComp;
    }

    public void setJaMoreComp(Integer jaMoreComp) {
        this.jaMoreComp = jaMoreComp;
    }

    @Column(name = "NC_ONE_BASIC_COMP")
    public Integer getNcOneBasicComp() {
        return this.ncOneBasicComp;
    }

    public void setNcOneBasicComp(Integer ncOneBasicComp) {
        this.ncOneBasicComp = ncOneBasicComp;
    }

    @Column(name = "NC_TWO_BASIC_COMP")
    public Integer getNcTwoBasicComp() {
        return this.ncTwoBasicComp;
    }

    public void setNcTwoBasicComp(Integer ncTwoBasicComp) {
        this.ncTwoBasicComp = ncTwoBasicComp;
    }

    @Column(name = "NC_THREE_BASIC_COMP")
    public Integer getNcThreeBasicComp() {
        return this.ncThreeBasicComp;
    }

    public void setNcThreeBasicComp(Integer ncThreeBasicComp) {
        this.ncThreeBasicComp = ncThreeBasicComp;
    }

    @Column(name = "NC_MORE_COMP")
    public Integer getNcMoreComp() {
        return this.ncMoreComp;
    }

    public void setNcMoreComp(Integer ncMoreComp) {
        this.ncMoreComp = ncMoreComp;
    }

    @Column(name = "JS_SEC_ALL_DIRECTION")
    public Integer getJsSecAllDirection() {
        return this.jsSecAllDirection;
    }

    public void setJsSecAllDirection(Integer jsSecAllDirection) {
        this.jsSecAllDirection = jsSecAllDirection;
    }

    @Column(name = "JS_GRAVITY_FIT")
    public Integer getJsGravityFit() {
        return this.jsGravityFit;
    }

    public void setJsGravityFit(Integer jsGravityFit) {
        this.jsGravityFit = jsGravityFit;
    }

    @Column(name = "JS_GRAVITY_RUBBING")
    public Integer getJsGravityRubbing() {
        return this.jsGravityRubbing;
    }

    public void setJsGravityRubbing(Integer jsGravityRubbing) {
        this.jsGravityRubbing = jsGravityRubbing;
    }

    @Column(name = "JS_ADDITIONAL_SEC")
    public Integer getJsAdditionalSec() {
        return this.jsAdditionalSec;
    }

    public void setJsAdditionalSec(Integer jsAdditionalSec) {
        this.jsAdditionalSec = jsAdditionalSec;
    }

    @Column(name = "SO_NONE")
    public Integer getSoNone() {
        return this.soNone;
    }

    public void setSoNone(Integer soNone) {
        this.soNone = soNone;
    }

    @Column(name = "SO_ONE")
    public Integer getSoOne() {
        return this.soOne;
    }

    public void setSoOne(Integer soOne) {
        this.soOne = soOne;
    }

    @Column(name = "SO_TWO")
    public Integer getSoTwo() {
        return this.soTwo;
    }

    public void setSoTwo(Integer soTwo) {
        this.soTwo = soTwo;
    }

    @Column(name = "SO_MORE")
    public Integer getSoMore() {
        return this.soMore;
    }

    public void setSoMore(Integer soMore) {
        this.soMore = soMore;
    }

    @Column(name = "EC_A_ELE_CONSUM_FUN", nullable = false)
    public int getEcAEleConsumFun() {
        return this.ecAEleConsumFun;
    }

    public void setEcAEleConsumFun(int ecAEleConsumFun) {
        this.ecAEleConsumFun = ecAEleConsumFun;
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

    @Column(name = "FK_TB_ACE_SUB_PRO_LEV", nullable = false)
    public int getFkTbAceSubProLev() {
        return this.fkTbAceSubProLev;
    }

    public void setFkTbAceSubProLev(int fkTbAceSubProLev) {
        this.fkTbAceSubProLev = fkTbAceSubProLev;
    }

}
