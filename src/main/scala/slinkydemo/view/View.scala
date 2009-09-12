package slinkydemo.view

import xml.Elem

object DemoContent {
  implicit def ContentToHtmlable(content: HtmlContent): Htmlable = Page(content) 
}

final case class DemoContent(title: String, content: Elem) extends HtmlContent {
  override def head = <title>The Scalaz Conference: {title}</title>

  override def body =
    <div id="content">
      <h1>The Scalaz Conference: {title}</h1>
      {content}
    </div>
}
