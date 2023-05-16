package AVLTreeee;
import java.util.Scanner;
public class AVLTree {
    Node root;

    int getLevelTree(Node node) {
        if (node == null) {
            return 0;
        }
        return node.level;
    }

    int getKeseimbanganTree(Node node) {
        if (node == null) {
            return 0;
        }
        return getLevelTree(node.kiri) - getLevelTree(node.kanan);
    }

    Node putarKanan(Node y) {
        Node x = y.kiri;
        Node t2 = x.kanan;

        x.kanan = y;
        y.kiri = t2;

        y.level = Math.max(getLevelTree(y.kiri), getLevelTree(y.kanan)) + 1;
        x.level = Math.max(getLevelTree(x.kiri), getLevelTree(x.kanan)) + 1;

        return x;
    }

    Node putarKiri(Node x) {
        Node y = x.kanan;
        Node t2 = y.kiri;

        y.kiri = x;
        x.kanan = t2;

        x.level = Math.max(getLevelTree(x.kiri), getLevelTree(x.kanan)) + 1;
        y.level = Math.max(getLevelTree(y.kiri), getLevelTree(y.kanan)) + 1;

        return y;
    }

    Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.kiri = insert(node.kiri, data);
        } else if (data > node.data) {
            node.kanan = insert(node.kanan, data);
        } else {
            return node;
        }

        node.level = Math.max(getLevelTree(node.kiri), getLevelTree(node.kanan)) + 1;

        int faktorKeseimbangan = getKeseimbanganTree(node);

        if (faktorKeseimbangan > 1 && data < node.kiri.data) {
            return putarKanan(node);
        }

        if (faktorKeseimbangan < -1 && data > node.kanan.data) {
            return putarKiri(node);
        }

        if (faktorKeseimbangan > 1 && data > node.kiri.data) {
            node.kiri = putarKiri(node.kiri);
            return putarKanan(node);
        }

        if (faktorKeseimbangan < -1 && data < node.kanan.data) {
            node.kanan = putarKanan(node.kanan);
            return putarKiri(node);
        }

        return node;
    }

    void printTree(Node node, String indent) {
        if (node != null) {
            printTree(node.kanan, indent + "   ");
            System.out.println(indent + node.data);
            printTree(node.kiri, indent + "   ");
        }
    }

    void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            printPreOrder(node.kiri);
            printPreOrder(node.kanan);
        }
    }

    void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.kiri);
            System.out.print(node.data + " ");
            printInOrder(node.kanan);
        }
    }

    void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.kiri);
            printPostOrder(node.kanan);
            System.out.print(node.data + " ");
        }
    }

    int jumlahLevel(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = jumlahLevel(node.kiri);
            int rightHeight = jumlahLevel(node.kanan);

            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            } else {
                return (rightHeight + 1);
            }
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("===========================================");
            System.out.println("                MENU UTAMA");
            System.out.println("===========================================");
            System.out.println("1. Insert");
            System.out.println("2. Cetak Pohon");
            System.out.println("3. Cetak Pre-order");
            System.out.println("4. Cetak In-order");
            System.out.println("5. Cetak Post-order");
            System.out.println("6. Cetah level");
            System.out.println("7. Cetak semua");
            System.out.println("8. Reset ");
            System.out.println("0. exit");
            System.out.println("===========================================");
            System.out.print("Pilihan: ");
            int command = sc.nextInt();
            int subCommand =0;
            int levels;
            switch (command) {
                
                case 1:
                    System.out.println("===========================================");
                    System.out.println("                Menu Insert");
                    while (subCommand!=2) {
                        System.out.println("===========================================");
                        System.out.println("1. Insert ");
                        System.out.println("2. Kembali");
                        System.out.print("Pilihan: ");
                        subCommand = sc.nextInt();
                        if (subCommand == 1) {
                            System.out.print("Masukkan angka: ");
                            int number = sc.nextInt();
                            tree.root = tree.insert(tree.root, number);
                            System.out.println("");
                            tree.printTree(tree.root, "");
                            System.out.println("");
                            
                        }
                    }
                    break;
                case 2:
                    System.out.println("Pohon : \n");
                    tree.printTree(tree.root, "");
                    System.out.println("");
                    break;
                case 3:
                    System.out.print("Pre-Order : ");
                    tree.printPreOrder(tree.root);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("In-Order : ");
                    tree.printInOrder(tree.root);
                    System.out.println("");
                    break;
                case 5:
                    System.out.print("Post-Order :");
                    tree.printPostOrder(tree.root);
                    System.out.println("");
                case 6:
                    levels = tree.jumlahLevel(tree.root);
                    System.out.println("Jumlah tingkatan/level: " + levels);
                    break;
                case 7:
                    System.out.println("Pohon : \n");
                    tree.printTree(tree.root, "");
                    System.out.println("");
                    System.out.print("Pre-Order    : ");
                    tree.printPreOrder(tree.root);
                    System.out.println("");
                    System.out.print("In-Order     : ");
                    tree.printInOrder(tree.root);
                    System.out.println("");
                    System.out.print("Post-Order   : ");
                    tree.printPostOrder(tree.root);
                    System.out.println("");
                    levels = tree.jumlahLevel(tree.root);
                    System.out.println("Jumlah tingkatan/level: " + levels);
                    break;
                case 8:
                    main(args);
                case 0:
                    System.out.println("Program berakhir.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Opsi tidak dikenal, silakan masukkan lagi.");
            }
        }
    }
}
