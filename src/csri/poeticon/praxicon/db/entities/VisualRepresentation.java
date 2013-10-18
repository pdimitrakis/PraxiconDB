/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package csri.poeticon.praxicon.db.entities;

import csri.poeticon.praxicon.Constants;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Erevodifwntas
 */
@XmlRootElement()
@Entity
@Table(name="VisualRepresentation")
public class VisualRepresentation implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="CUST_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator="CUST_SEQ")
    private Long id;

    @Column(name="MediaType")
    private String media_type;

    @Column(name="Type")
    private String type;

    @Column(name="Representation")
    private String representation;

    public VisualRepresentation(String media_type, String representation)
    {
        this.media_type = media_type;
        this.representation = representation;
    }

    public VisualRepresentation()
    {
    }



  //  @Column(name = "CONCEPT_ID", insertable=false, updatable=false)
 //   private long ownerID;

//    @ManyToOne(optional=false, cascade=CascadeType.ALL)
//    @JoinColumn(name="VR_GROUP_ID")
//    private VisualRepresentationGroup owner;

    @Column(name="Prototype")
    private boolean prototype;

//    @XmlTransient
//    public VisualRepresentationGroup getOwner()
//    {
//        return owner;
//    }

    /**
     * @xmlcomments.args
     *	   xmltag="prototype"
     *     xmldescription="This attributes defines that this visual representation
     *     is a prototypical instance of the concept
     */
    @XmlAttribute
    public boolean isPrototype()
    {
        return prototype;
    }

    /**
     * @xmlcomments.args
     *	   xmltag="vrtype"
     *     xmldescription="This attributes defines more info about the visual representation
     *  (for example, if it is an immitation of the actual action)
     */
    @XmlAttribute
    public String getVisualRepresentationType()
    {
        return type;
    }

    public void setVisualRepresentationType(String visual_representation_type)
    {
        this.type = visual_representation_type;
    }

    public void setPrototype(boolean prototype)
    {
        this.prototype = prototype;
    }
//
//    public void setOwner(VisualRepresentationGroup owner)
//    {
//        this.owner = owner;
//    }

    /**
     * @xmlcomments.args
     *	   xmltag="&lt;mediaType&gt;"
     *     xmldescription="This tag defines the type of the media that represents
     *     visually the entity
     */
    @XmlElement()
    public String getMediaType()
    {
        return media_type;
    }

    public void setMediaType(String media_type)
    {
        this.media_type = media_type;
    }

//    @XmlAttribute(name="owner")
//    public long getOwnerID() {
//        return ownerID;
//    }


    @XmlElement()
    public String getRepresentation()
    {
        return representation;
    }

    public String getRepresentationWithPath()
    {
        if (representation.startsWith("http:"))
        {
            return representation;
        }
        if(representation.startsWith("file:"))
        {
            return representation;
        }
        if (this.media_type.equalsIgnoreCase("image"))
        {
            return Constants.imagePath + representation;
        }
        else
        {
            return Constants.videoPath + representation;
        }
    }

    public void setRepresentation(String representation)
    {
        this.representation = representation;
    }

    @XmlAttribute
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisualRepresentation))
        {
            return false;
        }
        VisualRepresentation other = (VisualRepresentation) object;
        if (this.media_type!=null&& this.representation!=null&&this.media_type.equalsIgnoreCase(other.media_type) && this.representation.equalsIgnoreCase(other.representation))
        {
            return true;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        if (this.id == null && other.id == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "[id=" + id + "] "+this.media_type+ ": "+ this.representation;
    }

//    public void afterUnmarshal(Unmarshaller u, Object parent)
//    {
//        this.owner = (VisualRepresentation)parent;
//    }
}
