package tpunt.project.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tpunt.project.models.entities.Address;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-18T10:35:28")
@StaticMetamodel(Organisation.class)
public class Organisation_ { 

    public static volatile SingularAttribute<Organisation, Address> address;
    public static volatile SingularAttribute<Organisation, String> name;
    public static volatile SingularAttribute<Organisation, String> description;
    public static volatile SingularAttribute<Organisation, Long> id;

}