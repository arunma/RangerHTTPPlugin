package com.arunma.ranger.http

import java.util
import java.util.{HashMap => JMap}

import com.arunma.ranger.http.service.client.HttpServiceClient
import org.apache.ranger.plugin.client.BaseClient
import org.apache.ranger.plugin.service.{RangerBaseService, ResourceLookupContext}

import scala.collection.JavaConverters._

class RangerServiceHTTP extends RangerBaseService {

  override def validateConfig(): util.Map[String, AnyRef] = {
    if (configs.containsKey("services_list_url")) {
      val serviceUp = HttpServiceClient.isServiceUp(configs.get("services_list_url"))
      if (serviceUp) retSuccessMap() else returnFailureMap()
    }
    else {
      returnFailureMap()
    }
  }

  override def lookupResource(resourceLookupContext: ResourceLookupContext): util.List[String] = {
    val serviceUrl = configs.get("services_list_url")
    HttpServiceClient.getServicePaths(serviceUrl).asJava
  }

  def returnFailureMap() = {
    val errMessage = "Failed, cannot connect to the HTTP Service"
    val retMap = new JMap[String, AnyRef]()
    BaseClient.generateResponseDataMap(false, errMessage, errMessage, null, null, retMap)
    retMap
  }

  def retSuccessMap() = {
    val retMap = new JMap[String, AnyRef]()
    val successMessage = "Connection test successful"
    BaseClient.generateResponseDataMap(true, successMessage, successMessage, null, null,retMap)
    retMap
  }
}
