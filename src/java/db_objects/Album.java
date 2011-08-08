package db_objects;
// Generated 29.07.2011 14:49:44 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Album generated by hbm2java
 */
@Entity
@Table(name="album"
    ,catalog="j_chat"
)
public class Album  implements java.io.Serializable {


     private Long id;
     private User user;
     private String title;
     private String description;
     private Set<Photo> photos = new HashSet<Photo>(0);

    public Album() {
    }

	
    public Album(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
    }
    public Album(User user, String title, String description, Set<Photo> photos) {
       this.user = user;
       this.title = title;
       this.description = description;
       this.photos = photos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Column(name="title", nullable=false)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="description", nullable=false, length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="album")
    public Set<Photo> getPhotos() {
        return this.photos;
    }
    
    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }




}

