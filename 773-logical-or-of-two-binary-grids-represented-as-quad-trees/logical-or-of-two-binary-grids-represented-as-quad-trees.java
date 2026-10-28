class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        // If either subtree represents all 1s, the OR result is all 1s
        if (quadTree1.isLeaf && quadTree1.val) {
            return new Node(true, true, null, null, null, null);
        }

        if (quadTree2.isLeaf && quadTree2.val) {
            return new Node(true, true, null, null, null, null);
        }

        // If the first subtree is a leaf containing 0, return the second
        if (quadTree1.isLeaf) {
            return quadTree2;
        }

        // If the second subtree is a leaf containing 0, return the first
        if (quadTree2.isLeaf) {
            return quadTree1;
        }

        // Recursively calculate OR for all four quadrants
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        // If all four children are leaves with the same value, merge them
        if (topLeft.isLeaf && topRight.isLeaf &&
            bottomLeft.isLeaf && bottomRight.isLeaf &&
            topLeft.val == topRight.val &&
            topLeft.val == bottomLeft.val &&
            topLeft.val == bottomRight.val) {

            return new Node(topLeft.val, true, null, null, null, null);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}