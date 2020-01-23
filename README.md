# java-tomcat-ssl-test
A basic test to verify trusted certificats in Java.

## Useful scripts
### Security
#### Generate security files
`./scripts/generateSecurityFiles.sh`

#### Verify certificate
`./scripts/readCrt.sh`

###Run test
#### Build project
`./mvnw clean install`
#### Run Tomcat
`docker pull unidata/tomcat-docker`

`./scripts/runTomcat.sh`
