package archunit.core

import archunit.CleanArchitectureConstantes.EVENTS_PACKAGE
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition

class EventRules {

    @ArchTest
    val eventsShouldHaveNameEndingWithEvent = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(EVENTS_PACKAGE).should().
        haveSimpleNameEndingWith("Event")
        .because("Events are need to ending with Event")
        .allowEmptyShould(true)


}