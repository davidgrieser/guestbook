FROM guestbook:dev

ENTRYPOINT [ "java -Xmx300m -Xss512k -Dserver.port=$PORT -jar /app/app.jar" ]
