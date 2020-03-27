package de.wirvsvirus.testresults.systemtests

import de.wirvsvirus.testresults.systemtests.testconfig.TestConstants
import de.wirvsvirus.testresults.systemtests.testconfig.TestSettings
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.specification.ProxySpecification.host
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test


class TestPostResult {

    @Test
    fun `Test post negative test result`() {
        Given {
            configuredHostAndPortAreUsed()
            callToServiceIsAuthenticated()
            contentType(ContentType.JSON)
            testResultForJohnDoeIsNegative()
        } When {
            resultIsPostedToBackend()
        } Then {
            statusCode(200)
            body("id", equalTo(TestConstants.JOHN_DOE_HASHCODE))
            body("status", equalTo("NEGATIVE"))
            body("notified", `is`(false))
        }
    }

    private fun RequestSpecification.resultIsPostedToBackend() = post("/tests/{id}")

    private fun RequestSpecification.testResultForJohnDoeIsNegative(): RequestSpecification {
        val testResultJson = """
                {
                "status" : "NEGATIVE",
                "contact": "foo@bar.com"
                }
            """
        pathParam("id", TestConstants.JOHN_DOE_HASHCODE)
        return body(testResultJson)
    }

}

