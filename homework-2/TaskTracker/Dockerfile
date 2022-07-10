FROM gradle:7.4.0-jdk11-alpine as build
LABEL stage=intermediate

WORKDIR /home/app
COPY . .
RUN gradle --no-daemon --build-cache -x test build

FROM bellsoft/liberica-openjdk-alpine:11
MAINTAINER www.digitalleague.ru

ENV TZ="Europe/Moscow"
ENV JAVA_OPTS="-Duser.timezone=$TZ -Dfile.encoding=UTF-8 -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true"

ENV SPRING_PROFILE="default"
ENV APP_HOME /tasktracker
ENV LOG_FOLDER="$APP_HOME/log"
ENV JAR_NAME=TaskTracker-0.0.1-SNAPSHOT.jar

VOLUME /tmp
VOLUME $APP_HOME
VOLUME $APP_HOME/log

WORKDIR $APP_HOME
COPY --from=build /home/app/build/libs/*.jar ./
RUN chmod -R 777 $APP_HOME

EXPOSE 8080
CMD exec java $JAVA_OPTS \
    -Dlogs.base.dir=$LOG_FOLDER \
    -Dspring.profiles.active=$SPRING_PROFILE \
    -jar $JAR_NAME
