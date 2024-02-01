# Web Crawler

This project is a web application designed to crawl a provided URL and extract images from it. The README provides detailed information about the project's goals, structure, and setup instructions.

## Goal
The main objective is to perform a web crawl on a given URL and extract all images from the web page. The extracted images will be returned as a JSON array of URLs. Jsoup, a basic library for crawling, is included as a Maven dependency in this project.

### Functionality
- **Web Crawler**: Capable of finding all images on the web page(s) it crawls.
- **Crawl Sub-pages**: Ability to crawl sub-pages to discover more images.
- **Multi-threading**: Implemented to perform the crawl on multiple sub-pages simultaneously.
- **Domain Restriction**: Ensures the crawl stays within the same domain as the input URL.
- **Avoid Duplicate Crawls**: Prevents re-crawling of pages that have already been visited.
- **Crawler "Friendly"**: Designed to avoid being banned from the site by regulating crawl frequency.
- **Front-end Development**: Showcases skills in JavaScript, HTML, and/or CSS to enhance the visual appeal of the site.

## Structure
- **ImageFinder Servlet**: Located in `src/main/java/com/eulerity/hackathon/imagefinder/ImageFinder.java`.
- **Main Landing Page**: Found in `src/main/webapp/index.html`, providing instructions and serving as the starting page for the web application.
- **Project Configuration**: `pom.xml` in the root directory contains Maven project configuration details.

## Running the Project
Below are the steps to set up and run the project along with the necessary requirements.

### Requirements
- Maven 3.5 or higher
- Java 8 (specifically, not Java 9+)

### Setup
1. **Build the Project**: Open a terminal window, navigate to the project's root directory, and run:
   ```
   mvn package
   ```
2. **Clean**: To clear the project build, use:
   ```
   mvn clean
   ```
3. **Run the Server**: Start the server with the command:
   ```
   mvn clean test package jetty:run
   ```
   Once the server is running, access `localhost:8080` in your browser to view the `index.html` welcome page.

### ImageFinder Servlet (`ImageFinder.java`)
- Updated to incorporate web crawling functionality for image URL extraction.
- Implemented multi-threading for efficient crawling.
- Added logic to limit crawling to the same domain and prevent re-crawling of pages.

### Frontend (`index.html`)
- Modified to support the new backend functionality.
- Enhanced user interface to handle and display results from the ImageFinder servlet.

### Maven Configuration (`pom.xml`)
- Added dependencies required for web crawling and multi-threading.
- Updated project configuration to accommodate new functionality.

### Testing (`ImageFinderTest` Class)
- Updated to test the new features of the `ImageFinder` servlet.
- Mocked the `getParameter` method to simulate URL input.
- Adjusted assertions to verify non-empty responses, indicating successful image URL extraction.
- Imported JUnit's `Assert` class for assertion functionality.
