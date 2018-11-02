package eng.it.loatool.resource;

public class ResourceListItem {

    private Integer resourceId;
    private String name;
    private Integer loaPhysical;
    private Integer loaCognitive;
    private Boolean assigned;
    private Integer assignmentId;

    public ResourceListItem(Resource item, Boolean assigned, Integer assignmentId) {
        this(item, assigned);
        this.assignmentId = assignmentId;
    }

    public ResourceListItem(Resource item, Boolean assigned) {
        super();
        if (item == null)
            return;
        this.resourceId = item.getPkTbId();
        this.name = item.getName();
        this.loaPhysical = item.getLoaPhysical();
        this.loaCognitive = item.getLoaCognitive();
        this.assigned = assigned;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public Integer getLoaPhysical() {
        return loaPhysical;
    }

    public Integer getLoaCognitive() {
        return loaCognitive;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

}
