package otus.chuchalov

import io.circe.generic.auto.exportEncoder
import io.circe.syntax.EncoderOps
import io.circe.{Encoder, Json, ParsingFailure, parser}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import scala.language.postfixOps

object GeoAnalyzer {

  val NONE = "None"
  val BAD_PARAMETERS_ERROR = "Not enough parameters"
  val FIELD_REGION = "region"
  val FIELD_NAME = "name"
  val FIELD_CAPITAL = "capital"
  val FIELD_AREA = "area"
  val FIELD_COMMON = "common"
  val CONTINENT = "Africa"

  def formatter[A](data: A)(implicit encoder: Encoder[A]): String =
    data.asJson.noSpaces

  def main(args: Array[String]): Unit = {
    require(args.length > 1, BAD_PARAMETERS_ERROR)

    val sourcePath = args(0)
    val destPath = args(1)

    val jsonString = Files.readAllLines(Paths.get(sourcePath), StandardCharsets.UTF_8).asScala.mkString
    val parseResult: Either[ParsingFailure, Json] = parser.parse(jsonString)

    case class Country(name: String, capital: String, area: Long)

    parseResult match {
      case Left(parsingError) =>
        throw new IllegalArgumentException(s"Bad json: ${parsingError.message}")
      case Right(json) =>
        Files.write(
          Paths.get(destPath),
          formatter(json
            .asArray
            .get
            .filter(value => (value \\ FIELD_REGION).head.asString.getOrElse(NONE).equals(CONTINENT))
            .map(value => {
              Country(
                ((value \\ FIELD_NAME).head \\ FIELD_COMMON).head.asString.getOrElse(NONE),
                (value \\ FIELD_CAPITAL).head.asArray.get.head.asString.getOrElse(NONE),
                (value \\ FIELD_AREA).head.asNumber.getOrElse(0) ##
              )
            })
            .sortWith((c1, c2) => {
              c1.area > c2.area
            })
            .take(10)).getBytes(StandardCharsets.UTF_8))
    }
  }

}
