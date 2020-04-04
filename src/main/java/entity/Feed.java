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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "feed", catalog = "webscraper", schema = "")
@NamedQueries({
    @NamedQuery(name = "Feed.findAll", query = "SELECT f FROM Feed f join fetch f.hostid"),
    @NamedQuery(name = "Feed.findById", query = "SELECT f FROM Feed f join fetch f.hostid WHERE f.id = :id "),
    @NamedQuery(name = "Feed.findByPath", query = "SELECT f FROM Feed f join fetch f.hostid WHERE f.path = :path"),
    @NamedQuery(name = "Feed.findByType", query = "SELECT f FROM Feed f join fetch f.hostid WHERE f.type = :type"),
    @NamedQuery(name = "Feed.findByName", query = "SELECT f FROM Feed f join fetch f.hostid WHERE f.name = :name"),
    @NamedQuery(name = "Feed.findByHostId", query = "SELECT f FROM Feed f join fetch f.hostid WHERE f.hostid.id = :hostid"),
    @NamedQuery(name = "Feed.findContaining", query = "SELECT f FROM Feed f join fetch f.hostid WHERE f.name like CONCAT('%', :search, '%') or f.path like CONCAT('%', :search, '%') or f.type like CONCAT('%', :search, '%')")})
//the colon before names means it is substitutable variable.
//named queries follow JPQL standard, which are object orianted SQL queries.
//by using fetch we grab the needed dependency when getting a new object.
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "Host_id", referencedColumnName = "id")
    //lazy fetch is used in entities with many to one relationships to prevent
    //hibernate from getteing unneeded data till it is actualy needed.
    //on the other hand eager will grab everything from begining.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Host hostid;
    //Feed has a weak one to many relation ship with with image.
    //if we chnage anything in Feed object it will cascade that change to image as well.
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "feedid", fetch = FetchType.LAZY)
    private List<Image> imageList;

    //Constructor
    public Feed() {
    }

    public Feed(Integer id) {
        this.id = id;
    }

    // Parameterized Constructor
    public Feed(Integer id, String path, String type, String name) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.name = name;
    }

    // Setter and Getter method
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Host getHostid() {
        return hostid;
    }

    public void setHostid(Host hostid) {
        this.hostid = hostid;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
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
        if (!(object instanceof Feed)) {
            return false;
        }
        Feed other = (Feed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Feed[ id=" + id + " ]";
    }

}
