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

import org.scalatest._

class ReportFormatSpec extends FunSpec with Matchers {

  describe("ReportFormat") {

    it("apply method should return PdfReportFormat when pdf is passed in") {
      ReportFormat("pdf") shouldBe PdfReportFormat
    }

    it("apply method should return PdfReportFormat when PDF is passed in") {
      ReportFormat("PDF") shouldBe PdfReportFormat
    }

    it("apply method should return PdfReportFormat when pDf is passed in") {
      ReportFormat("pDf") shouldBe PdfReportFormat
    }

    it("apply method should return PdfReportFormat when xls is passed in") {
      ReportFormat("xls") shouldBe XlsReportFormat
    }

    it("apply method should return PdfReportFormat when XLS is passed in") {
      ReportFormat("XLS") shouldBe XlsReportFormat
    }

    it("apply method should return PdfReportFormat when xLs is passed in") {
      ReportFormat("xLs") shouldBe XlsReportFormat
    }
  }

  describe("PdfReportFormat") {

    it("should have format string = pdf") {
      PdfReportFormat.formatString shouldBe "pdf"
    }

    it("should have MIME type = application/pdf") {
      PdfReportFormat.mimeType shouldBe "application/pdf"
    }

  }

  describe("XlsReportFormat") {

    it("should have format string = xls") {
      XlsReportFormat.formatString shouldBe "xls"
    }

    it("should have MIME type = application/vnd.ms-excel") {
      XlsReportFormat.mimeType shouldBe "application/vnd.ms-excel"
    }

  }

}