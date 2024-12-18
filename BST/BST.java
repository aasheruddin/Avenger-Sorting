import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;




/**
 * Binary Search Tree (BST) class that implements Iterable for in-order traversal.
 * 
 * @param <T> The type of elements stored in the BST, must extend Comparable.
 */
public class BST<T extends Comparable<T>> implements Iterable<T> {

	 /**
     * Inner class representing a node in the BST.
     */
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
		}
		public T getData() { return data; }
		public void setData(T d) { data = d; }
		public void setLeft(BSTNode l) { left = l; }
		public void setRight(BSTNode r) { right = r; }
		public BSTNode getLeft() { return left; }
		public BSTNode getRight() { return right; }
		public boolean isLeaf() { return (getLeft() == null) && (getRight() == null); }
		public int compareTo(BSTNode o) {
			return this.getData().compareTo(o.getData());
		}
	}

	

	private BSTNode root;
	private int size;
	private Comparator<T> comparator;
	//private Queue<BSTNode> queue;

	 /**
     * Default constructor for the BST class.
     */
	public BST(){
		root =null;
		comparator = null;
		size = 0;
	}


	/**
     * Constructor for the BST class with a custom comparator.
     * 
     * @param comparator The comparator to be used for ordering elements in the BST.
     */
	public BST(Comparator<T> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	

	/**
	 * Return the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return true if element d is present in the tree.
	 */
	public T find(T d) {
		return find(d, root);
	}

	/**
	 * Add element d to the tree.
	 */
	public void add(T d) {
		BSTNode n = new BSTNode(d);
		size++;
		if (root == null) {
			root = n;
		} else {
			add(root, n);
		}
	}

	/**
	 * Return the height of the tree.
	 */
	public int height() {
		return height(root);
	}

	public void printInOrder() {
		inOrderTraversal(root);
	}
	
	public void printPreOrder() {
		inOrderTraversal(root);
	}
	
	public void printPostOrder() {
		inOrderTraversal(root);
	}
	
	public void printLevelOrder() {
		inOrderTraversal(root);
	}

	/**
     * Check if the tree contains the specified element.
     *
     * @param d The element to check for.
     * @return True if the element is present, false otherwise.
     */
	public boolean contains(T d) {
        return contains(d, root);
    }

	 /**
     * Remove the specified element from the tree.
     *
     * @param d The element to remove.
     */
	public void remove(T d){
		root = remove(root, d);
		size--;
	}


	private BSTNode remove(BSTNode root, T d) {
		if (root == null) {
			return null;
		}
	
		int compareResult = d.compareTo(root.getData());
	
		if (compareResult < 0) {
			root.setLeft(remove(root.getLeft(), d));
		} else if (compareResult > 0) {
			root.setRight(remove(root.getRight(), d));
		} else {
			// Node with only one child or no child
			if (root.getLeft() == null) {
				return root.getRight();
			} else if (root.getRight() == null) {
				return root.getLeft();
			}
	
			// Node with two children: Get the inorder successor (smallest
			// in the right subtree)
			root.setData(findMin(root.getRight()).getData());
	
			// Delete the inorder successor
			root.setRight(remove(root.getRight(), root.getData()));
		}
	
		return root;
	}

	private BSTNode findMin(BSTNode root) {
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}


	private boolean contains(T d, BSTNode r) {
        if (r == null) {
            return false;
        }

        int c = d.compareTo(r.getData());

        if (c == 0) {
            return true; // Found the target
        } else if (c < 0) {
            return contains(d, r.getLeft()); // Search left subtree
        } else {
            return contains(d, r.getRight()); // Search right subtree
        }
    }
	
	private T find(T d, BSTNode r) {
		if (r == null)
			return null;
		int c = d.compareTo(r.getData());
		if (c == 0)
			return r.getData();
		else if (c < 0)
			return find(d, r.getLeft());
		else
			return find(d, r.getRight());
	}

	/* Do the actual add of node r to tree rooted at n */
	private void add(BSTNode r, BSTNode n) {
		int c = n.compareTo(r);
		if (c < 0) 
			if(r.getLeft() == null){
				r.setLeft(n);
			}
			else
				add(r.getLeft(),n);

		else if (c > 0)
			if(r.getRight() == null){
				r.setRight(n);
			}

			else
				add(r.getRight(), n);
		
		}
	

	/* Implement a height method. */
	private int height(BSTNode r) {
		//check both sides
		// if both
		if(r == null){
		return -1;
		}
		else{
		int lefth = height(r.getLeft());
		int righth = height(r.getRight());
		return Math.max(lefth, righth) + 1;
		}
		}
		

	private void visit(BSTNode r) {
		if (r != null)
			System.out.println(r.getData());
	}
	
	private void inOrderTraversal(BSTNode r) {
		if (r == null)
			return;
		else {
			inOrderTraversal(r.getLeft());
			visit(r);
			inOrderTraversal(r.getRight());
		}
	}
	
	// private void preOrderTraversal(BSTNode r) {
	// 	// TODO:
	// 	if(r != null){
			
	// 	visit(r);
	// 	preOrderTraversal(r.getLeft());
	// 	preOrderTraversal(r.getRight());

	// 	}
		
	// 	else return;

	// }
	
	// private void postOrderTraversal(BSTNode r) {
	// 	// TODO:
	// 	if( r != null){
	// 		postOrderTraversal(r.getLeft());
	// 		postOrderTraversal(r.getRight());
	// 		visit(r);
	// 	}
	// 	else return;
	// }
	
	// private void levelOrderTraversal(BSTNode r) {
	// 	// TODO:
	// 	//let q1 be empty
	// 	Queue<BSTNode> q = new LinkedList<BSTNode> ();

	// 	if(r!=null){
	// 	q.add(r);
	// 	}

	// 	while(!q.isEmpty()){
	// 	BSTNode curr = q.remove();
	// 	visit(curr);
	// 	q.add(curr.getLeft());
	// 	q.add(curr.getRight());			

	// 	}
	// }

	   /**
     * Adds an element to the BST in the comparator order.
     * 
     * @param d The element to add.
     */
	public void addInOrder(T d) {		
		BSTNode n = new BSTNode(d);
		if (root == null) {
			root = n;
			size++;
		} else {
			addInOrder(root, n);
		}
	}

	 /**
     * Adds a node to the BST in comparator order.
     * 
     * @param r The root node of the subtree.
     * @param n The node to add.
     */
	private void addInOrder(BSTNode r, BSTNode n) {				
		int c = comparator.compare(n.getData(), r.getData());
		if (c < 0) {
			if(r.getLeft() == null) {
				r.setLeft(n);
				size++;
			}
			else
				addInOrder(r.getLeft(), n);
		}
		else {
			if(r.getRight() == null) {
				r.setRight(n);
				size++;
			}
			else
				addInOrder(r.getRight(), n);
		}
	}
	
	

		

	// 	private int compare(T a, T b) {
	// 		if (comparator == null) {
	// 			return a.compareTo(b);
	// 		} else {
	// 			return comparator.compare(a, b);
	// 		}

	// }


	  /**
     * Iterator for in-order traversal of the BST.
     */
	private class BSTIterator implements Iterator<T>{
		private Queue<BSTNode> q;
		
		/**
		 * constructs a new BSTiterator, initializes queue and populates it by traversing the BST 
		 */
		public BSTIterator() {
			q = new LinkedList<>();
			traverse(root);
		}

		/**
		 * traverses BST recursively and adds each node to the queue 
		 * @param n the current node being processed for traversal 
		 */
		private void traverse(BSTNode n) {
			if(n != null) {
				traverse(n.left);
				q.add(n);
				traverse(n.right);
			}
			
		}

		/**
		 * @return true if there is another element, false if not  
		 */
		@Override
		public boolean hasNext() {
			return !q.isEmpty();
		}

		/**
		 * gets the next element in the iterator 
		 * @return the next element in the iteration 
		 * @throws NoSuchElementException if there are no more elements to retrieve 
		 */
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return q.poll().data;
		}
	}
	
	 /**
     * Returns an iterator over the elements in this BST for in-order traversal.
     * 
     * @return An iterator over the elements in this BST.
     */
	public Iterator<T> iterator(){
		return new BSTIterator();
	}
}
