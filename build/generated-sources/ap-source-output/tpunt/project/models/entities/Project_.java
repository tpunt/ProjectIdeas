package tpunt.project.models.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tpunt.project.models.entities.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-18T12:56:59")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Date> lastUpdated;
    public static volatile SingularAttribute<Project, String> aimsAndObjectives;
    public static volatile SingularAttribute<Project, Long> id;
    public static volatile SingularAttribute<Project, String> openQuestions;
    public static volatile SingularAttribute<Project, String> title;
    public static volatile SingularAttribute<Project, String> deliverables;
    public static volatile SingularAttribute<Project, User> user;
    public static volatile SingularAttribute<Project, String> status;

}