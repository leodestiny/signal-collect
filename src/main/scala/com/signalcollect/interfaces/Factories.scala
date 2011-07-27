/*
 *  @author Philip Stutz
 *  
 *  Copyright 2011 University of Zurich
 *      
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *         http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */

package com.signalcollect.interfaces

import com.signalcollect.configuration._
import com.signalcollect.implementations.coordinator._

trait Factory extends Serializable {
  def name: String = this.getClass.getSimpleName.replace("$", "")
}

trait WorkerFactory extends Factory {
  def createInstance(workerId: Int,
                     workerConfig: WorkerConfiguration,
                     numberOfWorkers: Int,
                     coordinator: Any,
                     mapper: VertexToWorkerMapper): Any
}

trait LocalWorkerFactory extends WorkerFactory {
  override def createInstance(workerId: Int,
                              workerConfig: WorkerConfiguration,
                              numberOfWorkers: Int,
                              coordinator: Any,
                              mapper: VertexToWorkerMapper): Worker
}

trait MessageBusFactory extends Factory {
  def createInstance(numberOfWorkers: Int, mapper: VertexToWorkerMapper): MessageBus[Any]
}

trait StorageFactory extends Factory {
  def createInstance: Storage
}
