package controllers;

import models.Person;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.ExecutionResults;
import org.drools.runtime.StatelessKnowledgeSession;

import flexjson.JSONSerializer;
import play.Play;
import play.exceptions.CompilationException;
import play.mvc.Controller;

import java.util.Arrays;
import java.util.List;

public class Application extends DefaultController {

    public static void index() {


    	KnowledgeBuilderConfiguration kBuilderConfiguration = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(null, Play.classloader);
    	
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(kBuilderConfiguration);
        
        kbuilder.add(ResourceFactory.newFileResource("drl/rules.drl"), ResourceType.DRL);
        
        if (kbuilder.hasErrors()) {
            throw new CompilationException(kbuilder.getErrors().toString());
        }

        kbuilder.getKnowledgePackages();
        
        KnowledgeBaseConfiguration kBaseConfiguration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration(null, Play.classloader);

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfiguration);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
//        ksession.addEventListener(new DebugAgendaEventListener());
//        ksession.addEventListener(new DebugWorkingMemoryEventListener());

        ExecutionResults bresults =
                ksession.execute(CommandFactory.newBatchExecution( Arrays.asList(
                        CommandFactory.newInsertElements(Person.findAll())
                )));

        render(bresults);
    }

    public static void testeJson(Long id) {
    	
    	List<Person> persons = Person.all().fetch();
    	
    	JSONSerializer serializer = new JSONSerializer();
    	serializer.include("name", "pets.name", "pets.age", "pets.type.name").exclude("*");
    	
    	renderJSON(persons, serializer);
    }

}