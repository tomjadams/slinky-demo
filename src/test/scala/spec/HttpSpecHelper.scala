package spec

import slinkydemo.view.MimeType
import java.lang.String

sealed trait HttpMethod {
  import com.meterware.httpunit._

  def execute: WebResponse

  final def unary_! = execute
}

sealed trait PayloadableHttpMethod extends HttpMethod {
  def apply(payload: String): PayloadableHttpMethod

  def apply(mimeType: MimeType): PayloadableHttpMethod

  def payload(payload: String): PayloadableHttpMethod

  def as(mimeType: MimeType): PayloadableHttpMethod
}

final case class HttpGetMethod(url: String) extends HttpMethod {
  import com.meterware.httpunit._

  lazy val conversation = new WebConversation() {
    setExceptionsThrownOnErrorStatus(false)
  }
  lazy val request = new GetMethodWebRequest(url)

  override def execute = conversation.getResponse(request)
}

final case class HttpPostMethod(url: String, payload: Option[String], mimeType: Option[MimeType]) extends PayloadableHttpMethod {
  import com.meterware.httpunit._
  import slinkydemo.view.WwwFormUrlEncodedMimeType
  import java.io.InputStream
  import spec.SpecificationHelper._
  import scalaz.Scalaz._
  import scalaz.OptionW._

  lazy val conversation = new WebConversation() {
    setExceptionsThrownOnErrorStatus(false)
  }

  override def execute = {
    val request = new PostMethodWebRequest(url, ~(payload), mimeType | WwwFormUrlEncodedMimeType)
    conversation.getResponse(request)
  }

  def apply(payload: String) = this match {
    case HttpPostMethod(u, p, m) => HttpPostMethod(u, Some(payload), m)
  }

  def apply(mimeType: MimeType) = this match {
    case HttpPostMethod(u, p, m) => HttpPostMethod(u, p, Some(mimeType))
  }

  def payload(payload: String) = apply(payload)

  def as(mimeType: MimeType) = apply(mimeType)
}

sealed trait HttpService {
  def get: HttpMethod

  def post: PayloadableHttpMethod
}

private final case class HttpService_(url: String) extends HttpService {
  override def get = HttpGetMethod(url)

  override def post = HttpPostMethod(url, None, None)
}

object HttpService {
  implicit def StringToHttpService(url: String): HttpService = service(url)

  def service(url: String): HttpService = HttpService_(url)
}
