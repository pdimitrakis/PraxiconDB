/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csri.poeticon.praxicon.db.entities;

import gr.csri.poeticon.praxicon.Globals;
import gr.csri.poeticon.praxicon.XmlUtils;
import gr.csri.poeticon.praxicon.db.dao.ConceptDao;
import gr.csri.poeticon.praxicon.db.dao.implSQL.ConceptDaoImpl;
import gr.csri.poeticon.praxicon.db.entities.Concept.ConceptType;
import static java.lang.System.out;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static javax.persistence.Persistence.createEntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dmavroeidis
 */
public class ConceptTest {

    /**
     * The entity manager that persists and queries the DB.
     */
//    private static final String PERSISTENCE_UNIT_NAME = 
//            "PraxiconDBPUDerbyTest";
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static ConceptDao cDao = new ConceptDaoImpl();

    public ConceptTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        // Get the entity manager for testing.
        emf = createEntityManagerFactory(Globals.JpaPUDerbyTest);
        em = emf.createEntityManager();

    }

    @AfterClass
    public static void tearDownClass() {
        // Close the entity manager for testing.
        em.close();
        emf.close();
    }

    @Before
    public void setUp() {
        XmlUtils.importConceptsFromXml("misc/test-fixtures/ConceptsTest.xml");
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of getId method, of class Concept.
     */
    @Test
    public void testGetId() {
        out.println("getId");
        Concept instance = cDao.getConceptByExternalSourceIdExact("101579028");
        String expResult = "5";
        String result = instance.getId().toString();
        out.println("expResult: " + expResult);
        out.println("result: " + expResult);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of setId method, of class Concept.
//     */
//    @Test
//    public void testSetId() {
//        out.println("setId");
//        Long id = 9999999L;
//        Concept instance = cDao.getConceptByConceptId(5);
//        instance.setId((Long)id);
//        Long result = instance.getId();
//        assertEquals(result, id);
//    }
    /**
     * Test of getExternalSourceId method, of class Concept.
     */
    @Test
    public void testGetExternalSourceId() {
        out.println("getExternalSourceId");
        Concept instance = cDao.getConceptByNameExact("salamander%1:05:00::");
        String expResult = "101629276";
        String result = instance.getExternalSourceId();
        out.println("expResult: " + expResult);
        out.println("result: " + expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of setExternalSourceId method, of class Concept.
     */
    @Test
    public void testSetExternalSourceId() {
        out.println("setExternalSourceId");
        String externalSourceId = "testExternalSourceId";
        Concept instance = cDao.getConceptByExternalSourceIdExact("101629276");
        instance.setExternalSourceId(externalSourceId);
        String result = instance.getExternalSourceId();
        assertEquals(result, externalSourceId);
    }

    /**
     * Test of getName method, of class Concept.
     */
    @Test
    public void testGetName() {
        out.println("getName");
        Concept instance = cDao.getConceptByExternalSourceIdExact("101670092");
        String expResult = "tortoise%1:05:00::";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Concept.
     */
    @Test
    public void testSetName() {
        out.println("setName");
        String name = "testName";
        Concept instance = cDao.getConceptByExternalSourceIdExact("101670092");
        instance.setName(name);
        String result = instance.getName();
        assertEquals(result, name);
    }

    /**
     * Test of getConceptType method, of class Concept.
     */
    @Test
    public void testGetConceptType() {
        System.out.println("getConceptType");
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        Concept.ConceptType expResult = ConceptType.ENTITY;
        Concept.ConceptType result = instance.getConceptType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConceptType method, of class Concept.
     */
    @Test
    public void testSetConceptType_ConceptConceptType() {
        System.out.println("setConceptType");
        Concept.ConceptType conceptType = ConceptType.MOVEMENT;
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setConceptType(conceptType);
        Concept.ConceptType result = instance.getConceptType();
        assertEquals(result, conceptType);
    }

    /**
     * Test of setConceptType method, of class Concept.
     */
    @Test
    public void testSetConceptType_String() {
        System.out.println("setConceptType");
        String conceptType = "MOVEMENT";
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setConceptType(conceptType);
        String result = instance.getConceptType().toString();
        assertEquals(result, conceptType);
    }

    /**
     * Test of getSpecificityLevel method, of class Concept.
     */
    @Test
    public void testGetSpecificityLevel() {
        System.out.println("getSpecificityLevel");
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        Concept.SpecificityLevel expResult =
                Concept.SpecificityLevel.BASIC_LEVEL;
        Concept.SpecificityLevel result = instance.getSpecificityLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpecificityLevel method, of class Concept.
     */
    @Test
    public void testSetSpecificityLevel_ConceptSpecificityLevel() {
        System.out.println("setSpecificityLevel");
        Concept.SpecificityLevel specificityLevel =
                Concept.SpecificityLevel.BASIC_LEVEL;
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setSpecificityLevel(specificityLevel);
        Concept.SpecificityLevel result = instance.getSpecificityLevel();
        assertEquals(result, specificityLevel);
    }

    /**
     * Test of setSpecificityLevel method, of class Concept.
     */
    @Test
    public void testSetSpecificityLevel_String() {
        System.out.println("setSpecificityLevel");
        String specificityLevel = Concept.SpecificityLevel.BASIC_LEVEL.
                toString();
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setSpecificityLevel(specificityLevel);
        String result = instance.getSpecificityLevel().toString();
        assertEquals(result, specificityLevel);
    }

    /**
     * Test of getStatus method, of class Concept.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        Concept.Status expResult = Concept.Status.CONSTANT;
        Concept.Status result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Concept.
     */
    @Test
    public void testSetStatus_ConceptStatus() {
        System.out.println("setStatus");
        Concept.Status status = Concept.Status.CONSTANT;
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setStatus(status);
        Concept.Status result = instance.getStatus();
        assertEquals(result, status);
    }

    /**
     * Test of setStatus method, of class Concept.
     */
    @Test
    public void testSetStatus_String() {
        System.out.println("setStatus");
        String status = Concept.Status.CONSTANT.toString();
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setStatus(status);
        String result = instance.getStatus().toString();
        assertEquals(result, status);
    }

    /**
     * Test of getPragmaticStatus method, of class Concept.
     */
    @Test
    public void testGetPragmaticStatus() {
        System.out.println("getPragmaticStatus");
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        Concept.PragmaticStatus expResult = Concept.PragmaticStatus.CONCRETE;
        Concept.PragmaticStatus result = instance.getPragmaticStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPragmaticStatus method, of class Concept.
     */
    @Test
    public void testSetPragmaticStatus_ConceptPragmaticStatus() {
        System.out.println("setPragmaticStatus");
        Concept.PragmaticStatus pragmaticStatus =
                Concept.PragmaticStatus.CONCRETE;
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setPragmaticStatus(pragmaticStatus);
        Concept.PragmaticStatus result = instance.getPragmaticStatus();
        assertEquals(result, pragmaticStatus);
    }

    /**
     * Test of setPragmaticStatus method, of class Concept.
     */
    @Test
    public void testSetPragmaticStatus_String() {
        System.out.println("setPragmaticStatus");
        String pragmaticStatus = Concept.PragmaticStatus.CONCRETE.toString();
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        instance.setPragmaticStatus(pragmaticStatus);
        String result = instance.getPragmaticStatus().toString();
        assertEquals(result, pragmaticStatus);
    }

    /**
     * Test of getUniqueInstance method, of class Concept.
     */
    @Test
    public void testGetUniqueInstance() {
        System.out.println("getUniqueInstance");
        Concept instance = cDao.getConceptByExternalSourceIdExact(
                "101743605");
        Concept.UniqueInstance expResult = Concept.UniqueInstance.NO;
        Concept.UniqueInstance result = instance.getUniqueInstance();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of setUniqueInstance method, of class Concept.
//     */
//    @Test
//    public void testSetUniqueInstance() {
//        System.out.println("setUniqueInstance");
//        Concept.UniqueInstance uniqueInstance = null;
//        Concept instance = new Concept();
//        instance.setUniqueInstance(uniqueInstance);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSource method, of class Concept.
//     */
//    @Test
//    public void testGetSource() {
//        System.out.println("getSource");
//        Concept instance = new Concept();
//        String expResult = "";
//        String result = instance.getSource();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSource method, of class Concept.
//     */
//    @Test
//    public void testSetSource() {
//        System.out.println("setSource");
//        String source = "";
//        Concept instance = new Concept();
//        instance.setSource(source);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getComment method, of class Concept.
//     */
//    @Test
//    public void testGetComment() {
//        System.out.println("getComment");
//        Concept instance = new Concept();
//        String expResult = "";
//        String result = instance.getComment();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setComment method, of class Concept.
//     */
//    @Test
//    public void testSetComment() {
//        System.out.println("setComment");
//        String comment = "";
//        Concept instance = new Concept();
//        instance.setComment(comment);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOntologicalDomains method, of class Concept.
//     */
//    @Test
//    public void testGetOntologicalDomains() {
//        System.out.println("getOntologicalDomains");
//        Concept instance = new Concept();
//        List<OntologicalDomain> expResult = null;
//        List<OntologicalDomain> result = instance.getOntologicalDomains();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setOntologicalDomains method, of class Concept.
//     */
//    @Test
//    public void testSetOntologicalDomains() {
//        System.out.println("setOntologicalDomains");
//        List<OntologicalDomain> ontologicalDomains = null;
//        Concept instance = new Concept();
//        instance.setOntologicalDomains(ontologicalDomains);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addOntologicalDomain method, of class Concept.
//     */
//    @Test
//    public void testAddOntologicalDomain() {
//        System.out.println("addOntologicalDomain");
//        OntologicalDomain ontologicalDomain = null;
//        Concept instance = new Concept();
//        instance.addOntologicalDomain(ontologicalDomain);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLanguageRepresentations method, of class Concept.
//     */
//    @Test
//    public void testGetLanguageRepresentations() {
//        System.out.println("getLanguageRepresentations");
//        Concept instance = new Concept();
//        List<LanguageRepresentation> expResult = null;
//        List<LanguageRepresentation> result =
//                instance.getLanguageRepresentations();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getConcept_LanguageRepresentation method, of class Concept.
//     */
//    @Test
//    public void testGetConceptLanguageRepresentation() {
//        System.out.println("getConcept_LanguageRepresentation");
//        Concept instance = new Concept();
//        List<Concept_LanguageRepresentation> expResult = null;
//        List<Concept_LanguageRepresentation> result =
//                instance.getConcept_LanguageRepresentation();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setConcept_LanguageRepresentation method, of class Concept.
//     */
//    @Test
//    public void testSetConcept_LanguageRepresentation() {
//        System.out.println("setConcept_LanguageRepresentation");
//        List<Concept_LanguageRepresentation> languageRepresentations = null;
//        Concept instance = new Concept();
//        instance.setConcept_LanguageRepresentation(languageRepresentations);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getConceptLanguageRepresentationsEntries method, of class Concept.
//     */
//    @Test
//    public void testGetConceptLanguageRepresentationsEntries() {
//        System.out.println("getConceptLanguageRepresentationsEntries");
//        Concept instance = new Concept();
//        List<Concept_LanguageRepresentation> expResult = null;
//        List<Concept_LanguageRepresentation> result =
//                instance.getConceptLanguageRepresentationsEntries();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addConceptLanguageRepresentation method, of class Concept.
//     */
//    @Test
//    public void testAddConceptLanguageRepresentation() {
//        System.out.println("addConceptLanguageRepresentation");
//        Concept_LanguageRepresentation conceptLanguageRepresentation = null;
//        Concept instance = new Concept();
//        instance.addConceptLanguageRepresentation(conceptLanguageRepresentation);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addLanguageRepresentation method, of class Concept.
//     */
//    @Test
//    public void testAddLanguageRepresentation() {
//        System.out.println("addLanguageRepresentation");
//        LanguageRepresentation languageRepresentation = null;
//        boolean isRepresentative = false;
//        Concept instance = new Concept();
//        instance.addLanguageRepresentation(languageRepresentation,
//                isRepresentative);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFirstLanguageRepresentationName method, of class Concept.
//     */
//    @Test
//    public void testGetFirstLanguageRepresentationName() {
//        System.out.println("getFirstLanguageRepresentationName");
//        Concept instance = new Concept();
//        String expResult = "";
//        String result = instance.getFirstLanguageRepresentationName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLanguageRepresentationsNames method, of class Concept.
//     */
//    @Test
//    public void testGetLanguageRepresentationsNames() {
//        System.out.println("getLanguageRepresentationsNames");
//        Concept instance = new Concept();
//        List<String> expResult = null;
//        List<String> result = instance.getLanguageRepresentationsNames();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLanguageRepresentationsAndRepresentative method, of class Concept.
//     */
//    @Test
//    public void testGetLanguageRepresentationsAndRepresentative() {
//        System.out.println("getLanguageRepresentationsAndRepresentative");
//        Concept instance = new Concept();
//        List<String> expResult = null;
//        List<String> result =
//                instance.getLanguageRepresentationsAndRepresentative();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateLanguageRepresentations method, of class Concept.
//     */
//    @Test
//    public void testUpdateLanguageRepresentations() {
//        System.out.println("updateLanguageRepresentations");
//        Concept oldConcept = null;
//        Concept instance = new Concept();
//        instance.updateLanguageRepresentations(oldConcept);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVisualRepresentations method, of class Concept.
//     */
//    @Test
//    public void testGetVisualRepresentations() {
//        System.out.println("getVisualRepresentations");
//        Concept instance = new Concept();
//        List<VisualRepresentation> expResult = null;
//        List<VisualRepresentation> result =
//                instance.getVisualRepresentations();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addVisualRepresentation method, of class Concept.
//     */
//    @Test
//    public void testAddVisualRepresentation() {
//        System.out.println("addVisualRepresentation");
//        VisualRepresentation visualRepresentation = null;
//        Concept instance = new Concept();
//        instance.addVisualRepresentation(visualRepresentation);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setVisualRepresentation method, of class Concept.
//     */
//    @Test
//    public void testSetVisualRepresentation() {
//        System.out.println("setVisualRepresentation");
//        List<VisualRepresentation> visualRepresentations = null;
//        Concept instance = new Concept();
//        instance.setVisualRepresentation(visualRepresentations);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVisualRepresentationsEntries method, of class Concept.
//     */
//    @Test
//    public void testGetVisualRepresentationsEntries() {
//        System.out.println("getVisualRepresentationsEntries");
//        Concept instance = new Concept();
//        List<VisualRepresentation> expResult = null;
//        List<VisualRepresentation> result =
//                instance.getVisualRepresentationsEntries();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateVisualRepresentations method, of class Concept.
//     */
//    @Test
//    public void testUpdateVisualRepresentations() {
//        System.out.println("updateVisualRepresentations");
//        Concept oldConcept = null;
//        Concept instance = new Concept();
//        instance.updateVisualRepresentations(oldConcept);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMotoricRepresentations method, of class Concept.
//     */
//    @Test
//    public void testGetMotoricRepresentations() {
//        System.out.println("getMotoricRepresentations");
//        Concept instance = new Concept();
//        List<MotoricRepresentation> expResult = null;
//        List<MotoricRepresentation> result =
//                instance.getMotoricRepresentations();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMotoricRepresentationsEntries method, of class Concept.
//     */
//    @Test
//    public void testGetMotoricRepresentationsEntries() {
//        System.out.println("getMotoricRepresentationsEntries");
//        Concept instance = new Concept();
//        List<MotoricRepresentation> expResult = null;
//        List<MotoricRepresentation> result =
//                instance.getMotoricRepresentationsEntries();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMotoricRepresentations method, of class Concept.
//     */
//    @Test
//    public void testSetMotoricRepresentations() {
//        System.out.println("setMotoricRepresentations");
//        List<MotoricRepresentation> motoricRepresentations = null;
//        Concept instance = new Concept();
//        instance.setMotoricRepresentations(motoricRepresentations);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addMotoricRepresentation method, of class Concept.
//     */
//    @Test
//    public void testAddMotoricRepresentation() {
//        System.out.println("addMotoricRepresentation");
//        MotoricRepresentation motoricRepresentation = null;
//        Concept instance = new Concept();
//        instance.addMotoricRepresentation(motoricRepresentation);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateMotoricRepresentations method, of class Concept.
//     */
//    @Test
//    public void testUpdateMotoricRepresentations() {
//        System.out.println("updateMotoricRepresentations");
//        Concept oldConcept = null;
//        Concept instance = new Concept();
//        instance.updateMotoricRepresentations(oldConcept);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getInfo method, of class Concept.
//     */
//    @Test
//    public void testGetInfo() {
//        System.out.println("getInfo");
//        Concept instance = new Concept();
//        String expResult = "";
//        String result = instance.getInfo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getInfoShort method, of class Concept.
//     */
//    @Test
//    public void testGetInfoShort() {
//        System.out.println("getInfoShort");
//        Concept instance = new Concept();
//        String expResult = "";
//        String result = instance.getInfoShort();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class Concept.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Concept instance = new Concept();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class Concept.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Concept instance = new Concept();
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Concept.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Concept instance = new Concept();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
