package edu.sdsc.cse291;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.neo4j.visualization.graphviz.GraphvizWriter;
import org.neo4j.walk.Walker;

public class GraphVisualizationExample extends BaseTest {

  /***
   * Example of generating a Graphviz DOT file.
   * 
   * @throws IOException
   */
  @Test
  public void GraphvizExample() throws IOException {
    GraphvizWriter writer = new GraphvizWriter();
    writer.emit(new File("target/graph.dot"), Walker.fullGraph(graphDb));
  }

}
