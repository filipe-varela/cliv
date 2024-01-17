# ARG UID=10001

# RUN useradd \
#     --comment "" \
#     --home-dir "/home" \
#     --shell "/bin/bash" \
#     --uid "${UID}" \
#     kotlinuser

# RUN apt-get update && apt-get install -y \
#     curl zip unzip \
#     git kotlin \
#     && rm -rf /var/lib/apt/lists/* \
#     && apt-get clean \
#     && apt-get autoremove -y \
#     && rm -rf /var/lib/apt/lists/* \
#     && rm -rf /tmp/*
    
# USER kotlinuser

# RUN echo Welcome to KotlinDocker

# # Set the working directory in the container
# # WORKDIR /cliv

# # Copy the build.gradle and settings.gradle files to the container
# # COPY build.gradle settings.gradle ./

# # Copy the source code to the container
# # COPY src ./src

# # Build the application using Gradle
# # RUN gradle build

# # Use a lightweight base image for the final image
# # FROM gradle:latest-alpine

# # RUN apk add --no-cache curl zip unzip git kotlin  

# # Set the working directory in the container
# # WORKDIR /cliv

# Use an official OpenJDK runtime as a parent image
# FROM adoptopenjdk:11-jre-hotspot

# # Use the official Gradle image as the base image
FROM gradle:8.5.0-jdk21

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install Gradle
RUN apt-get update && \
    apt-get install -y gradle kotlin curl zip unzip git

# # Build the application
# RUN gradle build

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variable
# ENV NAME World

# Run app.py when the container launches
CMD ["gradle", "run"]

