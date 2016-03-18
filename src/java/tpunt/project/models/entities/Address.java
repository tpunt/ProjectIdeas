package tpunt.project.models.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The address domain model object is responsible for holding address data and
 * validating it.
 * 
 * @author tpunt
 */
@Entity
@Table(name="Addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name="address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="address_line", nullable=false, length=200)
    private String addressLine;
    @Column(name="city", nullable=false, length=50)
    private String city;
    @Column(name="region", nullable=false, length=50)
    private String region;
    @Column(name="zip", nullable=false, length=20)
    private String zip;
    @Column(name="country", nullable=false)
    private String country;

    public Long getId() {
        return id;
    }
    
    /**
     * Set the value of ID
     *
     * @param id new value of ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the value of zip
     *
     * @return the value of zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set the value of zip
     *
     * @param zip new value of zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the value of addressLine
     *
     * @return the value of addressLine
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * Set the value of addressLine
     *
     * @param addressLine new value of addressLine
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
