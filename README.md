# BPMN to Graph Converter

This project provides functionality to convert BPMN models into a graph data structure, allowing for easy navigation and pathfinding between nodes in the BPMN flow.

## Features

- Read a BPMN model from a specified URL.
- Save the BPMN model to a local file.
- Convert the BPMN model into a graph representation.
- Find paths between specified nodes in the BPMN graph.

## Prerequisites

- Java 21 or higher
- Gradle

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/avx99/traverse-bpm-diagram
   cd traverse-bpm-diagram
   ```

2. **Build the Project**

   Use Gradle to build the project:

   ```bash
   ./gradlew build
   ```

3. **Configure Dependencies**

   Ensure the following dependencies are included in your `build.gradle` file:

   ```groovy
   dependencies {
       implementation 'org.camunda.bpm.model:camunda-bpm-model-bpmn:<version>'
       implementation("com.fasterxml.jackson.core:jackson-core:<version>")
       implementation("org.camunda.bpm:camunda-engine:<version>")
       implementation("org.reflections:reflections:<version>")
       // Add other necessary dependencies as required
   }
   ```

## Usage

1. **Run the Application**

   You can run the application using the following command, providing the start and end node IDs as command-line arguments:

   ```bash
   java -cp build/libs/your-project-name.jar Main approveInvoice invoiceProcessed
   ```

   Replace `traverse-bpm-diagram.jar` with the actual name of your generated JAR file (update settings.gradle.kts).

2. **Arguments**

   The application requires two arguments:
    - `startNode`: The ID of the starting node in the BPMN process (e.g., `approveInvoice`).
    - `endNode`: The ID of the ending node in the BPMN process (e.g., `invoiceProcessed`).

3. **Example**

   To find the path from `approveInvoice` to `invoiceProcessed`, run:

   ```bash
   java -cp build/libs/your-project-name.jar Main approveInvoice invoiceProcessed
   ```

   The output will display the path between the two specified nodes.

## Code Structure

- **Main.java**: The entry point of the application, responsible for initializing components and executing the conversion process.
- **BpmHttpClient.java**: Handles HTTP requests to retrieve the BPMN model.
- **BpmFileSaver.java**: Saves the BPMN model to a file.
- **BpmConverter.java**: Converts the BPMN model into a graph representation.
- **Graph.java**: Represents the graph data structure with methods to add nodes, edges, and find paths.

