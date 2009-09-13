package slinkydemo.view

import xml.Elem

final case class ConferenceContent(title: String, content: Elem) extends HtmlContent {
  override def head = <title>Scalaz Conference: {title}</title>

  override def body =
    <div id="content">
      <h1>Scalaz Conference: {title}</h1>
      {content}
    </div>
}
