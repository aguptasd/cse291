package edu.sdsc.cse291;

import static com.google.common.io.Resources.getResource;
import static com.google.common.io.Resources.readLines;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.TestGraphDatabaseFactory;

import com.google.common.base.Charsets;

public class BaseTest {

  GraphDatabaseService graphDb;
  Map<String, Node> nodeMap;

  @Before
  public void setup() throws IOException {
    graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase();
    GraphLoader loader = new GraphLoader(graphDb);
    try (Transaction tx = graphDb.beginTx()) {
      nodeMap = readLines(getResource("exampleGraph.tsv"), Charsets.UTF_8, loader);
      tx.success();
    }
  }

  @After
  public void teardown() {
    graphDb.shutdown();
  }

}
