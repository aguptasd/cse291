package edu.sdsc.cse291;

import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Transaction;

public class TraversalExamples extends BaseTest {

  @Test
  public void depthFirstTraversal() {
    System.out.println("********\n* Depth First Traversal Example \n********");
    try (Transaction tx = graphDb.beginTx()) {
      Node amarnath = nodeMap.get("Amarnath");
      for (Path path : graphDb.traversalDescription().depthFirst().traverse(amarnath)) {
        System.out.println(path);
      }
      tx.success();
    }
  }

  @Test
  public void breadthFirstTraversal() {
    System.out.println("********\n* Breadth First Traversal Example \n********");
    try (Transaction tx = graphDb.beginTx()) {
      Node amarnath = nodeMap.get("Amarnath");
      for (Path path : graphDb.traversalDescription().breadthFirst().traverse(amarnath)) {
        System.out.println(path);
      }
      tx.success();
    }
  }

}
