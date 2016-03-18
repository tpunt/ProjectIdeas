package tpunt.project.models.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import tpunt.project.models.business.PrivilegeLevel;
import tpunt.project.models.entities.exceptions.InvalidInputException;

/**
 *
 * @author tpunt
 */
@Entity
@Table(name="Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="username", nullable=false, length=20)
    private String username;
    @Column(name="password", nullable=false)
    private String password;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="first_name", nullable=false)
    private String firstName;
    @Column(name="last_name", nullable=false)
    private String lastName;
    @Column(name="phone_number", nullable=false)
    private String phoneNumber;
    @Column(name="privilege_level", nullable=false)
    private PrivilegeLevel privilegeLevel = PrivilegeLevel.STUDENT;
    @JoinColumn(name="organisation_id", referencedColumnName = "organisation_id", columnDefinition="BIGINT")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Organisation organisation;

    /**
     * Get the value of privilegeLevel
     *
     * @return the value of privilegeLevel
     */
    public PrivilegeLevel getPrivilegeLevel() {
        return privilegeLevel;
    }

    /**
     * Set the value of privilegeLevel
     *
     * @param privileges new value of privilegeLevel
     */
    public void setPrivilegeLevel(PrivilegeLevel privileges) {
        this.privilegeLevel = privileges;
    }

    /**
     * Get the value of phoneNumber
     *
     * @return the value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the value of phoneNumber
     *
     * @param phoneNumber new value of phoneNumber
     * @throws tpunt.project.models.entities.exceptions.InvalidInputException
     */
    public void setPhoneNumber(String phoneNumber) throws InvalidInputException {
        if (!phoneNumber.matches("[0-9 ]{11,15}")) {
            throw new InvalidInputException("Digits and spaces only (length 11 to 15)");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     * @throws tpunt.project.models.entities.exceptions.InvalidInputException
     */
    public void setLastName(String lastName) throws InvalidInputException {
        if (!lastName.matches("[a-zA-Z]{1,50}")) {
            throw new InvalidInputException("Alphabetical characters only (length 1 to 50)");
        }

        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     * @throws tpunt.project.models.entities.exceptions.InvalidInputException
     */
    public void setFirstName(String firstName) throws InvalidInputException {
        if (!firstName.matches("[a-zA-Z]{1,50}")) {
            throw new InvalidInputException("Alphabetical characters only (length 1 to 50)");
        }

        this.firstName = firstName;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     * @throws tpunt.project.models.entities.exceptions.InvalidInputException
     */
    public void setEmail(String email) throws InvalidInputException {
        if (!email.matches("(.+@.+\\.[a-zA-Z]+(\\.[a-zA-Z]+)?)") && email.length() < 255) {
            throw new InvalidInputException("Email format incorrect");
        }

        this.email = email;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     * @throws tpunt.project.models.entities.exceptions.InvalidInputException
     */
    public void setUsername(String username) throws InvalidInputException {
        if (!username.matches("[a-zA-Z]{3,15}")) {
            throw new InvalidInputException(
                "Alphabetical characters only (length 3 to 15)"
            );
        }

        this.username = username;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     * @throws tpunt.project.models.entities.exceptions.InvalidInputException
     */
    public void setPassword(String password) throws InvalidInputException {
        if (!password.matches(".{8,}")) {
            throw new InvalidInputException(
                "At least 8 characters in length"
            );
        }

        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }

        User other = (User) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
