package de.wirvsvirus.testresults.systemtests.testconfig

class TestConstants {

    companion object {
        const val JOHN_DOE_HASHCODE = "d5083090c86fcbd33244ed8b6060ef69c93de9582a77f5f4309266a1f8f69990"
        const val JANE_DOE_HASHCODE = "d5083090c86fcbd33244ed8b6060ef69c93de9582a77f5f4309266a1f8f68880"
        const val JOHN_DOE_NAME="John Doe"
        const val JOHN_DOE_SAMPLE_NO = "B000100100"
        const val JOHN_DOE_BIRTHDAY = "2001-12-01"

        const val NEGATIVE_RESULT =  """
                {
                "status" : "NEGATIVE",
                "contact": "foo@bar.com"
                }
            """

        const val POSITIVE_RESULT =  """
                {
                "status" : "POSITIVE",
                "contact": "foo@bar.com"
                }
            """

        const val PENDING_RESULT =  """
                {
                "status" : "PENDING",
                "contact": "foo@bar.com"
                }
            """
    }
}