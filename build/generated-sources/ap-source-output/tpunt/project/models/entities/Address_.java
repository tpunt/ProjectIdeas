package tpunt.project.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-18T12:56:59")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> zip;
    public static volatile SingularAttribute<Address, String> country;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, Long> id;
    public static volatile SingularAttribute<Address, String> addressLine;
    public static volatile SingularAttribute<Address, String> region;

}