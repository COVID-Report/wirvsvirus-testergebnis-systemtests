package de.wirvsvirus.testresults.systemtests

import de.wirvsvirus.testresults.systemtests.testconfig.TestConstants
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test


class TestPostResult {

    @Test
    fun `Post pending test result with correct authentication`() {
        Given {
            configuredHostAndPortAreUsed()
            authenticatedAsPostUser()
            pendingResultForJohnDoeAvailable()
        } When {
            resultIsPostedToBackend()
        } Then {
            statusCode(200)
            body("id", equalTo(TestConstants.JOHN_DOE_HASHCODE))
            body("status", equalTo("PENDING"))
            body("notified", `is`(false))
        }
    }

    @Test
    fun `Post positive test result with correct authentication`() {
        Given {
            configuredHostAndPortAreUsed()
            authenticatedAsPostUser()
            positiveResultForJaneDoeAvailable()
        } When {
            resultIsPostedToBackend()
        } Then {
            statusCode(200)
            body("id", equalTo(TestConstants.JANE_DOE_HASHCODE))
            body("status", equalTo("POSITIVE"))
            body("notified", `is`(false))
        }
    }

    @Test
    fun `Posting result without authentication is rejected`() {
        Given {
            configuredHostAndPortAreUsed()
            pendingResultForJohnDoeAvailable()
        } When {
            resultIsPostedToBackend()
        } Then {
            statusCode(401)
        }
    }

    @Test
    fun `Posting result with wrong authentication is rejected`() {
        Given {
            configuredHostAndPortAreUsed()
            authenticatedAsPostUserUsingWrongPassword()
            pendingResultForJohnDoeAvailable()
        } When {
            resultIsPostedToBackend()
        } Then {
            statusCode(401)
        }
    }

    private fun RequestSpecification.pendingResultForJohnDoeAvailable(): RequestSpecification {
        contentType(ContentType.JSON)
        pathParam("id", TestConstants.JOHN_DOE_HASHCODE)
        return body(TestConstants.PENDING_RESULT)
    }

    private fun RequestSpecification.positiveResultForJaneDoeAvailable(): RequestSpecification {
        contentType(ContentType.JSON)
        pathParam("id", TestConstants.JANE_DOE_HASHCODE)
        return body(TestConstants.POSITIVE_RESULT)
    }

    private fun RequestSpecification.resultIsPostedToBackend() = post("/tests/{id}")
}

