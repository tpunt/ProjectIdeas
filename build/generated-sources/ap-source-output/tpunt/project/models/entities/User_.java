package tpunt.project.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tpunt.project.models.business.PrivilegeLevel;
import tpunt.project.models.entities.Organisation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-18T12:56:59")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, PrivilegeLevel> privilegeLevel;
    public static volatile SingularAttribute<User, Organisation> organisation;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}