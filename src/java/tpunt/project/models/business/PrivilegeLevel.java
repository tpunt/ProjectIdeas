package tpunt.project.models.business;

/**
 * The privilege levels of the system.
 * 
 * @author tpunt
 */
public enum PrivilegeLevel {
    STUDENT(0),
    ADMIN(1);
    
    private final int privilegeLevel;

    PrivilegeLevel(int privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }
    
    public int getValue() {
        return privilegeLevel;
    }
}
