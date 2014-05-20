/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csri.poeticon.praxicon.db.dao;

import gr.csri.poeticon.praxicon.db.entities.Concept;
import gr.csri.poeticon.praxicon.db.entities.Relation;
import gr.csri.poeticon.praxicon.db.entities.RelationType;
import gr.csri.poeticon.praxicon.db.entities.IntersectionOfRelationChains;
import java.util.List;

/**
 *
 * @author Erevodifwntas
 */
public interface RelationDao extends Dao<Long, Relation> {

    List<Concept> getOwners(Relation rel);

    List<IntersectionOfRelationChains> getObjRelations(Concept c);

    List<Relation> allRelationsOf(Concept c);

    boolean areRelated(Concept c1, Concept c2);

    List<Relation> findRelationsByConceptTypeOfRelation(
            Concept concept, RelationType type);
}