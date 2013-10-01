/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package csri.poeticon.praxicon.db.entities;

import csri.poeticon.praxicon.Constants;
import csri.poeticon.praxicon.Globals;
import csri.poeticon.praxicon.db.dao.ConceptDao;
import csri.poeticon.praxicon.db.dao.RelationDao;
import csri.poeticon.praxicon.db.dao.LanguageRepresentationDao;
import csri.poeticon.praxicon.db.dao.implSQL.ConceptDaoImpl;
import csri.poeticon.praxicon.db.dao.implSQL.RelationDaoImpl;
import csri.poeticon.praxicon.db.dao.implSQL.LanguageRepresentationDaoImpl;
import csri.poeticon.praxicon.db.entities.listeners.ConceptListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Dimitris Mavroeidis
 */
@XmlRootElement(name="entity")
@Entity
@EntityListeners(ConceptListener.class)
@Table(name="Concepts")
public class Concept implements Serializable {

    public static enum Type {
        ABSTRACT, ENTITY, FEATURE, MOVEMENT, UNKNOWN;
        @Override
        public String toString()
        {
            return this.name();
        }
    }

    public static enum SpecificityLevel {
        BASIC_LEVEL, ABOVE_BASIC_LEVEL, BELOW_BASIC_LEVEL, UNKNOWN;
        @Override
        public String toString()
        {
            return this.name();
        }
    }

    public static enum Status {
        CONSTANT, VARIABLE, TEMPLATE;
        @Override
        public String toString()
        {
            return this.name();
        }
    }

    public static enum UniqueInstance {
        YES, NO, UNKNOWN ;
        @Override
        public String toString()
        {
            return this.name();
        }
    }

    public static enum PragmaticStatus {
        FIGURATIVE, LITERAL, UNKNOWN ;
        @Override
        public String toString()
        {
            return this.name();
        }
    }


    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CUST_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator="CUST_SEQ")
    @Column(name="ConceptId")
    protected Long id;

    @Column(name="Name")
    String name;

    @Column(name="Type")
    @Enumerated(EnumType.STRING)
    protected Type conceptType;

    @Column(name="SpecificityLevel")
    @Enumerated(EnumType.STRING)
    protected SpecificityLevel specificity_level;

    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    protected Status status;

    @Column(name="UniqueInstance")
    @Enumerated(EnumType.STRING)
    protected UniqueInstance unique_instance;

    @Column(name="PragmaticStatus")
    @Enumerated(EnumType.STRING)
    protected PragmaticStatus pragmatic_status;

    @Column(name="Source")
    protected String source;

    @Column(name="Comment")
    protected String description;

//    @OneToMany(cascade=CascadeType.ALL, mappedBy = "owner")
//    private List<LanguageRepresentation> language_representation;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="LanguageRepresentation")
    @JoinTable(
        name="Concept_LanguageRepresentation",
        joinColumns={@JoinColumn(name="ConceptId")},
        inverseJoinColumns={@JoinColumn(name="LanguageRepresentationId")}
    )
    private List<LanguageRepresentation> language_representations;


    @OneToMany(cascade=CascadeType.ALL, mappedBy = "owner")
    private List<VisualRepresentation> visual_representations;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "owner")
    private List<MotoricRepresentation> motoric_representations;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "concept")
    private List<UnionOfIntersections> relations;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "object")
    private List<Relation> object_of_relations;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "subject")
    private List<Relation> subjectOfRelation;


    public Concept()
    {
        description = "";
        specificity_level = Concept.SpecificityLevel.UNKNOWN;
        language_representations = (List<LanguageRepresentation>) new <List>LanguageRepresentation();
        visual_representations = (List<VisualRepresentation>) new VisualRepresentation();
        motoric_representations = (List<MotoricRepresentation>) new MotoricRepresentation();
        object_of_relations =  new ArrayList<Relation>();
        relations = new ArrayList<UnionOfIntersections>();
    }


}

//
//    @XmlTransient
//    public List<Relation> getObjOfRelations() {
//        return objOfRelations;
//    }
//
//    public void setObjOfRelations(List<Relation> objOfRelations) {
//        this.objOfRelations = objOfRelations;
//    }
//
//
//        /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;vr&gt;"
//     *     xmldescription="This tag defines the Language Representation of the
//     *     concept"
//     */
//    @XmlElement(name="lr")
//    public List<LanguageRepresentation> getLanguageRepresentation() {
//        return language_representation;
//    }
//
//    public List<LanguageRepresentation> getLanguageRepresentationsEntries() {
//        List<LanguageRepresentation> languageRepresentationEntries = new ArrayList<LanguageRepresentation>();
//        for (int i = 0; i<this.language_representation.size(); i++)
//        {
//            languageRepresentationEntries.addAll(language_representation.get(i).getEntries());
//        }
//        return languageRepresentationEntries;
//    }
//
//    public void addLanguageRepresentation(List<LanguageRepresentation> language_representation)
//    {
//        language_representation.setOwner(this);
//        this.language_representation.add(language_representation);
//    }
//
//    public void setLanguageRepresentation(List<LanguageRepresentation> language_representation) {
//        language_representation.setOwner(this);
//        this.language_representation = language_representation;
//    }
//
//
//
//    /**
//     * Gets text of the first language representation of language "en" for this concept
//     * @return a string
//     */
//    public String getLanguageRepresentationName()
//    {
//        <List>LanguageRepresentation les = this.getLanguageRepresentation();
//        for(int i = 0; i < les.size(); i++){
//            if (les.get(i).getLanguage().name().equalsIgnoreCase("en"))
//            {
//                return les.get(i).getText();
//            }
//        }
//        if (les.size() > 0)
//        {
//            return les.get(0).getText();
//        }
//        return "noname";
//    }
//
//    /**
//     * Gets the basic level of this concept as text
//     * @return "basic_level" of "";
//     */
//    public String getLevelType()
//    {
//        if(isBasicLevel() == Concept.SpecificityLevel.BASIC_LEVEL)
//        {
//            return "basic_level";
//        }
//        else if (isBasicLevel() == Concept.SpecificityLevel.ABOVE_BASIC_LEVEL)
//        {
//            return "above_basic_level";
//        }
//        else if (isBasicLevel() == Concept.SpecificityLevel.BELOW_BASIC_LEVEL)
//        {
//            return "below_basic_level";
//        }
//        else
//        {
//            return "";
//        }
//    }
//
//    /**
//     * Gets a trimmed version of the concept name
//     * @return a string
//     */
//    public String getNameTrimmed()
//    {
//        int index = this.getName().indexOf("%");
//        if (index >= 0)
//        {
//            return this.getName().substring(0, index);
//        }
//        index = this.getName().indexOf("#");
//        if (index >= 0)
//        {
//            return this.getName().substring(0, index);
//        }
//        return "";
//    }
//
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;source&gt;"
//     *     xmldescription="This tag defines the source of the concept (from which
//     *          resources was generated (for example: Wordnet)"
//     */
//    @XmlElement(name="source")
//    public String getSource()
//    {
//        return source;
//    }
//
//    public void setSource(String s)
//    {
//        this.source = s;
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;union_of_intersections_of_relations&gt;"
//     *     xmldescription="This tag defines the union of interesections
//     *     of relation that this concept participates"
//     */
//    @XmlElement(name="union_of_intersections_of_relations")
//    public List<UnionOfIntersections> getRelations() {
//        return relations;
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;description&gt;"
//     *     xmldescription="This tag defines is a field for future use"
//     */
//    @XmlElement(name="description")
//    public String getDescription()
//    {
//        return description;
//    }
//
//    public void setDescription(String description)
//    {
//        this.description = description.trim();
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;is_basic_level&gt;"
//     *     xmldescription="This tag defines if the entity is basic level"
//     */
//    @XmlElement(name="is_basic_level")
//    public SpecificityLevel isBasicLevel()
//    {
//        return specificity_level;
//    }
//
//    public void setSpecificityLevel(String levelType)
//    {
//        if(levelType.equalsIgnoreCase("BASIC_LEVEL"))
//        {
//            this.specificity_level = Concept.SpecificityLevel.BASIC_LEVEL;
//        }
//        else if(levelType.equalsIgnoreCase("ABOVE_BASIC_LEVEL"))
//        {
//            this.specificity_level = Concept.SpecificityLevel.ABOVE_BASIC_LEVEL;
//        }
//        else if(levelType.equalsIgnoreCase("BELOW_BASIC_LEVEL"))
//        {
//            this.specificity_level = Concept.SpecificityLevel.ABOVE_BASIC_LEVEL;
//        }
//        else
//        {
//            this.specificity_level = Concept.SpecificityLevel.UNKNOWN;
//        }
//    }
//
//    public SpecificityLevel getBasicLevel()
//    {
//        return specificity_level;
//    }
//
//    public void setBasicLevel(SpecificityLevel basicLevel)
//    {
//        this.specificity_level = basicLevel;
//    }
//
//    public void setBasicLevel(String levelType)
//    {
//        if(levelType.equalsIgnoreCase("BASIC_LEVEL"))
//        {
//            this.specificity_level = Concept.SpecificityLevel.BASIC_LEVEL;
//        }
//        else if(levelType.equalsIgnoreCase("ABOVE_BASIC_LEVEL"))
//        {
//            this.specificity_level = Concept.SpecificityLevel.ABOVE_BASIC_LEVEL;
//        }
//        else if(levelType.equalsIgnoreCase("BELOW_BASIC_LEVEL"))
//        {
//            this.specificity_level = Concept.SpecificityLevel.ABOVE_BASIC_LEVEL;
//        }
//        else
//        {
//            this.specificity_level = Concept.SpecificityLevel.UNKNOWN;
//        }
//    }
//
//    /**
//     * Gets a string of concatenated full info for the concept.
//     * concept type, status, pragmatic status, basic level, description
//     * @return a string
//     */
//    public String getInfo()
//    {
//        StringBuilder sb= new StringBuilder();
//        sb.append(this.getConceptType());
//        sb.append("#");
//        sb.append(this.getStatus());
//        sb.append("#");
//        sb.append(this.getPragmaticStatus());
//        sb.append("#");
//        sb.append(this.isBasicLevel());
//        sb.append("#");
//        sb.append(this.getDescription());
//
//        return sb.toString();
//    }
//
//    /**
//     * Gets a string of concatenated shoft info for the concept.
//     * concept type and basic level
//     * @return a string
//     */
//    public String getInfoShort()
//    {
//        StringBuilder sb= new StringBuilder();
//        sb.append(this.getConceptType());
//        sb.append("#");
//        sb.append(this.isBasicLevel());
//        return sb.toString();
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;type&gt;"
//     *     xmldescription="This tag defines the type of the entity (movement, entity,
//     *                     feature, abstract)"
//     */
//    @XmlElement(name="type")
//    public Type getConceptType()
//    {
//        return concept_type;
//    }
//
//    public void setConceptType(Type conceptType)
//    {
//        this.concept_type = conceptType;
//    }
//
//    public void setConceptType(String conceptType)
//    {
//        this.concept_type = Type.valueOf(conceptType.trim().toUpperCase());
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;p_status&gt;"
//     *     xmldescription="This tag defines if the entity is literal or figurative"
//     */
//    @XmlElement(name="p_status")
//    public PragmaticStatus getPragmaticStatus()
//    {
//        return pragmatic_status;
//    }
//
//    public void setPragmaticStatus(PragmaticStatus pragmatic_status)
//    {
//        this.pragmatic_status = pragmatic_status;
//    }
//
//    public void setPragmaticStatus(String pragmatic_status)
//    {
//        String tmp = pragmatic_status;
//        tmp = tmp.replace(".", "_");
//        tmp = tmp.replace(":", "_");
//        this.pragmatic_status = PragmaticStatus.valueOf(tmp.trim().toUpperCase());
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;status&gt;"
//     *     xmldescription="This tag defines if the entity is a variable, an analogy or a constant"
//     */
//    @XmlElement(name="status")
//    public Status getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(Status varType)
//    {
//        this.status = varType;
//    }
//
//    public void setStatus(String varType)
//    {
//        this.status = Status.valueOf(varType.trim().toUpperCase());
//    }
//
//    /**
//     * Adds a new union of intersections to this concept containg an intersection
//     * of relations created using given relation types (fw+bw) and given relation objects
//     * @param rTypeForward list of forward types of relations
//     * @param rTypeBackward list of backward types of relations
//     * @param obj list of concepts to be used as objects
//     */
//    public void addRelation(List<String> rTypeForward, List<String> rTypeBackward, List<Concept>obj)
//    {
//        UnionOfIntersections union = new UnionOfIntersections();
//        IntersectionOfRelations inter = new IntersectionOfRelations();
//        for (int i = 0; i < rTypeForward.size(); i++)
//        {
//            TypeOfRelation rType = new TypeOfRelation();
//            rType.setForwardName(rTypeForward.get(i));
//            rType.setBackwardName(rTypeBackward.get(i));
//            Relation rel = new Relation();
//            rel.setType(rType);
//            rel.setSubject(this);
//            rel.setObject(obj.get(i));
//            RelationChain rChain = new RelationChain();
//            rChain.addRelation(rel, 0);
//            inter.addRelationChain(rChain);
//        }
//        union.addIntersection(inter);
//        this.addRelation(union);
//    }
//
//    /**
//     * Gets all unions of intersections for this concept by adding the unions of
//     * intresections that have it as the owner and creating unions of intersections
//     * for each relation that has this concept as object
//     * @return list of UnionOfIntersections
//     */
//    public List<UnionOfIntersections> getAllRelations() {
//        List <UnionOfIntersections> res = new ArrayList<UnionOfIntersections>();
//        res.addAll(relations);
//
//        RelationDao rDao = new RelationDaoImpl();
//        res.addAll(rDao.getObjRelations(this));
//
//        Concept tmp = null;
//        for (int i = 0; i < res.size(); i++)
//        {
//            UnionOfIntersections union = res.get(i);
//            for(int j=0; j<union.getIntersections().size(); j++)
//            {
//                IntersectionOfRelations inter = union.getIntersections().get(j);
//                for (int k = 0; k < inter.getRelations().size(); k++)
//                {
//                    RelationChain relCh = inter.getRelations().get(k);
//                    for (int l = 0; l<relCh.getRelations().size(); l++)
//                    {
//                        for (int m = 0; m < relCh.getRelations().size(); m++)
//                        {
//                            if (l == relCh.getRelations().get(m).relationOrder)
//                            {
//                                if (l == 0)
//                                {
//                                    if (relCh.getRelations().get(m).getRelation().getObject().equals(this))
//                                    {
//                                        Relation t = new Relation();
//
//                                        t.setObject(relCh.getRelations().get(m).getRelation().getSubject());
//                                        t.setSubject(this);
//                                        TypeOfRelation.RELATION_NAME tmpStr = relCh.getRelations().get(m).getRelation().getType().getBackwardName();
//                                        t.getType().setBackwardName(relCh.getRelations().get(m).getRelation().getType().getForwardName());
//                                        t.getType().setForwardName(tmpStr);
//                                        relCh.getRelations().get(m).setRelation(t);
//                                    }
//                                    tmp = relCh.getRelations().get(m).getRelation().getObject();
//                                }
//                                else
//                                {
//                                    if (relCh.getRelations().get(m).getRelation().getObject().equals(tmp))
//                                    {
//                                        Relation t = new Relation();
//                                        t.setObject(relCh.getRelations().get(m).getRelation().getSubject());
//                                        t.setSubject(tmp);
//                                         TypeOfRelation.RELATION_NAME tmpStr = relCh.getRelations().get(m).getRelation().getType().getBackwardName();
//                                        t.getType().setBackwardName(relCh.getRelations().get(m).getRelation().getType().getForwardName());
//                                        t.getType().setForwardName(tmpStr);
//                                        relCh.getRelations().get(m).setRelation(t);
//                                    }
//                                    tmp = relCh.getRelations().get(m).getRelation().getObject();
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        //remove double entries
//        for(int i = 0; i < res.size(); i++)
//        {
//            if (res.get(i).getIntersections().size() == 1)
//            {
//                if (res.get(i).getIntersections().get(0).relations.size() == 1)
//                {
//                    if (res.get(i).getIntersections().get(0).relations.get(0).getRelations().size() == 1)
//                    {
//                        boolean removeR = false;
//                        Relation r = res.get(i).getIntersections().get(0).relations.get(0).getActualRelations().get(0);
//                        for (int j = 0; j < res.size(); j++)
//                        {
//                            if(j == i)
//                            {
//                                continue;
//                            }
//                            List<IntersectionOfRelations> inter = res.get(j).getIntersections();
//                            for (int k = 0; k < inter.size(); k++)
//                            {
//                                for (int l = 0; l < inter.get(k).getRelations().size(); l++)
//                                {
//                                    for (int m = 0; m < inter.get(k).getRelations().get(l).getRelations().size(); m++)
//                                    {
//                                        if (inter.get(k).getRelations().get(l).getRelations().get(m).getRelationOrder() == 0)
//                                        {
//                                            if(inter.get(k).getRelations().get(l).getRelations().get(m).getRelation().equals(r))
//                                            {
//                                                removeR = true;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//                        if(removeR)
//                        {
//                            res.remove(i);
//                            i--;
//                        }
//                    }
//                }
//            }
//
//        }
//        return res;
//    }
//
//
//    /**
//     * Gets all unions of intersections for this concept excluding ISA relations
//     * (toke-type and type-token)by adding the unions of intresections that have
//     * it as the owner and creating unions of intersections for each relation
//     * that has this concept as object
//     * @return list of UnionOfIntersections
//     */
//   public List<UnionOfIntersections> getConceptsRelatedWithWithoutISAasUnions() {
//        List <UnionOfIntersections> concepts = new ArrayList<UnionOfIntersections>();
//
//        List <UnionOfIntersections> res = new ArrayList<UnionOfIntersections>();
//        res.addAll(relations);
//
//        RelationDao rDao = new RelationDaoImpl();
//        res.addAll(rDao.getObjRelations(this));
//
//        Concept tmp = null;
//        for (int i = 0; i < res.size(); i++)
//        {
//            UnionOfIntersections union = res.get(i);
//            for(int j=0; j<union.getIntersections().size(); j++)
//            {
//                IntersectionOfRelations inter = union.getIntersections().get(j);
//                for (int k = 0; k < inter.getRelations().size(); k++)
//                {
//                    RelationChain relCh = inter.getRelations().get(k);
//                    for (int l = 0; l<relCh.getRelations().size(); l++)
//                    {
//                        for (int m = 0; m < relCh.getRelations().size(); m++)
//                        {
//                            if (l == relCh.getRelations().get(m).relationOrder)
//                            {
//                                Relation rel = relCh.getRelations().get(m).getRelation();
//                                if(rel.getType().getForwardName()!= TypeOfRelation.RELATION_NAME.TYPE_TOKEN &&
//                                        rel.getType().getForwardName()!= TypeOfRelation.RELATION_NAME.TOKEN_TYPE)
//                                {
//                                    UnionOfIntersections u = new UnionOfIntersections();
//                                    IntersectionOfRelations inters = new IntersectionOfRelations();
//                                    RelationChain rel_ch = new RelationChain();
//                                    rel_ch.addRelation(rel, 0);
//                                    inters.addRelationChain(rel_ch);
//                                    u.addIntersection(inters);
//                                    concepts.add(u);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return concepts;
//    }
//
//    /**
//     * Gets all values (connected with has_value) of this concept excluding
//     * the concepts in a given list
//     * @param stack a list of concepts to exclude
//     * @return list of concepts
//     */
//    public List<Concept> getValue(List<Concept> stack)
//    {
//        List<Concept> res = new ArrayList<Concept>();
//        List<UnionOfIntersections> unions = this.getAllRelations();
//        for (int i = 0; i < unions.size(); i++)
//        {
//            UnionOfIntersections union = unions.get(i);
//            for (int j =0; j < union.getIntersections().size(); j++)
//            {
//                IntersectionOfRelations inter = union.getIntersections().get(j);
//                if (inter.getRelations().size() == 1)
//                {
//                    RelationChain rc = inter.getRelations().get(0);
//                    if (rc.getRelations().size() == 1)
//                    {
//                        Relation rel = rc.getRelations().get(0).getRelation();
//                        if (rel.getType().getForwardName() == TypeOfRelation.RELATION_NAME.HAS_VALUE ||
//                                rel.getType().getBackwardName() == TypeOfRelation.RELATION_NAME.HAS_VALUE)
//                        {
//                            Concept tmp = rel.getObject();
//                            if (tmp.equals(this))
//                            {
//                                tmp = rel.getSubject();
//                            }
//                            if (tmp.getStatus().name().equalsIgnoreCase("variable") &&
//                                    !stack.contains(tmp))
//                            {
//                                stack.add(tmp);
//                                res.addAll(tmp.getValue(stack));
//                                stack.remove(tmp);
//                            }
//                            else
//                            {
//                                res.add(tmp);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return res;
//    }
//
//    /**
//     * Gets all concept related to this concept, using this concepts all unions of
//     * intersections and object unions of intersections
//     * @return list of concepts
//     */
//    public List<Concept> getConceptsRelatedWith() {
//        List <Concept> concepts = new ArrayList<Concept>();
//
//        List <UnionOfIntersections> res = new ArrayList<UnionOfIntersections>();
//        res.addAll(relations);
//
//        RelationDao rDao = new RelationDaoImpl();
//        res.addAll(rDao.getObjRelations(this));
//
//        Concept tmp = null;
//        for (int i = 0; i < res.size(); i++)
//        {
//            UnionOfIntersections union = res.get(i);
//            for(int j=0; j<union.getIntersections().size(); j++)
//            {
//                IntersectionOfRelations inter = union.getIntersections().get(j);
//                for (int k = 0; k < inter.getRelations().size(); k++)
//                {
//                    RelationChain relCh = inter.getRelations().get(k);
//                    for (int l = 0; l<relCh.getRelations().size(); l++)
//                    {
//                        for (int m = 0; m < relCh.getRelations().size(); m++)
//                        {
//                            if (l == relCh.getRelations().get(m).relationOrder)
//                            {
//                                Relation rel = relCh.getRelations().get(m).getRelation();
//                                if (!rel.getObject().equals(this))
//                                {
//                                    if (!concepts.contains(rel.getObject()))
//                                    {
//                                        concepts.add(rel.getObject());
//                                    }
//                                }
//                                if (!rel.getSubject().equals(this))
//                                {
//                                    if (!concepts.contains(rel.getSubject()))
//                                    {
//                                        concepts.add(rel.getSubject());
//                                    }
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return concepts;
//    }
//
//    /**
//     * Gets all relations that belong to unions of intersections of this concept
//     * and have this concept as subject or object (relations reversed if object)
//     * @return list of relations
//     */
//    public List<Relation> getRelatedConceptsSingle()
//    {
//         List <Relation> res = new ArrayList<Relation>();
//         for (int i = 0; i < this.getRelations().size(); i++)
//         {
//             UnionOfIntersections union = this.getRelations().get(i);
//
//             for (int j =0; j < union.getIntersections().size(); j++ )
//             {
//                 IntersectionOfRelations inter = union.getIntersections().get(j);
//
//                 for (int k = 0; k < inter.getRelations().size(); k ++ )
//                 {
//                     RelationChain rChain = inter.getRelations().get(k);
//
//                     List<Relation> rels = rChain.getActualRelations();
//                     for(int l = 0; l < rels.size(); l ++)
//                     {
//                         Relation r = rels.get(l);
//                         Relation tmpRel = new Relation();
//                         if (r.getSubject() == this)
//                         {
//                             tmpRel.setSubject(r.getSubject());
//                             tmpRel.setObject(r.getObject());
//                             tmpRel.setType(r.getType());
//
//                             res.add(tmpRel);
//                         }
//                         else
//                         {
//                             tmpRel.setSubject(r.getObject());
//                             tmpRel.setObject(r.getSubject());
//                             TypeOfRelation reverseType = new TypeOfRelation();
//                             reverseType.setForwardName(r.getType().getBackwardName());
//                             reverseType.setBackwardName(r.getType().getForwardName());
//                             tmpRel.setType(reverseType);
//                             res.add(tmpRel);
//                         }
//                     }
//                 }
//             }
//         }
//         return res;
//    }
//
//    /**
//     * Gets all relations that belong to unions of intersections of this concept
//     * and have this concept as subject or object (relations reversed if object)
//     * and creates a union of intersections for each of them
//     * @return list of UnionOfIntersections
//     */
//    public List<UnionOfIntersections> getAllRelationsSingle() {
//        List <UnionOfIntersections> res = new ArrayList<UnionOfIntersections>();
//        List <UnionOfIntersections> tmp = this.getAllRelations();
//        for (int i = 0; i < tmp.size(); i++)
//        {
//            for (int j = 0; j < tmp.get(i).getIntersections().size(); j++)
//            {
//                IntersectionOfRelations inter = tmp.get(i).getIntersections().get(j);
//                for (int k = 0; k < inter.getRelations().size(); k++)
//                {
//                    RelationChain rc = inter.getRelations().get(k);
//
//                    for (int l = 0; l <rc.getRelations().size(); l++)
//                    {
//                        Relation r = rc.getRelations().get(l).getRelation();
//                        RelationChain tmpRC = new RelationChain();
//                        tmpRC.addRelation(r, 0);
//                        IntersectionOfRelations tmpInter = new IntersectionOfRelations();
//                        tmpInter.addRelationChain(rc);
//                        UnionOfIntersections tmpUnion = new UnionOfIntersections();
//                        tmpUnion.addIntersection(tmpInter);
//                        res.add(tmpUnion);
//                    }
//                }
//            }
//        }
//        return res;
//    }
//    public void setRelations(List<UnionOfIntersections> relations) {
//        this.relations = relations;
//    }
//
//    public void addRelation(UnionOfIntersections relation)
//    {
//        relation.setConcept(this);
//        this.relations.add(relation);
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="name"
//     *     xmldescription="This attribute defines the name of the element"
//     */
//    @XmlAttribute()
//    public String getName() {
//        if (name!=null)
//        {
//            return name;
//        }
//        else
//            return id+"";
//    }
//
//    public String getNameNoNumbers() {
//        if (name!=null)
//        {
//            return name.replaceAll("%\\d+:\\d+:\\d+:\\d*:\\d*", "");
//        }
//        else
//            return id+"";
//    }
//
//    public void setName(String name) {
//        this.name = name.trim();
//    }
//

//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;lr&gt;"
//     *     xmldescription="This tag defines the Language Representation of the
//     *     concept"
//     */
//    @XmlElement(name="lr")
//    public LanguageRepresentation getLanguageRepresentation() {
//        return LanguageRepresentation;
//    }
//
//    public LanguageRepresentation getLanguageRepresentationsEntries() {
//        return language_representation;
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;vr&gt;"
//     *     xmldescription="This tag defines the Visual Representation of the
//     *     concept"
//     */
//    @XmlElement(name="vr")
//    public VisualRepresentation getVisualRepresentation() {
//        return visual_representation;
//    }
//
//    public List<VisualRepresentation> getVisualRepresentationsEntries() {
//        List<VisualRepresentation> visualRepresentationEntries = new ArrayList<VisualRepresentation>();
//        for (int i = 0; i<this.VisualRepresentation.size(); i++)
//        {
//            visualRepresentationEntries.addAll(VisualRepresentation.get(i).getEntries());
//        }
//        return visualRepresentationEntries;
//    }
//
//    public void addVisualRepresentation(VisualRepresentationGroup VisualRepresentation)
//    {
//        VisualRepresentation.setOwner(this);
//        this.VisualRepresentations.add(VisualRepresentation);
//    }
//
//    public void setVisualRepresentation(List<VisualRepresentationGroup> VisualRepresentations) {
//        for(int i = 0; i <  VisualRepresentations.size(); i++)
//        {
//            VisualRepresentations.get(i).setOwner(this);
//        }
//        this.VisualRepresentation = VisualRepresentation;
//    }
//
//    public void setLanguageRepresentation(LanguageRepresentation language_representation) {
//        this.LanguageRepresentation = language_representation;
//    }
//
//    /**
//     * @xmlcomments.args
//     *	   xmltag="&lt;mr&gt;"
//     *     xmldescription="This tag defines the motoric representation"
//     */
//    @XmlElement(name="mr")
//    public List<MotoricRepresentationGroup> getMotoricRepresentations() {
//        return motoricRepresentation;
//    }
//
//    public List<MotoricRepresentation> getMotoricRepresentationsEntries() {
//        List<MotoricRepresentation> motoricRepresentationEntries = new ArrayList<MotoricRepresentation>();
//        for (int i = 0; i<this.MotoricRepresentation.size(); i++)
//        {
//            motoricRepresentationEntries.addAll(motoricRepresentation.get(i).getEntries());
//        }
//        return motoricRepresentationEntries;
//    }
//
//    public void setMotoricRepresentations(List<MotoricRepresentationGroup> motoricRepresentations) {
//        this.motoricRepresentation = motoricRepresentation;
//    }
//
//    public void addMotoricRepresentation(MotoricRepresentationGroup MotoricRepresentation)
//    {
//        MotoricRepresentation.setOwner(this);
//        this.MotoricRepresentation.add(MotoricRepresentation);
//    }
//
//    @XmlAttribute
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Concept)) {
//            return false;
//        }
//        Concept other = (Concept) object;
//        if (this.name !=null && other.name !=null && this.name.equalsIgnoreCase(other.name))
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    @Override
//    public String toString() {
//        if(name != null && !name.equalsIgnoreCase(""))
//        {
//            return name;// + " (Entity)";
//        }
//        else
//        {
//            List <LanguageRepresentation> tmpList = this.getLanguageRepresentationsEntries();
//            if (tmpList.size()>0)
//            {
//                StringBuilder tmp = new StringBuilder(tmpList.get(0).getText());
//                for (int i = 1; i< tmpList.size(); i++)
//                {
//                    tmp.append("\\"+tmpList.get(i).getText());
//                }
//                return tmp.toString();
//            }
//            else
//            {
//                return id + "";
//            }
//        }
//    }
//
//    public void afterUnmarshal(Unmarshaller u, Object parent) {
//
//        if (Globals.ToMergeAfterUnMarshalling)
//        {
//            ConceptDao cDao = new ConceptDaoImpl();
//            Concept tmp = cDao.getConceptWithNameOrID(this.getName());
//            if (tmp == null)
//            {
//                if (this.concept_type == null)
//                {
//                    this.concept_type = Type.UNKNOWN;
//                }
//
//                cDao.merge(this);
//            }
//            else
//            {
//                cDao.update(this);
//            }
//        }
//        else
//        {
//            Concept tmp = (Concept)Constants.globalConcepts.get(this.getName());
//            if (tmp == null)
//            {
//                if (this.concept_type == null)
//                {
//                    this.concept_type = Type.UNKNOWN;
//                }
//                tmp = new Concept(this);
//                Constants.globalConcepts.put(tmp.getName(), tmp);
//            }
//            else
//            {
//                tmp.concept_type = this.concept_type;
//                updateLanguageRepresentations(tmp);
//                updateVisualRepresentations(tmp);
//                updateMotoricRepresentations(tmp);
//                updateObjOfRelations(tmp);
//                updateRelations(tmp);
//              }
//        }
//
//        System.err.println("Finish unmarshalling: " + this.getName());
//    }
//
//    /**
//     * Updates LanguageRepresentation of a concept using this concept LanguageRepresentation
//     * @param oldCon the concept to be updated
//     */
//    public void updateLanguageRepresentations(Concept oldCon) {
//        for (int i = 0; i < this.getLanguageRepresentations().size(); i++) {
//            if (!oldCon.getLanguageRepresentations().contains(this.getLanguageRepresentations().get(i))) {
//                this.getLanguageRepresentations().get(i).getConcepts().remove(this);
//                this.getLanguageRepresentations().get(i).getConcepts().add(this);
//                oldCon.getLanguageRepresentations().add(this.getLanguageRepresentations().get(i));
//            }
//        }
//    }
//
//    /**
//     * Updates MotoricRepresentations of a concept using this concept MotoricRepresentations
//     * @param oldCon the concept to be updated
//     */
//    public void updateMotoricRepresentations(Concept oldCon) {
//        for (int i = 0; i < this.getMotoricRepresentations().size(); i++) {
//            if (!oldCon.getMotoricRepresentations().contains(this.getMotoricRepresentations().get(i))) {
//                this.getMotoricRepresentations().get(i).setOwner(oldCon);
//                oldCon.getMotoricRepresentations().add(this.getMotoricRepresentations().get(i));
//            }
//        }
//    }
//
//    /**
//     * Updates object relations of a concept using this concept object relations
//     * @param oldCon the concept to be updated
//     */
//    public void updateObjOfRelations(Concept oldCon) {
//        for (int i = 0; i < this.getObjOfRelations().size(); i++) {
//            if (!oldCon.getObjOfRelations().contains(this.getObjOfRelations().get(i))) {
//                if (this.getObjOfRelations().get(i).getObject().equals(this))
//                {
//                    this.getObjOfRelations().get(i).setObject(oldCon);
//                }
//                else
//                {
//                    this.getObjOfRelations().get(i).setSubject(oldCon);
//                }
//                oldCon.getObjOfRelations().add(this.getObjOfRelations().get(i));
//            }
//        }
//    }
//
//    /**
//     * Updates relations of a concept using this concept relations
//     * @param oldCon the concept to be updated
//     */
//    public void updateRelations(Concept oldCon) {
//        for (int i = 0; i < this.getRelations().size(); i++) {
//            if (!oldCon.getRelations().contains(this.getRelations().get(i))) {
//                this.getRelations().get(i).setConcept(oldCon);
//                oldCon.getRelations().add(this.getRelations().get(i));
//            }
//        }
//    }
//
//    /**
//     * Updates VisualRepresentations of a concept using this concept VisualRepresentations
//     * @param oldCon the concept to be updated
//     */
//    public void updateVisualRepresentations( Concept oldCon) {
//        for (int i = 0; i < this.getVisualRepresentations().size(); i++) {
//            if (!oldCon.getVisualRepresentations().contains(this.getVisualRepresentations().get(i))) {
//                this.getVisualRepresentations().get(i).setOwner(oldCon);
//                oldCon.getVisualRepresentations().add(this.getVisualRepresentations().get(i));
//            }
//        }
//    }
//
//    private Concept(Concept newCon)
//    {
//        this.concept_type=newCon.getConceptType();
//        this.specificity_level=newCon.isBasicLevel();
//        this.description=newCon.getDescription();
//        this.pragmatic_status=newCon.getPragmaticStatus();
//        this.status = newCon.getStatus();
//        LanguageRepresentation = new LanguageRepresentation();
//        VisualRepresentation = new ArrayList<VisualRepresentationGroup>();
//        MotoricRepresentation = new ArrayList<MotoricRepresentationGroup>();
//        objOfRelations =  new ArrayList<Relation>();
//        relations = new ArrayList<UnionOfIntersections>();
//        this.name = newCon.name;
//
//        for(int i = 0; i < newCon.getLanguageRepresentations().size(); i++)
//        {
//            if (!this.getLanguageRepresentations().contains(newCon.getLanguageRepresentations().get(i)))
//            {
//                newCon.getLanguageRepresentations().get(i).getConcepts().remove(newCon);
//                this.getLanguageRepresentations().add(newCon.getLanguageRepresentations().get(i));
//            }
//        }
//
//        for(int i = 0; i < newCon.getVisualRepresentations().size(); i++)
//        {
//            if (!this.getVisualRepresentations().contains(newCon.getVisualRepresentations().get(i)))
//            {
//                newCon.getVisualRepresentations().get(i).setOwner(this);
//                this.getVisualRepresentations().add(newCon.getVisualRepresentations().get(i));
//            }
//        }
//
//        for(int i = 0; i < newCon.getMotoricRepresentations().size(); i++)
//        {
//            if (!this.getMotoricRepresentations().contains(newCon.getMotoricRepresentations().get(i)))
//            {
//                newCon.getMotoricRepresentations().get(i).setOwner(this);
//                this.getMotoricRepresentations().add(newCon.getMotoricRepresentations().get(i));
//            }
//        }
//
//        for(int i = 0; i < newCon.getObjOfRelations().size(); i++)
//        {
//            if (!this.getObjOfRelations().contains(newCon.getObjOfRelations().get(i)))
//            {
//                if (newCon.getObjOfRelations().get(i).getObject().equals(newCon))
//                {
//                    newCon.getObjOfRelations().get(i).setObject(this);
//                }
//                else
//                {
//                    newCon.getObjOfRelations().get(i).setSubject(this);
//                }
//                this.getObjOfRelations().add(newCon.getObjOfRelations().get(i));
//            }
//        }
//
//        for(int i = 0; i < newCon.getRelations().size(); i++)
//        {
//            if (!this.getRelations().contains(newCon.getRelations().get(i)))
//            {
//                newCon.getRelations().get(i).setConcept(this);
//                this.getRelations().add(newCon.getRelations().get(i));
//            }
//        }
//    }
//}
