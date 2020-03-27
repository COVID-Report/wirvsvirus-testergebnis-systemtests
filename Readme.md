## A simple test suite for wirvsvirus-testresults-backend

Usage:


```bash
 ./gradlew test -DpostUser=92104ab6-d02f-45b1-b0ad-9f40d56297b8 -DpostUserPass=d5439065-f3f6-40d7-870a-425895172eb
```

Parameters to set with `-D`

* postUser, postUserPass: Credentials for user to post data
* httpHost, httpPort: host and port to connect to, defaults to locahost:8080