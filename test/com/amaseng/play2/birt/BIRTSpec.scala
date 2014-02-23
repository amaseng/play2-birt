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
import java.io.File

class BIRTSpec extends Spec {

  object `BIRT ` {

    def `should start, generate report and stop correctly` {
      val birt = new BIRT
      birt.start()
      birt.generateReport(new File("hello_world.rptdesign"), Map.empty, Map.empty)
      birt.stop()
    }

  }

}