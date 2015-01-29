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

sealed trait ReportFormat {
  val formatString: String
  val mimeType: String
}

object PdfReportFormat extends ReportFormat {
  val formatString: String = "pdf"
  val mimeType: String = "application/pdf"
}

object XlsReportFormat extends ReportFormat {
  val formatString: String = "xls"
  val mimeType: String = "application/vnd.ms-excel"
}

object PostscriptReportFormat extends ReportFormat {
  val formatString: String = "postscript"
  val mimeType: String = "application/vnd.cups-ppd"
}

object ReportFormat {
  def apply(formatString: String): ReportFormat =
    formatString.toLowerCase match {
      case "pdf" => PdfReportFormat
      case "xls" => XlsReportFormat
      case "postscript" => PostscriptReportFormat
      case _ => throw new IllegalArgumentException("Format '" + formatString + "' not supported.")
    }
}