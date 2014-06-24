Signal/Collect [![Build Status](https://travis-ci.org/uzh/signal-collect/builds)](https://api.travis-ci.org/uzh/signal-collect?branch=master)
========================================================================================================================

Signal/Collect is a framework for computations on large graphs. The model allows to concisely express many iterated and data-flow algorithms, while the framework parallelizes and distributes the computation.

This repository contains pre-release snapshots of the distributed 2.1 version.

How to Compile the Project
--------------------------
Ensure Java 7 is available on the system, verify with "java -version" on the command line.

Install SBT: http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html

Go to the project folder and start SBT on the command line. The output should end with:
"[info] Set current project to signal-collect (in build file:XYZ/signal-collect/)"

To generate a .jar file with dependencies, use the "assembly" command on the SBT prompt.

To generate an Eclipse project, use the "eclipse" command on the SBT prompt.

If SBT throws an "java.lang.OutOfMemoryError: PermGen space" exception, put a file named ".sbtconfig" into the home folder and add this single line to it: "export SBT_OPTS=-XX:MaxPermSize=1024M".


How to Develop in Eclipse
-------------------------
Generate an Eclipse project as described above.

Install the Eclipse-based Typesafe IDE from http://scala-ide.org/download/sdk.html.

Within the IDE, install all the Scala IDE plugins: Help → Install New Software ... → Add → http://download.scala-ide.org/sdk/e38/scala210/stable/site

Ensure that Eclipse uses the Java 7 library and JVM: Preferences → Java → Installed JREs → JRE/JDK 7 should be installed and selected.

Open the Scala project that was generated by SBT with: File → Import... → General → Existing Projects into Workspace → select "signal-collect" folder


Thanks a lot to
---------------
* [University of Zurich](http://www.ifi.uzh.ch/ddis.html) and the [Hasler Foundation](http://www.haslerstiftung.ch/en/home) are generously funding our research on graph processing and the development of Signal/Collect.
* YourKit allows us to use their great [Java/Scala profiler](http://www.yourkit.com/java/profiler/index.jsp).
* GitHub helps us by hosting our [code repositories](https://github.com/uzh/signal-collect).
