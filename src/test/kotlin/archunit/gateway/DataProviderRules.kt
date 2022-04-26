package archunit.gateway

import archunit.CleanArchitectureConstantes.DATA_PROVIDERS_DATABASES_COLLECTIONS_PACKAGE
import archunit.CleanArchitectureConstantes.DATA_PROVIDERS_DATABASES_REPOSITORY_PACKAGE
import archunit.CleanArchitectureConstantes.DATA_PROVIDERS_INTEGRATIONS_INPUTS_PACKAGE
import archunit.CleanArchitectureConstantes.DATA_PROVIDERS_INTEGRATIONS_OUTPUTS_PACKAGE
import archunit.CleanArchitectureConstantes.DATA_PROVIDERS_KAFKA_PUBLISH_PACKAGE
import archunit.CleanArchitectureConstantes.DATA_PROVIDERS_PACKAGE
import archunit.CleanArchitectureConstantes.PORTS_PACKAGE
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition

class DataProviderRules {

    @ArchTest
    val dataProvidersShouldHaveNameEndingWithDataProvider = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_PACKAGE).should().
        haveSimpleNameEndingWith("DataProvider")
        .andShould().beAnnotatedWith("org.springframework.stereotype.Component")
        .because("Dataproviders are need to ending with DataProvider.")
        .allowEmptyShould(true)


    @ArchTest
    val databaseRepositoryShouldImplementJPARepositoryOrCrudRepositoryOrPagingAndSortingRepository = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_DATABASES_REPOSITORY_PACKAGE).should().
        haveSimpleNameEndingWith("Repository")
        .andShould().beAssignableTo("org.springframework.data.jpa.repository.JpaRepository")
        .orShould().beAssignableTo("org.springframework.data.repository.CrudRepository")
        .orShould().beAssignableTo("org.springframework.data.repository.PagingAndSortingRepository")
        .because("Repositorys are need to extends JpaRepository or CrudRepository or PagingAndSortingRepository.")
        .allowEmptyShould(true)


    @ArchTest
    val databaseRepositoryShouldBeAnInterface = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_DATABASES_REPOSITORY_PACKAGE).should().beInterfaces()
        .because("Repositorys are need to be an Interface.")
        .allowEmptyShould(true)


    @ArchTest
    val dataProvidersShouldOnlyImplementPortInterfaces = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_PACKAGE).should().
        implement(com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage(PORTS_PACKAGE))
        .andShould().beAnnotatedWith("org.springframework.stereotype.Component")
        .because("Dataproviders are need to implementing a port.")
        .allowEmptyShould(true)

    @ArchTest
    val tablesShouldBeAnnotatedByEntityOrTable = ArchRuleDefinition.classes()
        .that()
        .haveSimpleNameEndingWith("Table")
        .should()
        .beAnnotatedWith("javax.persistence.Entity")
        .andShould()
        .beAnnotatedWith("javax.persistence.Table")
        .allowEmptyShould(true)
        .because("Table entities belong to the Dataprovider layer, and those annotations are required to use JPA" +
                "in our architecture.")


    @ArchTest
    val collectionsShouldHaveNameEndingWithCollection =   ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_DATABASES_COLLECTIONS_PACKAGE).should().
        haveSimpleNameEndingWith("Collection")
        .allowEmptyShould(true)
        .because("Entities collections are need to ending with Collection")

    @ArchTest
    val inputsIntegrationShouldHaveNameEndingWithRequest =   ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_INTEGRATIONS_INPUTS_PACKAGE).should().
        haveSimpleNameEndingWith("Request")
        .allowEmptyShould(true)
        .because("Integration inputs are need to ending with Request")

    @ArchTest
    val outputsIntegrationShouldHaveNameEndingWithResponse =   ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_INTEGRATIONS_OUTPUTS_PACKAGE).should().
        haveSimpleNameEndingWith("Response")
        .allowEmptyShould(true)
        .because("Integration outputs are need to ending with Response")


    @ArchTest
    val tablesShouldHaveNameEndingWithTable =  ArchRuleDefinition.classes()
        .that().
        resideInAPackage("..dataproviders.databases.entities..").should().
        haveSimpleNameEndingWith("Table")
        .allowEmptyShould(true)
        .because("Entities tables are need to ending with Table");

    @ArchTest
    val entitiesShouldBeFreeOfCycles = SlicesRuleDefinition.slices()
        .matching("..entity.(*)..")
        .should()
        .beFreeOfCycles()
        .allowEmptyShould(true)
        .because("We should not have entities with cyclical dependencies.")


    val kafkaPublishShouldStartWithPublishAndFinishWithEvent = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_KAFKA_PUBLISH_PACKAGE).should().
        haveSimpleNameEndingWith("Event").andShould().
            haveSimpleNameStartingWith("Publish")
        .allowEmptyShould(true)
        .because("Publish Kafka need to start with Publish and ending with Event")

    val kafkaPublishShouldimplementPort = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(DATA_PROVIDERS_KAFKA_PUBLISH_PACKAGE).should().
        implement(com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage(PORTS_PACKAGE))
        .allowEmptyShould(true)
        .because("Publish Kafka need to be implemented by Port")

}