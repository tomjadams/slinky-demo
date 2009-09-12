package slinkydemo.view

// TODO Move these to scapps
// TODO Add JSON to ContentTypeResolver. Add these also.
// TODO Use javax.activation.mimteype

object MimeType {
  implicit def MimeTypeToString(m: MimeType): String = m.value
}

sealed trait MimeType {
  val value: String
}

case object JsonMimeType extends MimeType {
  override val value = "application/json"
}

case object PlainTextMimeType extends MimeType {
  override val value = "text/plain"
}

case object HtmlMimeType extends MimeType {
  override val value = "text/html"
}

case object WwwFormUrlEncodedMimeType extends MimeType {
  override val value = "application/x-www-form-urlencoded"
}
