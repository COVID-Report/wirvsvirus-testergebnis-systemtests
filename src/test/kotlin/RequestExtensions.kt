package de.wirvsvirus.testresults.systemtests

import de.wirvsvirus.testresults.systemtests.testconfig.TestSettings
import io.restassured.specification.ProxySpecification
import io.restassured.specification.RequestSpecification

fun RequestSpecification.configuredHostAndPortAreUsed() {
    port(TestSettings.targetPort)
    ProxySpecification.host(TestSettings.targetHost)
}

fun RequestSpecification.authenticatedAsPostUser() {
    auth().basic(TestSettings.postUser, TestSettings.postUserPass)
}

fun RequestSpecification.authenticatedAsPostUserUsingWrongPassword() {
    auth().basic(TestSettings.postUser, TestSettings.postUserPass + "XXX")
}
