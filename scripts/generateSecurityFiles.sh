if [ ! -d "./security" ]; then
  mkdir ./security
fi

openSslSubject="/C=FR/ST=Rhone/L=Lyon/O=CMS/CN=tomcat.example.com/"

MSYS_NO_PATHCONV=1 openssl req -new -newkey rsa:4096 -days 1 -nodes -x509 -subj \
    "${openSslSubject}" -keyout \
    ./security/ssl.key -out ./security/ssl.crt