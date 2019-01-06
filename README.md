# Technical Test
This project requires Maven to be installed. Installation instructions can be found at https://maven.apache.org/install.html

To build and run this project, run the command 'mvn package' in the root directory of the project.

Once built, the jar can be found in the /target directory.

To run:

Part 1A, to get results from a specific supplier, or multiple suppliers, run the command:

	java -jar filename.jar <--supplier supplier_name> <pickup> <dropoff> [seats]

or for multiple suppliers:

	java -jar filename.jar <--supplier supplier_name1,supplier_name2,...> <pickup> <dropoff> [seats]

'--supplier' can be replaced with the shortened flag '-s'

Part 1B, to get results from all suppliers, run the command:

	java -jar filename.jar <--all> <pickup> <dropoff> [seats]

'--all' can be replaced with the shortened flag '-a'

Part 2, to run the REST API server, use the following command:

	java -jar filename.jar [--restapi]

'--restapi' can be replaced with the shortened flag '-r'

By default, if no argument is given, the REST API service will be started.

The REST API can be accessed through port 8080 on the localhost at /taxi 

e.g. localhost:8080/taxi?\<pickup\>&\<dropoff\>&[seats]

if no seats parameter is given, all vehicle types will be returned

	
