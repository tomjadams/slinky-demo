package scalaz.http

import org.specs._

object ApiTests extends Specification("JSON API Tests") {
  import _root_.scapps.{Json, Id}, Json._, Id._
  import scalaz.http.slinky.view.JsonMimeType
  import spec.HttpService._
  import scalaz.http.response._, Status._

  lazy val registerEndpoint = "http://localhost:8081/api/register" post JsonMimeType
  lazy val registerPayload = Map(
      'person-> "Tom Adams",
      'organisation-> "mogeneration"
  lazy val successfulRegistration = Map(
      'description -> "Person registration successful.",
      'person-> "Tom Adams",
      'organisation-> "mogeneration").jsonString
  lazy val invalidJson = Map('description -> "Person registration failed.", 'error -> "Invalid JSON payload.").jsonString

  "A person that wants to register" can {
    "POST invalid JSON and get back a 400" in {
      val response = !(registerEndpoint payload "{invalid,}")
      response.getResponseCode must be equalTo BadRequest
      response.getText.trim must be equalTo invalidJson
    }

    "POST a JSON message containing the person's details" in {
      val response = !(registerEndpoint payload registerPayload)
      response.getResponseCode must be equalTo OK
      response.getText.trim must be equalTo successfulRegistration
    }
  }
}
