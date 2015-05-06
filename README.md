monty-hall-simulation
=====================

A program that verifies that it is statistically beneficial to switch doors in the Monty Hall problem.

###Try it!
To try it out, run the following command lines (except the '$'s) in a terminal window:

        $ git clone https://github.com/mikost/monty-hall-simulation
        $ cd monty-hall-simulation
        $ mvn clean package
        $ mvn exec:java -Dexec.mainClass=name.mikkoostlund.montyhallsimulation.MontySimMain

####Example output:
        [INFO] Scanning for projects...
        [WARNING] The POM for org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 is missing, no dependency information  
        available
        [WARNING] Failed to retrieve plugin descriptor for org.eclipse.m2e:lifecycle-mapping:1.0.0: Plugin  
        org.eclipse.m2e:lifecycle-mapping:1.0.0 or one of its dependencies could not be resolved: Failure to find  
        org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 in https://repo.maven.apache.org/maven2 was cached in the local  
        repository, resolution will not be reattempted until the update interval of central has elapsed or updates  
        are forced
        [INFO]                                                                         
        [INFO] ------------------------------------------------------------------------
        [INFO] Building Monty Hall simulation 0-SNAPSHOT
        [INFO] ------------------------------------------------------------------------
        [WARNING] The POM for org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 is missing, no dependency information  
        available
        [WARNING] Failed to retrieve plugin descriptor for org.eclipse.m2e:lifecycle-mapping:1.0.0: Plugin  
        org.eclipse.m2e:lifecycle-mapping:1.0.0 or one of its dependencies could not be resolved: Failure to find  
        org.eclipse.m2e:lifecycle-mapping:jar:1.0.0 in https://repo.maven.apache.org/maven2 was cached in the local  
        repository, resolution will not be reattempted until the update interval of central has elapsed or updates  
        are forced
        [INFO] 
        [INFO] --- exec-maven-plugin:1.4.0:java (default-cli) @ monty-hall-simulation ---
        The simulation was run 1000 times.
        Using the "switch door strategy", the contestant won 670 times.
        Using the "keep door strategy", the contestant won 317 times.
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
        [INFO] Total time: 0.798 s
        [INFO] Finished at: 2015-05-06T15:49:42+02:00
        [INFO] Final Memory: 12M/212M
        [INFO] ------------------------------------------------------------------------
