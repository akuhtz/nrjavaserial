#About

This is a fork of the RXTX library with a focus on ease of use and the ability to embed in other libraries. 

##Some of the features we have added##

A simplified serial port object called NRSerialPort, see the Wiki for an example...

Self deployment of native libraries ( all native code is stored inside the jar and deployed at runtime). No more manual install of native code.

Arm Cortex support (Gumstix)

Android Support (requires a rooted phone to access the serial hardware)

Single makefile compile (simplifies the compilation of project binaries)

Ant build script for jar creation

Removal of partially implemented code to streamline the lib for just serial port access

Full Eclipse integration, for testing application code against sources.

##And a bunch of bug fixes##

Fixed the memory access error that causes OSX to crash the JVM when serial.close() is called

Fixed the windows serial port zombie bind that prevents re-accessing serial ports when exiting on an exception

Fixed erroneous printouts of native library mis-match

# Building Jar

Checkout the repository.

$cd nrjavaserial/nrjavaserial

$ant

The ready to deploy .jar file will be found in the target/ directory. 

#Building Native Code

Native code is built using the Makefile found in nrjavaserial/nrjavaserial . After the native code is built, the .jar is rebuilt. 

$cd nrjavaserial/nrjavaserial/

$make windows #This will build the windows binaries. This will attempt to build the 64 and 32 bit binaries. 

$make wine #This will build the windows binaries on Linux

$make linux #This will attempt to build both the 32 and 64 bit Linux binaries

$make linux32 or $make linux64 #This will attempt to build 32 or 64 bit Linux binaries

$make arm #This will attempt to build the binaries for all the supported ARM flavors

$make ppc #This will attempt to build the PPC binaries. 

$make osx #This will attempt to build the OSX binaries. 


#Windows Builds

Download mingw64: http://tdm-gcc.tdragon.net/

#This is how to use NRSerialPort objects

NRSerialPort serial = new NRSerialPort("COM3", 115200);                          

serial.connect();

DataInputStream ins = new DataInputStream(serial.getInputStream());

DataOutputStream outs = new DataOutputStream(serial.getOutputStream());

byte b = ins.read();

outs.write(b);

serial.disconnect(); 

#Release Build with Maven

* Don't use the latest version of maven-release-plugin because MRELEASE-875 causes problems. <br>
  I ended up using version 2.3.2 and this worked for me under Windows 7.

* Add the following to git config: <br>
  git config status.displayCommentPrefix true
 
* Add git binaries to your PATH.

* Use maven-3.0.5

* Do not use the <gpg.useagent> property. Add the password for the maven-gpg-plugin manually.

* Steps:<br>
  mvn release:prepare -DdryRun <br>
  mvn deploy &nbsp;&nbsp;&nbsp;   <i> just to make sure deployment works</i> <br>
  
  mvn release:clean<br>
  mvn release:prepare -Dusername=&lt;your_github_username&gt; -Dpassword=&lt;your_password&gt;<br>
  mvn release:perform -Dusername=&lt;your_github_username&gt; -Dpassword=&lt;your_password&gt;<br>

  
