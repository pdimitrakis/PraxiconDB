/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csri.poeticon.praxicon.db.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author dmavroeidis
 */
@Entity
public class RelationArgument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CUST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUST_SEQ")
    @Column(name = "RelationArgumentId")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Concept concept;

    @OneToOne(cascade = CascadeType.ALL)
    private RelationSet relationSet;

    /*
     Relations that have "this" RelationArgument as Object.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "object")
    private List<Relation> relationsContainingRelationArgumentAsObject;

    /*
     Relations that have "this" RelationArgument as Subject.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Relation> relationsContainingRelationArgumentAsSubject;

    /**
     * Constructor #1. Both concept and relationSet are set to null.
     */
    public RelationArgument() {
        this.concept = null;
        this.relationSet = null;
    }

    /**
     * Constructor #2. concept is given and relationSet is set to null.
     *
     * @param concept
     */
    public RelationArgument(Concept concept) {
        this.concept = concept;
        this.relationSet = null;
    }

    /**
     * Constructor #3. relationSet is given and concept is set to null.
     *
     * @param relationSet
     */
    public RelationArgument(RelationSet relationSet) {
        this.concept = null;
        this.relationSet = relationSet;
    }

    /**
     * Gets the id of this RelationArgument.
     *
     * @return Long integer.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * Sets the id of this RelationArgument.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the Concept connected with this RelationArgument (can be null)
     */
    public Concept getConcept() {
        return concept;
    }

    /**
     *
     * Sets the concept of this RelationArgument. It cannot be set if the
     * relationSet has already been set.
     *
     * @param concept
     */
    public void setConcept(Concept concept) {
        if (this.relationSet == null) {
            this.concept = concept;
        }
    }

    /**
     * Gets the RelationSet of this RelationArgument.
     *
     * @return the RelationSet connected with this RelationArgument (can be null)
     */
    public RelationSet getRelationSet() {
        return relationSet;
    }

    /**
     * Sets the RelationSet of this RelationArgument. It cannot be set if the
     * concept has already been set.
     *
     * @param relationSet
     */
    public void setRelationSet(RelationSet relationSet) {
        if (this.concept == null) {
            this.relationSet = relationSet;
        }
    }

    /**
     *
     * @return an Object that is either a Concept or RelationSet.
     */
    public Object getRelationArgumentAsObject() {
        if (concept != null) {
            return (Object)this.concept;
        } else if (relationSet != null) {
            return (Object)this.relationSet;
        }
        return null;
    }

    /**
     *
     * @return the class type of this RelationArgument.
     *         Can be either Concept or RelationSet.
     */
    public Class getRelationArgumentClassType() {
        if (concept != null) {
            return this.concept.getClass();
        } else if (relationSet != null) {
            return this.relationSet.getClass();
        }
        return null;
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
        if (!(object instanceof RelationArgument)) {
            return false;
        }
        RelationArgument other = (RelationArgument)object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gr.csri.poeticon.praxicon.db.entities.RelationArgument[ id=" +
                id + " ]";
    }
}
