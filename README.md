# postman2jmx
Postman collection to Jmeter jmx file converter.

### Installation

postman2jmx requires Java8+ and Maven 3+.

- Clone or download the project.
```sh
$ git clone https://github.com/Loadium/postman2jmx.git
```
- Build the project.
```sh
$ cd postman2jmx
$ mvn package
```

### Usage

- After build, go to the Postman2Jmx folder. It is located under the target folder.
```sh
$ cd target/Postman2Jmx
```
- Then execute the following command to convert your postman json collection file to the jmx file.
```sh
$ java -jar my_postman_collection.json my_jmx_file.jmx
```

