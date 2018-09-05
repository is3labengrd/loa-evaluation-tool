package eng.it.loatool.prototypes;

import java.util.Random;

public class Process {

    public Process() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSublevels() {
        return sublevels;
    }

    public void setSublevels(Integer sublevels) throws IllegalArgumentException {
        this.sublevels = sublevels;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) throws IllegalArgumentException {
        this.sub1 = sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) throws IllegalArgumentException {
        this.sub2 = sub2;
    }

    public String getSub3() {
        return sub3;
    }

    public void setSub3(String sub3) throws IllegalArgumentException {
        this.sub3 = sub3;
    }

    public String getAction() {
        if ((new Random()).nextInt() % 2 == 0) {
            return "Add";
        }
        return "Analysis";
    }

    private String name;
    private Integer sublevels;
    private String sub1;
    private String sub2;
    private String sub3;

}
