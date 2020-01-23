if [ ! -d "./security" ]; then
  mkdir ./security
fi

# Doubled the slashes to avoid Mingw converting the parameters into a path
openssl req -new -newkey rsa:4096 -days 1 -nodes -x509 -subj \
    "//C=FR/ST=Rhone/L=Lyon/O=Unidata/CN=tomcat.example.com" -keyout \
    ./security/ssl.key -out ./security/ssl.crt