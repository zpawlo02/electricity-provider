package com.pawelzielinski;

import com.querydsl.codegen.GenericExporter;
import com.querydsl.codegen.Keywords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Entity;
import java.io.File;

@SpringBootApplication
public class ElectricityProviderApplication {

	public static void main(String[] args) {

		SpringApplication.run(ElectricityProviderApplication.class, args);
		/*GenericExporter exporter = new GenericExporter();
		exporter.setKeywords(Keywords.JPA);
		exporter.setEntityAnnotation(Entity.class);
		exporter.setEmbeddableAnnotation(Embeddable.class);
		exporter.setEmbeddedAnnotation(Embedded.class);
		exporter.setSupertypeAnnotation(MappedSuperclass.class);
		exporter.setSkipAnnotation(Transient.class);
		exporter.setTargetFolder(new File("target/generated-sources/java"));
		exporter.export(ElectricityProviderApplication.class.getPackage());*/
	}

}
