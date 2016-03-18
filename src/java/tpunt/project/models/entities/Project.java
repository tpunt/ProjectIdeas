package tpunt.project.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tpunt.project.models.entities.exceptions.InvalidInputException;

/**
 *
 * @author tpunt
 */
@Entity
@Table(name="Projects")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name="project_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title", nullable=false, length=80)
    private String title;
    @Column(name="aims_objectives", nullable=false)
    private String aimsAndObjectives;
    @Column(name="open_questions", nullable=false)
    private String openQuestions;
    @Column(name="deliverables", nullable=false)
    private String deliverables;
    @JoinColumn(name="user_id", referencedColumnName = "user_id", columnDefinition="BIGINT")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;
    @Column(name="status", nullable=false)
    private String status = "S";
    @Column(name="last_updated", columnDefinition="DATE DEFAULT CURRENT_DATE", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date lastUpdated;

    public void setTitle(String title) throws InvalidInputException {
        if (title.length() < 1 || title.length() > 100) {
            throw new InvalidInputException("Length must be between 1 and 100 characters");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAimsAndObjectives(String aimsAndObjectives) throws InvalidInputException {
        if (aimsAndObjectives.length() < 1) {
            throw new InvalidInputException("Mandatory field");
        }
        this.aimsAndObjectives = aimsAndObjectives;
    }

    public String getAimsAndObjectives() {
        return aimsAndObjectives;
    }

    public void setOpenQuestions(String openQuestions) throws InvalidInputException {
        if (openQuestions.length() < 1) {
            throw new InvalidInputException("Mandatory field");
        }
        this.openQuestions = openQuestions;
    }

    public String getOpenQuestions() {
        return openQuestions;
    }

    public void setDeliverables(String deliverables) throws InvalidInputException {
        if (deliverables.length() < 1) {
            throw new InvalidInputException("Mandatory field");
        }
        this.deliverables = deliverables;
    }

    public String getDeliverables() {
        return deliverables;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setStatus(String status) throws InvalidInputException {
        switch (status) {
            case "A":
            case "S":
                this.status = status;
                break;
            default:
                throw new InvalidInputException("Unrecognised status type (only A or S allowed)");
        }
        
    }

    public String getStatus() {
        return status;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
