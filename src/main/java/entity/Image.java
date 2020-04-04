package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Shariar
 */
@Entity
@Table(name = "image", catalog = "webscraper", schema = "")
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i join fetch i.feedid"),
    @NamedQuery(name = "Image.findById", query = "SELECT i FROM Image i join fetch i.feedid WHERE i.id = :id"),
    @NamedQuery(name = "Image.findByFeedId", query = "SELECT i FROM Image i join fetch i.feedid WHERE i.feedid.id = :feedid"),
    @NamedQuery(name = "Image.findByName", query = "SELECT i FROM Image i join fetch i.feedid WHERE i.name = :name"),
    @NamedQuery(name = "Image.findByPath", query = "SELECT i FROM Image i join fetch i.feedid WHERE i.path = :path"),
    @NamedQuery(name = "Image.findByDate", query = "SELECT i FROM Image i join fetch i.feedid WHERE i.date = :date"),
    @NamedQuery(name = "Image.findContaining", query = "SELECT i FROM Image i join fetch i.feedid WHERE i.name like CONCAT('%', :search, '%') or i.path like CONCAT('%', :search, '%')")})
//the colon before names means it is substitutable variable.
//named queries follow JPQL standard, which are object orianted SQL queries.
//by using fetch we grab the needed dependency when getting a new object.
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "Feed_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    //lazy fetch is used in entities with many to one relationships to prevent
    //hibernate from getteing unneeded data till it is actualy needed.
    //on the other hand eager will grab everything from begining.
    private Feed feedid;

    //Constructor
    public Image() {
    }

    //Parameterized Constructor
    public Image(Integer id) {
        this.id = id;
    }

    public Image(Integer id, String name, String path, Date date) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.date = date;
    }

    // GETTER AND SETTER METHOD
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Feed getFeedid() {
        return feedid;
    }

    public void setFeedid(Feed feedid) {
        this.feedid = feedid;
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
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Image[ id=" + id + " ]";
    }

}
