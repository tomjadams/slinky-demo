# Slinky Demo Project

Demonstration project of Slinky, Scalaz's HTTP library.

## Setup

Pull down dependencies.

<pre>
$ sbt update
</pre>

## Quick run

<pre>
$ sbt run
</pre>

## Run tests

<pre>
$ sbt run    # in one console
$ sbt test   # in another
</pre>

## Deploy

Start Jetty

<pre>
$ sbt jetty-run
</pre>

Have sbt/Jetty listen for changes & redeploy

<pre>
$ sbt ~ prepare-webapp
</pre>

Stop Jetty

<pre>
$ sbt jetty-stop
</pre>
