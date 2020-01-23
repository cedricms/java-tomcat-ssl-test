if [ ! -d "./webapps" ]; then
  mkdir ./webapps
fi

if [ ! -f "./webapps/tomcat-ssl-test.war" ]; then
  cp ./tomcat-ssl-test/target/tomcat-ssl-test.war ./webapps/tomcat-ssl-test.war
fi

serverFileLocalPath=$(pwd)/tomcat-ssl-test/src/main/resources/server.xml
serverFileTomcatPath=/usr/local/tomcat/conf/server.xml

sslCrtFileLocalPath=$(pwd)/security/ssl.crt
sslCrtFileTomcatPath=/usr/local/tomcat/conf/ssl.crt

sslKeyFileLocalPath=$(pwd)/security/ssl.key
sslKeyFileTomcatPath=/usr/local/tomcat/conf/ssl.key

webappsLocalPath=$(pwd)/webapps
webappsTomcatPath=/usr/local/tomcat/webapps

MSYS_NO_PATHCONV=1 docker run -it -d  -p 80:8080 -p 443:8443 \
    -v ${serverFileLocalPath}:${serverFileTomcatPath} \
    -v ${sslCrtFileLocalPath}:${sslCrtFileTomcatPath} \
    -v ${sslKeyFileLocalPath}:${sslKeyFileTomcatPath} \
    -v ${webappsLocalPath}:${webappsTomcatPath} \
    unidata/tomcat-docker &