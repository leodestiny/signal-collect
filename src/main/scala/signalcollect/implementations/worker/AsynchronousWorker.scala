/*
 *  @author Philip Stutz
 *  
 *  Copyright 2010 University of Zurich
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

package signalcollect.implementations.worker

import signalcollect.api.Factory._
import signalcollect.interfaces._
import java.util.concurrent.TimeUnit
import java.util.concurrent.BlockingQueue
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedHashSet

class AsynchronousWorker(
  workerId: Int,
  mb: MessageBus[Any, Any],
  messageInboxFactory: QueueFactory,
  storageFactory: StorageFactory)
  extends AbstractWorker(workerId, mb, messageInboxFactory, storageFactory) {

  def handlePauseAndContinue {
    if (shouldStart) {
      shouldStart = false
      isPaused = false
      sendStatusToCoordinator
    } else if (shouldPause) {
      shouldPause = false
      isPaused = true
      sendStatusToCoordinator
    }
  }

  def handleIdling {
    handlePauseAndContinue
    if (isConverged || isPaused) {
      processInboxOrIdle(idleTimeoutNanoseconds)
    } else {
      processInbox
    }
  }

  def run {
    while (!shouldShutdown) {
      handleIdling
      // While the computation is in progress, alternately check the inbox and collect/signal
      if (!isPaused) {
        vertexStore.toSignal.foreach(vertex => signal(vertex))
        vertexStore.toCollect.foreachWithSnapshot(vertex => { processInbox; if (collect(vertex)) { signal(vertex) } }, () => false)
      }
    }
  }
}