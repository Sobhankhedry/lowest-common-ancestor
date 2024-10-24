# Lowest Common Ancestor with JavaFX

## Algorithm Explanation


The LCA algorithm works as follows:

**Recursive Approach:** Given a binary tree and two nodes, the algorithm traverses from the root to find the first node that is an ancestor of both nodes.

## Example


https://github.com/user-attachments/assets/278496d1-a32d-4f09-91c6-44bf6e29578d

**Red nodes:** the two node that are selected to find their LCA.
**Yellow nodes:** the path that the both nodes go through.
**Gray nodes:** the higher common parrents.
**Green node:** the LCA of the two selected nodes.


## Project Description

This project implements the Lowest Common Ancestor (LCA) algorithm using JavaFX for visual representation. The LCA is a fundamental concept in tree-based data structures, where the LCA of two nodes in a tree is the lowest (or deepest) node that has both nodes as descendants. This project provides an intuitive way to explore and visualize this concept.
## Features

- **Visual Representation:** Uses JavaFX to draw and interact with a binary tree.
- **Algorithm Visualization:** Highlights the lowest common ancestor of two selected nodes in the tree.
- **Customizable Tree:** Users can create, add, and delete nodes to build a custom binary tree.
- **showing higher parrents:** it shows the higher parrents of the two slected nodes with gray colour.
## Requirements

- **Java JDK 11 or later** (for JavaFX)
- **JavaFX SDK 11 or later**



### Prerequisites

1. **Java Development Kit (JDK)**
   - Download and install the latest version of JDK from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).
2. **JavaFX SDK**
   - Download and install JavaFX SDK from [JavaFX](https://openjfx.io/).
### Setting up the Project

1. Clone the repository:

   ```bash
    git clone https://github.com/Sobhankhedry/lowest-common-ancestor.git
   ```
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse).
3. Configure JavaFX in your IDE:
- Add the path to your JavaFX SDK in your project settings
- Add the necessary JavaFX modules to your VM options. For example:
bash
```
--module-path "path_to_your_javafx_sdk" --add-modules javafx.controls,javafx.fxml
```
4. Build and run the project using your IDE.
## Usage/Example


1. Adding Nodes:
- In the Code, add nodes to the binary tree
2. Selecting Nodes:
- In the code select the nodes you want find the LCA.
3. Custom Tree Operations:
- You can add, remove, or modify nodes to explore different scenarios and test the LCA algorithm.
- All the possible situations have been considered and done.


## Contributing

Contributions are always welcome!

If you have suggestions, improvements, or want to report issues, feel free to open a pull request or issue.


   
