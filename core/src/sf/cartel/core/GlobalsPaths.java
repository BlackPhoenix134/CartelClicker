package sf.cartel.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class GlobalsPaths {
    public static PathNode GetShipPath1() {
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
}