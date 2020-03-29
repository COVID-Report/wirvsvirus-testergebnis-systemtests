package de.wirvsvirus.testresults.systemtests

import de.wirvsvirus.testresults.systemtests.testconfig.TestConstants
import de.wirvsvirus.testresults.systemtests.testconfig.TestSettings
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.specification.ProxySpecification.host
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test


class TestGetHash {

    @Test
    fun `Test GET hashes - all parameters passed`() {

        Given {
            configuredHostAndPortAreUsed()
            johnDoeProvidesHisData()
        } When {
            hashCodeIsRequested()
        } Then {
            statusCode(200)
            body(equalTo("\"" + TestConstants.JOHN_DOE_HASHCODE + "\""))
        }
    }

    private fun RequestSpecification.hashCodeIsRequested() = get("/hashes")


    @Test
    fun `Test GET hashes - birthday in wrong format passed returns error`() {
        Given {
            configuredHostAndPortAreUsed()
            johnDoeProvidesDataWithInvalidBirthday()
        } When {
            hashCodeIsRequested()
        } Then {
            statusCode(500)
        }
    }

    private fun RequestSpecification.johnDoeProvidesDataWithInvalidBirthday(): RequestSpecification {
        param("sampleId", TestConstants.JOHN_DOE_SAMPLE_NO)
        param("name", TestConstants.JOHN_DOE_NAME)
        return param("birthday", "20012-01")
    }

    fun RequestSpecification.johnDoeProvidesHisData(): RequestSpecification {
        param("sampleId", TestConstants.JOHN_DOE_SAMPLE_NO)
        param("name", TestConstants.JOHN_DOE_NAME)
        return param("birthday", TestConstants.JOHN_DOE_BIRTHDAY)
    }
}

