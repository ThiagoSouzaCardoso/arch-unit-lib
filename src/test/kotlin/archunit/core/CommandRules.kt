package archunit.core

import archunit.CleanArchitectureConstantes.COMMANDS_PACKAGE
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition

class CommandRules {

    @ArchTest
    val commandsShouldHaveNameEndingWithCommand = ArchRuleDefinition.classes()
        .that().
        resideInAPackage(COMMANDS_PACKAGE).should().
        haveSimpleNameEndingWith("Command")
        .because("Commands are need to ending with Command")
        .allowEmptyShould(true)

}