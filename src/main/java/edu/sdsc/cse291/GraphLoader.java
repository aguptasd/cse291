package edu.sdsc.cse291;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;

import com.google.common.base.Splitter;
import com.google.common.io.LineProcessor;

public class GraphLoader implements LineProcessor<Map<String, Node>>{

  private static final Logger logger = Logger.getLogger(GraphLoader.class.getName());

  private final GraphDatabaseService graphDb;
  private final Map<String, Node> nodeMap = new HashMap<>();

  public GraphLoader(GraphDatabaseService graphDb) {
    this.graphDb = graphDb;
  }

  @Override
  public Map<String, Node> getResult() {
    return nodeMap;
  }

  private Node getNode(String label) {
    if (!nodeMap.containsKey(label)) {
      Node node = graphDb.createNode();
      node.setProperty("name", label);
      nodeMap.put(label, node);
    }
    return nodeMap.get(label);
  }

  @Override
  public boolean processLine(String line) throws IOException {
    List<String> tuple = Splitter.on('\t').splitToList(line);
    if (3 != tuple.size()) {
      logger.warning("Couldn't process: " + line);
    } else {
      Node subject = getNode(tuple.get(0));
      Node object = getNode(tuple.get(2));
      RelationshipType type = DynamicRelationshipType.withName(tuple.get(1));
      subject.createRelationshipTo(object, type);
    }
    return true;
  }
}
