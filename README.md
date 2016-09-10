# RPA

Grails: 2.4.3
Java JDK: 1.7

`Mark as Test Source` todas as subpastas imediatas de test (não as subpastas das subpastas)

Run configurations:


Grails: dev

$ run-app --stacktrace --verbose

Grails: test

$ test-app -Dgeb.env=chrome functional:cucumber --stacktrace --verbose
