/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csri.poeticon.praxicon;

import csri.poeticon.praxicon.db.entities.Concept;
import csri.poeticon.praxicon.db.entities.CollectionOfConcepts;
import csri.poeticon.praxicon.db.entities.LanguageRepresentation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import org.apache.xerces.dom.DocumentImpl;
import org.hibernate.internal.jaxb.mapping.orm.ObjectFactory;

/**
 *
 * @author dmavroeidis
 */
public class GeneratePraxiconXSD {
//    public static SchemaOutputResolver scoure;
//    JAXBContext jaxbContext;
    
    public GeneratePraxiconXSD(String suggestedSchemaName) throws JAXBException, IOException 
    {
//        final File parentFolder = new File("new_xsd/.");
//        final String schemaName = suggestedSchemaName;
//        JAXBContext jc = JAXBContext.newInstance(Concept.class);
//        jc.generateSchema(new SchemaOutputResolver() {
//            @Override
//            public Result createOutput(String namespace, String schema) throws IOException
//            {
//                return new StreamResult(new File(parentFolder, schemaName));
//            }
//        });
   
    }
    
    
    public static void main(String args[]) throws JAXBException, IOException
    {
        
        SchemaOutputResolver sour;
        JAXBContext jaxbContext;
        sour = new PraxiconDBOutputResolver();
        jaxbContext = JAXBContext.newInstance(Concept.class);
        
        jaxbContext.generateSchema(sour);
        //TODO: Fix renaming of xml schema files to ones that make sense
        //TODO: Now I get a "SimpleNamespaceResolver contained no localNamespaceURI; 
        //TODO: aborting rename." error.
        //sour.createOutput("http://www.csri.gr", "output.xsd");
    }
}
