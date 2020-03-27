package de.wirvsvirus.testresults.systemtests.testconfig

class TestSettings {

    companion object {
        val targetPort = Integer.getInteger("httpPort", 8080)
        val targetHost = System.getProperty("httpHost", "localhost")
        val postUser = System.getProperty("postUser", "postUser")
        val postUserPass = System.getProperty("postUserPass", "postUserPass")
    }

}