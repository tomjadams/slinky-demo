# Slinky Demo Project

Demonstration project of Slinky, Scalaz's HTTP library.

## Setup

Pull down dependencies.

$ sbt update

## Quick run

$ sbt run

## Deploy

Start Jetty
$ sbt jetty-run

Have sbt/Jetty listen for changes & redeploy
$ sbt ~ prepare-webapp

Stop Jetty
$ sbt jetty-stop
