App Engine Java Guestbook
Copyright (C) 2010-2012 Google Inc.

## Sample guestbook for use with App Engine Java.

Requires [Apache Maven](http://maven.apache.org) 3.1 or greater, and JDK 7+ in order to run.

To build, run

    mvn package

Building will run the tests, but to explicitly run tests you can use the test target

    mvn test

To start the app, use the [App Engine Maven Plugin](http://code.google.com/p/appengine-maven-plugin/) that is already included in this demo.  Just run the command.

    mvn appengine:devserver

For further information, consult the [Java App Engine](https://developers.google.com/appengine/docs/java/overview) documentation.

To see all the available goals for the App Engine plugin, run

    mvn help:describe -Dplugin=appengine

Could come next :
    test angular js
    test api (jersey-test-framework ?, jersey-tests ?, swagger ?)
    test with groovy
    jersey-server-linking ?
    consumer based tests
    test "be liberal in what you accept" (use xpath ?)
    cdi
    use swagger ?
    use graddle ?
rest client with circuit breaker
log aggregation
correlation id to follow calls from one service to another
Domain driven design
node
authentication
spring roo
spring boot


