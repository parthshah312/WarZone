import static org.junit.jupiter.api.Assertions.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.Test;

import model.GameMap;
import model.TerritoryDetails;
import model.ValidateMap;


public class ValidateMapTest {

    @Test
    void testCreateSubGraph() {
        GameMap gameMap = new GameMap();
        /* it is check for subgraph*/
        ValidateMap validator = new ValidateMap();
        Graph<TerritoryDetails, DefaultEdge> fullGraph = validator.createGraph(gameMap);
        Graph<TerritoryDetails, DefaultEdge> subGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        TerritoryDetails vertex1 = new TerritoryDetails("Ain","Rhone-Alpes");
        TerritoryDetails vertex2 = new TerritoryDetails("Aisne","Picardie");
        TerritoryDetails vertex3 = new TerritoryDetails("Allier","Auvergne");

        subGraph.addVertex(vertex1);
        subGraph.addVertex(vertex2);
        subGraph.addVertex(vertex3);

        subGraph.addEdge(vertex1, vertex2);
        subGraph.addEdge(vertex2, vertex3);

        assertNotNull(subGraph);
        assertFalse(subGraph.vertexSet().isEmpty());
    }

}

