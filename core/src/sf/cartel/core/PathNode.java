package sf.cartel.core;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sf.cartel.core.Extensions.Collections;

public class PathNode {
    private List<PathNode> nextNodes = new ArrayList<>();
    private Vector2 position;

    public PathNode(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public PathNode addNode(PathNode node) {
        nextNodes.add(node);
        return node;
    }

    public PathNode addNode(Vector2 node) {
        return addNode(new PathNode(node));
    }


    public void addNodes(List<PathNode> nodes) {
        nextNodes.addAll(nodes);
    }

    public float distanceTo(PathNode other) {
        return Vector2.dst(position.x, position.y, other.getPosition().x, other.getPosition().y);
    }

    public boolean hasNextNode() {
        return !(nextNodes == null || nextNodes.isEmpty());
    }

    public PathNode getRandomNextNode() {
        return Collections.getRandomItem(nextNodes);
    }
}
