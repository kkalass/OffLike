#!/bin/bash

if [ "$1" = "debug" ] ; then 
    MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4400,server=y,suspend=n" mvn org.mortbay.jetty:jetty-maven-plugin:run ;
else
    mvn org.mortbay.jetty:jetty-maven-plugin:run ;
fi
