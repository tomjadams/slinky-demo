# Slinky Demo Project

Demonstration project of Slinky, Scalaz's HTTP library.

## Setup

1. Install sbt: http://simple-build-tool.googlecode.com/
1. Pull down dependencies.
<pre>
$ sbt update
</pre>
1. This demo currently rely on some unpublished artefacts, to get it:
<pre>
$ # Get sqm: http://github.com/tomjadams/scala-query-migrations/tree/master
$ git clone git://github.com/tomjadams/scala-query-migrations.git
$ sbt update
$ sbt publish-local
$ # Get: http://github.com/szeiger/scala-query/tree/scala-2.7
$ git clone git://github.com/szeiger/scala-query.git
$ git checkout origin/scala-2.7
$ sbt update
$ sbt publish-local
</pre>

You should be good to go!

## Quick run

In one console:
<pre>
$ sbt run
</pre>

In another:

<pre>
$ curl -i http://localhost:8081/api/register -d '{"name":"Slinky Malinky","organisation":"Scattercat"}'
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 109
Server: Jetty(7.0.0.RC2)

{"description" : "Person registration successful.", "name" : "Slinky Malinky", "organisation" : "Scattercat"}
</pre>

## Run tests

<pre>
$ sbt run    # in one console
$ sbt test   # in another
</pre>

## Deploy

Note. This does not currently work as we use newer Jetty libs. You're welcome to have a crack at fixing it.

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

