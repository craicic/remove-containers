# Remove Containers
A small tool wrote in Java. The main idea was to remove 'orphans' containers created by TestContainers library when you
append  `.withReuse(true);`
to the container instance.

### How it works ?
Two hours cron task, It requests docker using ProcessBuilder commands, parse the result with **fasterxml.jackson** then removes the containers if they 
have the label testcontainers set to true.


### How to use ?
Get the repo. Run a `maven clean package`. Run the jar. 

