package de.wirvsvirus.testresults.systemtests

import de.wirvsvirus.testresults.systemtests.testconfig.TestConstants
import de.wirvsvirus.testresults.systemtests.testconfig.TestSettings
import io.restassured.http.ContentType
import io.restassured.specification.ProxySpecification
import io.restassured.specification.RequestSpecification


fun RequestSpecification.configuredHostAndPortAreUsed() {
    port(TestSettings.targetPort)
    ProxySpecification.host(TestSettings.targetHost)
}

fun RequestSpecification.callToServiceIsAuthenticated() {
    auth().basic(TestSettings.postUser, TestSettings.postUserPass)
}
