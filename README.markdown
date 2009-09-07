# Slinky Demo Project

Demonstration project of Slinky, Scalaz's HTTP library.

## Setup

1. Install sbt: http://simple-build-tool.googlecode.com/
1. Pull down dependencies.

<pre>
$ sbt update
</pre>

1. We currently rely on some unreleased code, to get it: 

Get http://github.com/tomjadams/scala-query-migrations/

<pre>
$ sbt update
$ sbt publish-local
</pre>

Get: http://github.com/szeiger/scala-query/tree/scala-2.7

Make sure you're on the scala-2.7 branch

<pre>
$ sbt update
$ sbt publish-local
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
