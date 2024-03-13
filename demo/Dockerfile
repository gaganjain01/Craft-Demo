#STAGE 1
#Building from base image 17.0.4.1_1-jre and providing it an alias builder
FROM eclipse-temurin:17.0.4.1_1-jre as builder
#Creating a work directory extracted
WORKDIR extracted
#Adding jar file from target folder into the container folder and calling it as app.jar
ADD target/*.jar app.jar
#Extract different layers(dependencies, springboot loader, application and snapshot dependencies) from jar file
#Stores extracted layers in extracted folder we created in step 2
RUN java -Djarmode=layertools -jar app.jar extract

#STAGE 2
#Copy all layers from extracted folder to another directory application here
#If there is a change in dependencies folder then only that part is copies and build
FROM eclipse-temurin:17.0.4.1_1-jre
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

#Exposing port 8080
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]