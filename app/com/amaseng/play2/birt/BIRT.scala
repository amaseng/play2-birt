/*
 * Copyright 2014 AmaSeng Software Sdn. Bhd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amaseng.play2.birt

import org.eclipse.birt.report.engine.api.{HTMLRenderOption, IReportEngineFactory, IReportEngine, EngineConfig}
import org.eclipse.birt.core.framework.Platform
import java.util.logging.Level
import java.io.{File, InputStream, ByteArrayOutputStream, ByteArrayInputStream}
import org.eclipse.core.internal.registry.RegistryProviderFactory
import play.api.{Play, Logger}

class BIRT {

  lazy val engine: IReportEngine = {
    val logDir = Play.current.configuration.getString("birt.log.dir")
    val config = new EngineConfig
    logDir match {
      case Some(logDir) =>
        val logDirFile = new File(logDir)
        if (!logDirFile.exists)
          logDirFile.mkdirs()
        config.setLogConfig(logDir, Level.WARNING)
      case None =>
    }
    config.setEngineHome(".")
    Platform.startup(config)
    val factory = Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY).asInstanceOf[IReportEngineFactory]
    val engine = factory.createReportEngine(config)
    engine.changeLogLevel(Level.WARNING)
    engine
  }

  def start() {
    Logger.info("Starting BIRT...")
    engine
    Logger.info("BIRT started successfully.")
  }

  def stop() {
    Logger.info("Stopping BIRT...")
    try {
      engine.destroy()
      Platform.shutdown()
      RegistryProviderFactory.releaseDefault()
      Logger.info("BIRT stopped successfully.")
    }
    catch {
      case e: Throwable => Logger.info("Error when stopping BIRT: " + e.getMessage)
    }
  }

  def generateReport(templateFile: File, parametersMap: Map[String, AnyRef], dataMap: Map[String, AnyRef]): InputStream = {

    import collection.JavaConverters._

    val template = engine.openReportDesign(templateFile.getAbsolutePath)
    val options = new HTMLRenderOption()
    val outStream = new ByteArrayOutputStream()
    options.setOutputStream(outStream)
    options.setOutputFormat("pdf")
    val task = engine.createRunAndRenderTask(template)
    task.setAppContext(dataMap.asJava)
    task.setRenderOption(options)
    task.setParameterValues(parametersMap.asJava)
    // Deprecated, added to appContext directly
    //for((key, value) <- dataMap)
      //task.addScriptableJavaObject(key, value)
    task.run()
    task.close()
    new ByteArrayInputStream(outStream.toByteArray)
  }

}

object BIRT extends BIRT