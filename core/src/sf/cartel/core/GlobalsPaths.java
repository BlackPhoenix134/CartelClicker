package sf.cartel.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.core.Math.GoodMath;

public abstract class GlobalsPaths {
    public static PathNode getShipPath1() {
        PathNode initialNode = new PathNode(new Vector2(-376.016f, 117.082855f));

        PathNode branchNode =
                initialNode.addNode(new Vector2(-321.97775f, 5.628961f))


                        .addNode(new Vector2(-32.648075f, -210.52396f))
                        .addNode(new Vector2(228.53659f, -352.37433f))
                        .addNode(new Vector2(455.94754f, -394.0288f))
                        .addNode(new Vector2(486.344f, -242.04623f))
                        .addNode(new Vector2(468.33124f, -32.648113f));

        PathNode branchEndNode = new PathNode( new Vector2(52.91244f, 400.7835f));
        branchNode.addNode(new Vector2(615.81067f, 137.34717f))
                .addNode(new Vector2(564.024f, 410.91574f))
                .addNode(branchEndNode);

        branchNode.addNode(new Vector2(260.05896f, 104.6991f))
                .addNode(new Vector2(-37.15126f, 222.90771f))
                .addNode(branchEndNode);

        branchEndNode
                .addNode(new Vector2(-352.37427f, 450.31863f))
                .addNode(new Vector2(-559.5208f, 305.09082f))
                .addNode(initialNode);
        return initialNode;
    }

    public static List<PathNode> getPlaneNodes() {
        List<PathNode> startNodes = new ArrayList<PathNode>();
       startNodes.add(new PathNode(
               GlobalsMapPolygon.createBelizePolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createCostaRicaPolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createElSalvadorPolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createHondurasPolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createJamaycaPolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createNicaraguaPolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createPanamaPolygon().getCenter()));
       startNodes.add(new PathNode(GlobalsMapPolygon.createQuakamolePolygon().getCenter()));

         for(PathNode node1 : startNodes) {
             for(PathNode node2 : startNodes) {
                if(node1 != node2)
                    node1.addNode(node2);
             }
         }

        return startNodes;
    }

    public static List<PathNode> getPlaneNodesOffset() {
        List<PathNode> startNodes = new ArrayList<PathNode>();
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createBelizePolygon().getCenter(), 5)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createCostaRicaPolygon().getCenter(), 15)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createElSalvadorPolygon().getCenter(), 5)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createHondurasPolygon().getCenter(), 20)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createJamaycaPolygon().getCenter(), 5)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createNicaraguaPolygon().getCenter(), 20)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createPanamaPolygon().getCenter(), 10)));
        startNodes.add(new PathNode(GoodMath.randomOffset(GlobalsMapPolygon.createQuakamolePolygon().getCenter(), 20)));

        for(PathNode node1 : startNodes) {
            for(PathNode node2 : startNodes) {
                if(node1 != node2)
                    node1.addNode(node2);
            }
        }

        return startNodes;
    }

    public static List<PathNode> getShipNodes() {
        List<PathNode> startNodes = new ArrayList<PathNode>();

        PathNode node = new PathNode(new Vector2(-132.24178f, 245.75986f));
        startNodes.add(node);
        node = node.addNode(new Vector2(-79.55583f, 250.8258f));
        node = node.addNode(new Vector2(-4.57959f, 211.31131f));
        node = node.addNode(new Vector2(16.697418f, 147.48022f));

        node = new PathNode(new Vector2(16.697418f, 147.48022f));
        startNodes.add(node);
        node = node.addNode(new Vector2(-4.57959f, 211.31131f));
        node = node.addNode(new Vector2(-79.55583f, 250.8258f));
        node = node.addNode(new Vector2(-132.24178f, 245.75986f));

        node = new PathNode(new Vector2(58.238266f, 12.725691f));
        startNodes.add(node);
        node = node.addNode(new Vector2(145.37279f, -62.25051f));
        node = node      .addNode(new Vector2(194.006f, -116.9629f));
        node = node       .addNode(new Vector2(237.57326f, -181.80719f));

        node = new PathNode(new Vector2(237.57326f, -181.80719f));
        startNodes.add(node);
        node = node.addNode(new Vector2(194.006f, -116.9629f));
        node = node.addNode(new Vector2(145.37279f, -62.25051f));
        node = node.addNode(new Vector2(58.238266f, 12.725691f));

        node = new PathNode(new Vector2(237.57326f, -181.80719f));
        startNodes.add(node);
        node = node.addNode(new Vector2(-38.01497f, -156.47739f));
        node = node.addNode(new Vector2(-87.66136f, -125.06841f));
        node = node.addNode(new Vector2(-158.5848f, -68.32968f));
        node = node.addNode(new Vector2(-197.08607f, -14.630497f));
        node = node.addNode(new Vector2(-202.15202f, 20.831215f));

        node = new PathNode(new Vector2(-202.15202f, 20.831215f));
        startNodes.add(node);
        node = node.addNode(new Vector2(-197.08607f, -14.630497f));
        node = node.addNode(new Vector2(-158.5848f, -68.32968f));
        node = node.addNode(new Vector2(-87.66136f, -125.06841f));
        node = node.addNode(new Vector2(-38.01497f, -156.47739f));
        node = node.addNode(new Vector2(237.57326f, -181.80719f));


        node = new PathNode(new Vector2(401.7103f, -279.07358f));
        startNodes.add(node);
        node = node.addNode(new Vector2(436.15878f, -250.70422f));
        node = node.addNode(new Vector2(451.3567f, -145.33224f));
        node = node.addNode(new Vector2(445.2775f, 5.6333294f));
        node = node.addNode(new Vector2(398.67078f, 152.54616f));
        node = node.addNode(new Vector2(372.3277f, 228.53558f));

        node = new PathNode(new Vector2(372.3277f, 228.53558f));
        startNodes.add(node);
        node = node.addNode(new Vector2(398.67078f, 152.54616f));
        node = node.addNode(new Vector2(445.2775f, 5.6333294f));
        node = node.addNode(new Vector2(451.3567f, -145.33224f));
        node = node.addNode(new Vector2(436.15878f, -250.70422f));
        node = node.addNode(new Vector2(401.7103f, -279.07358f));

        node = new PathNode(new Vector2(302.41754f, 245.75986f));
        startNodes.add(node);
        node = node.addNode(new Vector2(51.145905f, 230.56198f));
        node = node.addNode(new Vector2(-123.12309f, 296.4194f));

        node = new PathNode(new Vector2(-123.12309f, 296.4194f));
        startNodes.add(node);
        node = node.addNode(new Vector2(51.145905f, 230.56198f));
        node = node.addNode(new Vector2(302.41754f, 245.75986f));
        return startNodes;
    }
}
