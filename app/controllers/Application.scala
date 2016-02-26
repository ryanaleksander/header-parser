package controllers

import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import scala.util.matching.Regex

class Application extends Controller {

  def index = Action { implicit request =>
	  val pattern = new Regex("^.+\\((.+)\\).+$")
	  val os = request.headers.get("User-Agent").get match {
		  case pattern(op) => op
		  case notFound => notFound
	  }
    Ok(Json.obj(
	    "ipaddress" -> request.remoteAddress,
	    "language" -> s"${request.acceptLanguages.head.language}-${request.acceptLanguages.head.country}",
	    "software" -> os
    ))
  }

}
