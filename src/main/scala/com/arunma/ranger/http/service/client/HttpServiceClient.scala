package com.arunma.ranger.http.service.client

import com.softwaremill.sttp._

object HttpServiceClient {

  implicit val backend = HttpURLConnectionBackend()

  def isServiceUp(uriString: String): Boolean = {
    val request = sttp.get(uri"$uriString")
    val response = backend.send(request)
    if (response.code == 200) true else false
  }

  def getServicePaths(uriString: String):List[String]={
    val request = sttp.get(uri"$uriString")
    val response = backend.send(request)
    response.body match {
      case Right(delimitedMethods) => delimitedMethods.split(",").toList
      case Left(a) => {
        println (s"Service URL at ${uriString} returned a Left - $a")
        List()
      }
    }
  }
}
