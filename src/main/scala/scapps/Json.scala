package scapps

import dispatch.json._
import Function._
import scalaz.Scalaz._

trait Json[-T] {
  def json(t: T) : JsValue
}

object Json {
  def parseJson(jsonString: String): Either[Exception, JsValue] =
    try {
      Right(Js(jsonString))
    } catch {
      case e: Exception => Left(e)
    }

  def json[T](f: T => JsValue): Json[T] = new Json[T] {
    def json(t: T) = f(t)
  }

  def jsValuer[T] = json((t : T) => JsValue(t))

  implicit val IntJson = jsValuer[Int]

  implicit val JsValueJson = json[JsValue](p => p)

  implicit val StringJson = jsValuer[String]

  implicit val LongJson = jsValuer[Long]

  implicit val FloatJson = jsValuer[Float]

  implicit val DoubleJson = jsValuer[Double]

  implicit val BigIntJson = jsValuer[BigInt]

  implicit val BigDecimalJson = jsValuer[BigDecimal]

  implicit val BoolJson = jsValuer[Boolean]

  implicit val SymbolJson = jsValuer[Symbol]

  implicit val JavaLongJson = json[java.lang.Long](l => JsValue(l.longValue))

  implicit val JavaFloatJson = json[java.lang.Float](l => JsValue(l.floatValue))

  implicit def EitherJson[X, Y](implicit jX: Json[X], jY: Json[Y]) =
    json[Either[X,Y]](e => {
      e.fold(jX.json _, jY.json _)
    })

  implicit def Tuple2Json[Y](implicit jY: Json[Y]) = json[Tuple2[String, Y] with Product](t2 => {
    JsObject(Map(JsString(t2._1) -> jY.json(t2._2)))
  })

  implicit def StringMapJson[A](implicit jA : Json[A]) = json[Map[String, A]](map => {
    JsObject(map.elements.toList.map(pair => (JsString(pair._1) -> jA.json(pair._2))))
  })

  implicit def SymbolMapJson[A](implicit jA : Json[A]) = json[Map[Symbol, A]](map => {
    JsObject(map.elements.toList.map(pair => (JsString(pair._1.name) -> jA.json(pair._2))))
  })

  implicit def DangerousAnyMapJson = json[Map[String, Any]](map => {
    StringMapJson(jsValuer[Any]).json(map)
  })

  implicit def ListJson[A](implicit ja: Json[A]) : Json[List[A]] = json[List[A]](list => {
    JsArray(list map (ja.json(_)))
  })
  //    case xs: List[_] => JsArray(xs.map(JsValue.apply))
  //    case xs: Seq[_] => JsArray(xs.map(JsValue.apply).toList)
}
