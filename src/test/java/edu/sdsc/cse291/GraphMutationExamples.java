package edu.sdsc.cse291;

import static com.google.common.collect.Iterables.getOnlyElement;

import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

/***
 * Programmatically add node and relationship property
 */
public class GraphMutationExamples extends BaseTest {

  /***
   * Programmatically adding node label.
   * 
   * Anyone who has an outgoing 'hasExpertise' edge gets the node label 'Person'
   */
  @Test
  public void addLabel() {
    System.out.println("********\n* Adding labels \n********");
    try (Transaction tx = graphDb.beginTx()) {
      for (Node node : GlobalGraphOperations.at(graphDb).getAllNodes()) {
        if (node.hasRelationship(Direction.OUTGOING,
            DynamicRelationshipType.withName("hasExpertise"))) {
          System.out.println("Adding PERSON label to " + (String) node.getProperty("name"));
          node.addLabel(DynamicLabel.label("PERSON"));
        }
      }
    }
  }

  /***
   * Programmatically updating node properties.
   */
  @Test
  public void modifyNodeProperties() {
    System.out.println("********\n* Modify node properties \n********");
    try (Transaction tx = graphDb.beginTx()) {
      Node amarnath = nodeMap.get("Amarnath");
      amarnath.setProperty("highestDegree", "PhD");
      tx.success();
    }
  }

  /***
   * Programmatically updating relationship properties.
   */
  @Test
  public void modifyRelationshipProperties() {
    System.out.println("********\n* Modify relationship properties \n********");
    try (Transaction tx = graphDb.beginTx()) {
      Node amarnath = nodeMap.get("Amarnath");
      Relationship graduated =
          getOnlyElement(amarnath.getRelationships(DynamicRelationshipType
              .withName("graduatedFrom")));
      graduated.setProperty("year", 1994);
      tx.success();
    }
  }

}
