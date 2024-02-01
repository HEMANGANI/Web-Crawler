# Web Crawler
This project is a web application whose goal is to crawl a provided URL and pick out the images from it. This README will provide more information about the goals of the project, its structure, and setup instructions.

## Goal
The goal is to perform a web crawl on a URL string provided by the user. From the crawl, we will need to parse out all of the images on that web page and return a JSON array of strings that represent the URLs of all images on the page. [Jsoup](https://jsoup.org/) is a great basic library for crawling and is already included as a maven dependency in this project.

### Functionality
- Web crawler that can find all images on the web page(s) that it crawls.
- Crawl sub-pages to find more images.

- Implement multi-threading so that the crawl can be performed on multiple sub-pages at a time.
- Keep the crawl within the same domain as the input URL.
- Avoid re-crawling any pages that have already been visited.

- Made crawler "friendly" - try not to get banned from the site by performing too many crawls.
- Show off front-end dev skills with Javascript, HTML, and/or CSS to make the site look more engaging.

## Structure
The ImageFinder servlet is found in `src/main/java/com/eulerity/hackathon/imagefinder/ImageFinder.java`. 

The main landing page for this project can be found in `src/main/webapp/index.html`. This page contains more instructions and serves as the starting page for the web application. 

Finally, in the root directory of this project, you will find the `pom.xml`. This contains the project configuration details used by maven to build the project.

## Running the Project
Here we will detail how to setup and run this project so you may get started, as well as the requirements needed to do so.

### Requirements
Before beginning, make sure you have the following installed and ready to use
- Maven 3.5 or higher
- Java 8
  - Exact version, **NOT** Java 9+ - the build will fail with a newer version of Java

### Setup
To start, open a terminal window and navigate to wherever you unzipped to the root directory. To build the project, run the command:

>`mvn package`

If all goes well you should see some lines that end with "BUILD SUCCESS". When you build your project, maven should build it in the `target` directory. To clear this, you may run the command:

>`mvn clean`

To run the project, use the following command to start the server:

>`mvn clean test package jetty:run`

You should see a line at the bottom that says "Started Jetty Server". Now, if you enter `localhost:8080` into your browser, you should see the `index.html` welcome page! If all has gone well to this point, you're ready to begin!

### ImageFinder Servlet (`ImageFinder.java`)
- Updated to include web crawling functionality for extracting image URLs.
- Implemented multi-threading for efficient crawling of multiple sub-pages.
- Added logic to restrict crawling within the same domain and avoid re-crawling pages.

### Frontend (`index.html`)
- Modified to support the new backend functionality.
- Updated the user interface to handle and display the results from the ImageFinder servlet.

### Maven Configuration (`pom.xml`)
- Added new dependencies required for web crawling and multi-threading.
- Updated project configuration to align with the new functionality.

### Testing (`ImageFinderTest` Class)
- Updated to test the new functionality of the `ImageFinder` servlet.
- Added mocking for `getParameter` method to simulate URL input.
- Modified assertions to check for non-empty responses, indicating successful image URL extraction.
- Included JUnit's `Assert` class import for enabling assertions.
