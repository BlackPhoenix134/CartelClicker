package sf.cartel.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
}
