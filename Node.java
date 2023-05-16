package AVLTreeee;

class Node {
    int data;
    Node kiri, kanan;
    int level;

    Node(int data) {
        this.data = data;
        level = 1;
    }
}
