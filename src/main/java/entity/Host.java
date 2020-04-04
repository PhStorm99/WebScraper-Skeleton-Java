package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Shariar
 */
@Entity
@Table(name = "host", catalog = "webscraper", schema = "")
@NamedQueries({
    @NamedQuery(name = "Host.findAll", query = "SELECT h FROM Host h"),
    @NamedQuery(name = "Host.findById", query = "SELECT h FROM Host h WHERE h.id = :id"),
    @NamedQuery(name = "Host.findByName", query = "SELECT h FROM Host h WHERE h.name = :name"),
    @NamedQuery(name = "Host.findContaining", query = "SELECT h FROM Host h WHERE h.name like CONCAT('%', :search, '%')")})
//the colon before names means it is substitutable variable.
//named queries follow JPQL standard, which are object orianted SQL queries.
public class Host implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    //host has a weak one to many relation ship with with feed.
    //if we chnage anything in host object it will cascade that change to feed as well.
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "hostid", fetch = FetchType.LAZY)
    private List<Feed> feedList;

    //Constructor
    public Host() {
    }

    //parametrized constructor
    public Host(Integer id) {
        this.id = id;
    }

    public Host(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //Setter and Getter Methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Host)) {
            return false;
        }
        Host other = (Host) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Host[ id=" + id + " ]";
    }

}
