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

    test js
    test angular
    consumer based tests (swagger ?)
        test "be liberal in what you accept" (use xpath ?)
    test with groovy
    cdi
        http://www.mastertheboss.com/jboss-frameworks/cdi/java-ee-6-cdi-example-application?showall=&start=1
    graddle
    rest client with circuit breaker
    log aggregation
        correlation id to follow calls from one service to another
    Domain driven design
        if jpa annotation defines the structure of the database, then model is not isolated from the infrastructure (though it's not so intrusive)
        maybe we can find a project which propose a domain driven design with hibernate
        find how to persist value objects
        create annotations : @ValueObject, @Factory (adds readability, but can be obtained with javadoc)
    nodejs
    authentication / security over rest services
    crud generation
        spring roo
        lightadmin		
        telosys
		https://sites.google.com/site/telosystutorial/springmvc-jpa-springdatajpa

    architecture rules
        domain driven design
        use async process whenever possible
        dependencies to external services should be restricted to an adapter layer


