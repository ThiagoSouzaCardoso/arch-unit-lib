package archunit.core

import archunit.CleanArchitectureConstantes.CORE_PACKAGE
import archunit.CleanArchitectureConstantes.JAVA_PACKAGE
import archunit.CleanArchitectureConstantes.JETBRAINS_PACKAGE
import archunit.CleanArchitectureConstantes.KOTLIN_PACKAGE
import archunit.CleanArchitectureConstantes.LOG_PACKAGE
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition

class CoreRules{

    @ArchTest
    val coreLayerCannotDependOnFrameworks = ArchRuleDefinition.classes()
        .that().resideInAPackage(CORE_PACKAGE)
        .should().onlyDependOnClassesThat().resideInAnyPackage(
            JAVA_PACKAGE,
            KOTLIN_PACKAGE,
            LOG_PACKAGE,
            CORE_PACKAGE,
            JETBRAINS_PACKAGE
        )
        .allowEmptyShould(true)

}