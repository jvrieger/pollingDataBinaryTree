/* Name: Julia Rieger
 * File: LinkedBinaryTree.java
 * Desc:
 *
 * A Linked Binary Sorted Tree that implements a Binary Tree
 * such that generic objects implementing Comparabl can be inserted into
 * tree, one instance of LinkedBinaryTree will be made to hold
 * all PollingData objects, and update accordingly
 */

public class LinkedBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {

    private int size; //number of nodes in bst
    private Node<E> root; //Node in root position of bst, null if no root

    /**
     * private Node class to create Node objects to hold generic objects and populate bst
     */
    private class Node<E> {
	private E data;
	private Node<E> left;
	private Node<E> right;
	
	public Node(E data, Node<E> left, Node<E> right) {
	    this.data = data;
	    this.left = left;
	    this.right = right;
	}
	public E getData() {	
	    return this.data;
	}
	public Node<E> getLeft() { 
	    return this.left;
	}
	public Node<E> getRight() {
	    return this.right;
	}
	public void setData(E data) {
	    this.data = data;
	}
	public void setLeft(Node<E> left) {
	    this.left = left;
	}
	public void setRight(Node<E> right) {
	    this.right = right;
	}
	public boolean isLeaf() {
	    return this.left == null && this.right == null;
	}
    }

    /**
     * returns element in the root of the bst
     * @return E element in root position
     */
    public E getRootElement() {
	return this.root.getData();
    }

    /**
     * returns the size of the bst in terms of nodes,
     * size is incremented every time insert is executed
     * and decremented every time remove is executed (true)
     * @return int of the number of nodes bst contains
     */
    public int size() {
	return this.size;
    }

    /**
     * returns whether bst is empty or contains at least one node
     * @return boolean true if bst is empty, false otherwise
     */
    public boolean isEmpty() {
	return this.size == 0;
    }
    
    /**
     * returns whether the bst contains an element
     * @param E element to be searched for in bst
     * @return boolean true if bst contains element, false otherwise 
     */
    public boolean contains(E element) {
	return containsRec(root, element);
    }

    private boolean containsRec(Node<E> root, E key) {
	if (root == null) {
	    return false;
	}
	else if (root.getData().compareTo(key) == 0) {
	    return true;
	}
	else if (root.getData().compareTo(key) == 1) {
	    return containsRec(root.getLeft(), key);
	}
	else {
	    return containsRec(root.getRight(), key);
	}   
    }
    
    /**
     * inserts the PollingData object in order
     * @param E element of PollingData object to be inserted
     */
    public void insert(E element) {
        root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> root, E key) {
	if (root == null) {
	    Node<E> rt = new Node<E>(key, null, null);
	    size++;
	    return rt;
	}
	else if (root.getData().compareTo(key) == 0) {
	    root.setData(key);
	    return root;
	}
	else if (root.getData().compareTo(key) > 0) {
	    root.setLeft(insertRec(root.getLeft(), key));
	}
	else {
	    root.setRight(insertRec(root.getRight(), key));
	}
	return root;
    }

    /**
     * removes an element from a tree
     * @return boolean true if element existed and was removed
     * and false if element didn't exist in tree
     */
    public boolean remove(E element) {
	if (!contains(element)) {
	    return false;
	}
	else {
	    removeRec(root, element);
	    return true;
	}
    }

    private Node<E> removeRec(Node<E> root, E key) {
	if (root == null) {
	    return null;
	}
	if (root.getData().compareTo(key) == 1) {
	    root.setLeft(removeRec(root.getLeft(), key));
	}
	else if (root.getData().compareTo(key) == -1) {
	    root.setRight(removeRec(root.getRight(), key));
	}
	else {
	    if (root.getLeft() == null) {
		return root.getRight();
	    }
	    else if (root.getRight() == null) {
		return root.getLeft();
	    }
	    else {
		root.setData(minKey(root.getRight()));
		root.setRight(removeRec(root.getRight(), root.getData()));
     	    }
	}
	return root;
    }

    private E minKey(Node<E> root) {
	if (root.getLeft() == null) {
	    return root.getData();
	}
	else {
	    return minKey(root.getLeft());
	}
    }
    
    /**
     * returns the nodes of the binary tree in
     * in-order traversal order, left tree, root, right tree
     * @return String the format of in order tree 
     */
    public String toStringInOrder() {
	return toStringInOrderRec(root);
    }

    private String toStringInOrderRec(Node<E> root) {
	if (root == null) {
	    return "";
	}
	return toStringInOrderRec(root.getLeft()) + root.getData() + " " + toStringInOrderRec(root.getRight());
    }

    /**
     * returns the nodes of the binary tree in
     * pre-order traversal order, root, left tree, right tree
     * @return String the format of pre order tree 
     */
    public String toStringPreOrder() {
	return toStringPreOrderRec(root);
    }

    private String toStringPreOrderRec(Node<E> root) {
	if (root == null) {
	    return "";
	}
	return root.getData() + " " + toStringPreOrderRec(root.getLeft()) + toStringPreOrderRec(root.getRight());
    }

    /**
     * returns the nodes of the binary tree in
     * post-order traversal order, left tree, right tree, root
     * @return String the format of post order tree 
     */
    public String toStringPostOrder() {
	return toStringPostOrderRec(root);
    }

    private String toStringPostOrderRec(Node<E> root) {
	if (root == null) {
	    return "";
	}
	return toStringPostOrderRec(root.getLeft()) + toStringPostOrderRec(root.getRight()) + root.getData() + " ";
    }

    @Override
    public String toString() {
	return "\nTree:\nPre:\t" + toStringPreOrder().trim() + "\nIn:\t" + toStringInOrder().trim() + "\nPost:\t" + toStringPostOrder().trim();
    }
}
