# How to execute cucumber scenarios in parallel
---

#### Author: Akash Tyagi
#### Date: 31-Dec-2020
#### Reach me out at: akashdktyagi@gmail.com

---
For Video where I explaind the concept: https://youtu.be/jRihi74zJFw

---

### Explanation and Details:

* Clone this project to try to run it by running command ``mvn clean install``. This will execute four features files in parallel. Currently pom sure value config has a setting to run 2 feature files in parallel.
* To run cucumber tests in parallel you need to add below lines in your pom.
* Cucumber does not have anything of its own to run tests in parallel
* Cucumber relies on maven sure fire parallel execution mechanism for parallel execution.
* Since its maven sure fire plugin which is responsible for running tests in parallel, you need to read the official documentation of maven sure fire plugin to play around different combinations
* Maven Sure fire official documentation can be found here: ![Click Here](https://maven.apache.org/surefire/maven-surefire-plugin/examples/fork-options-and-parallel-execution.html)
* Details about the tags in the configuration segment of maven surefire plugin:  (mentioned in the below xml)
    * Value of parallel  tag should be kept as ```method```
    * Thread count represents how many parallel execution you want.
    * By writing parallel tag as ```method```, and thread count as ```2```, two feature files will be executed in parallel.
    * Using above combinations, feature files will be executed in parallel. 
    * You do not necessarily have to execute the scenarios in the same feature file to be executed in parallel. Above approach of running the feature files in parallel is standard approach in nearly all the projects.
    * ```perCoreThreadCount``` should be kept as ```false```. This is used to represent that thread count mentioned above  is total and not per core. If you have dual core machine and this tag is marked as true(by default it is true), then sure fire will exeucte two thread count in each core, i.e. total of 4 parallel execution.
    * ```forkCount``` is used to represent defines the maximum number of JVM processes that maven-surefire-plugin will spawn concurrently to execute the tests. For more details on this check the official documentation. [Click Here](https://maven.apache.org/surefire/maven-surefire-plugin/examples/fork-options-and-parallel-execution.html)
    * Fork count is used so that for each parallel execution, sure fire will trigger a separate JVM so that there is no clash and to achieve level of separation.
```xml
  	<build>
		<plugins>
			<plugin>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>3.0.0-M1</version>
				<configuration>
					<forkCount>3</forkCount>
	    				<reuseForks>true</reuseForks>
					<parallel>methods</parallel>
					<threadCount>2</threadCount>
					<perCoreThreadCount>false</perCoreThreadCount>
					<!-- <useUnlimitedThreads>true</useUnlimitedThreads>-->
					<argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
				</configuration>
			</plugin>
		</plugins>
	</build>

```
