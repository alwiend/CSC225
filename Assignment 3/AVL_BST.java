/*
* Alwien Dippenaar
* V00849850
* CSC 225 Assignment 3
* March 14th, 2018
* */

import java.lang.*;

public class AVL_BST{
	public static BST myTree = new BST();

	/*
	* This method calls the helper method in the BST class to check if the tree is an AVL tree.
	* @param b The tree with all the node correctly inserted in the BST stucture
	* @return true If the tree is an AVL tree
	* @return false If the tree is not an AVL tree
	* */
	public static boolean checkAVL(BST b){
		return b.isBalanced(b.getRoot());
	}

	/*
	* This method creates the BST by calling a method in the BST class
	* @param a The array of values to be inserted into the tree
	* @return myTree The completed tree
	* */
	public static BST createBST(int[] a){
		for (int i = 0; i < a.length; i++) {
			myTree.insert(a[i]);
		}
		return myTree;
	}
	public static void main(String[] args){
	//	int[] A = {5,2,8,6,1,9,52,3};
		int[] A = {82, 85, 153, 195, 124, 66, 200, 193, 185, 243, 73, 153, 76};
	//	int[] A = {5, 1, 98, 100, -3, -5, 55, 3, 56, 50};
	//	int[] A = {297, 619, 279, 458, 324, 122, 505, 549, 83, 186, 131, 71};
	//	int[] A = {78};
		BST b = createBST(A);
		System.out.println(checkAVL(b));
	}
}

class BST{
	private BSTNode root;

	/*
	* Initiliazes he root of the tree
	* */
	public BST(){
		root = null;
	}

	/*
	* Checks to see if it an empty tree
	* */
	public boolean isEmpty(){
		return root == null;
	}

	/*
	* A method that starts the tree insert process
	* @param x The item to be inserted into the tree
	* */
	public void insert(int x){
		root = insert(root, x);
	}

	/*
	* A recursive function that finds the prper pace for insertion
	* @param node The current node to see if it is empty, ie. the place of insertion
	* @param x The value of the node
	* @return node The newly inserted node
	* */
	public BSTNode insert(BSTNode node, int x){
		if (node == null){
			node = new BSTNode(x);
		} else {
			if (x <= node.getData()){
				node.leftChild = insert(node.leftChild, x);
			} else {
				node.rightChild = insert(node.rightChild, x);
			}
		}
		node.incrementHeight(node, node.getHeight());
		return node;
	}

	/*
	* This method will check to see if the tree is an AVL tree or not
	* @param node The current node of interest
	* @return true or false depending on if it is an AVL tree or not
	* */
	public boolean isBalanced(BSTNode node){
		boolean isLeft = true;
		boolean isRight = true;
		BSTNode tempLeft = node.getLeftChild();
		BSTNode tempRight = node.getRightChild();
		int leftChildHeight = 0;
		int rightChildHeight = 0;
		if(node == null) {
			return true;
		}
		if (tempLeft == null){
			isLeft = false;
		} else {
			leftChildHeight = tempLeft.getHeight();
		}
		if (tempRight == null){
			isRight = false;
		} else {
			rightChildHeight = tempRight.getHeight();
		}
		if(isLeft && isRight){
			if (Math.abs(leftChildHeight - rightChildHeight) > 1){
				return false;
			}
			if (leftChildHeight >= rightChildHeight){
				 return isBalanced(tempLeft);
			} else {
				return isBalanced(tempRight);
			}
		}
		if (isRight == false){
			if (leftChildHeight > 1){
				return false;
			}
		}
		if (isLeft == false){
			if(rightChildHeight > 1){
				return false;
			}
		}
		return true;
	}

	/*
	* Method to get the root of the tree
	* @return root The root of the tree
	* */
	public BSTNode getRoot(){
		return root;
	}
}

class BSTNode{
	public BSTNode leftChild, rightChild;
	int height;
	int data;

	/*
	* Initializes the BSTNode with left and right child, height, and data fields
	* */
	public BSTNode(){
		leftChild = null;
		rightChild = null;
		height = 0;
		data = 0;
	}

	/*
	* intializes the node with the data and height values that correspond to that node
	* */
	public BSTNode(int x){
		leftChild = null;
		rightChild = null;
		height = 1;
		data = x;
	}

	/*
	* Method to return the leftChild of the parent node
	* */
	public BSTNode getLeftChild() {
		return leftChild;
	}

	/*
	* Method to return the rightChild of the parent node
	* */
	public BSTNode getRightChild() {
		return rightChild;
	}

	/*
	* Method to return the node of the current node
	* */
	public int getHeight(){
		return height;
	}

	/*
	* Method to increment the height of node dependent of the heights of its children nodes
	* @param node The current node for the height incrementation
	* @param height the height of the node that is to be increased
	* */
	public void incrementHeight(BSTNode node, int height){
		BSTNode tempLeft = node.getLeftChild();
		BSTNode tempRight = node.getRightChild();
		int leftChildHeight = 0;
		int rightChildHeight = 0;
		if (tempLeft != null){ // checks if the left child is null
			leftChildHeight = tempLeft.getHeight();
		}
		if (tempRight != null){ // checks if the right child is null
			rightChildHeight = tempRight.getHeight();
		}
		if (leftChildHeight > rightChildHeight){ // chooses to larger height of the left and right child
			this.height = leftChildHeight + 1; // increments the currents node height by 1 more than the child
		} else {
			this.height = rightChildHeight + 1; // increments the currents node height by 1 more than the child
		}
	}

	/*
	* Method to return the data of the current node
	* */
	public int getData(){
		return data;
	}
}
