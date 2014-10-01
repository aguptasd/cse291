package edu.sdsc.cse291;

import org.junit.Test;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.Transaction;

public class AlgorithmExample extends BaseTest {

  @Test
  public void test1() {
    try (Transaction tx = graphDb.beginTx()) { 
      PathFinder<Path> finder = GraphAlgoFactory.allSimplePaths(PathExpanders.forDirection(Direction.OUTGOING), 10);
      for (Path path: finder.findAllPaths(nodeMap.get("Amarnath"), nodeMap.get("Information Systems"))) {
        System.out.println(path);
      }
      tx.success();
    }
  }
  
  @Test
  public void test() {
    try (Transaction tx = graphDb.beginTx()) { 
      PathFinder<Path> finder = GraphAlgoFactory.shortestPath(PathExpanders.forDirection(Direction.OUTGOING), 10);
      for (Path path: finder.findAllPaths(nodeMap.get("Amarnath"), nodeMap.get("Information Systems"))) {
        System.out.println(path);
      }
      tx.success();
    }
  }

}
