// Class: Height balanced AVL Tree
// Binary Search Tree
// import java.util.Queue;
// import java.util.LinkedList;

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    {   
        AVLTree newnode = new AVLTree(address,size,key);
        AVLTree sentinal = this.getsentinal();
        AVLTree root = this.getRoot();
        if(root==null){
            sentinal.right = newnode;
            newnode.parent = sentinal;
            
        }
        AVLTree temp = root;
        AVLTree par = sentinal;
        while(temp!=null){
            par = temp;
            if(temp.key>key) temp=temp.left;
            else if(temp.key<key) temp=temp.right;
            else{
                if(temp.address>address) temp=temp.left;
                else if(temp.address<address) temp=temp.right;
                else{
                    if(temp.size>size) temp=temp.left;
                    else if(temp.size<size) temp=temp.right;
                    else return null;
                }
            }
        }
        if(par.key>key){
            par.left = newnode;
            newnode.parent = par;
        }
        else if(par.key<key){
            par.right = newnode;
            newnode.parent = par;
        }
        else{
            if(par.address>address){
                 par.left = newnode;
                 newnode.parent = par;
            }
            else if(par.address<address){
                par.right = newnode;
                newnode.parent = par;
            }
            else{
                if(par.size>size){
                    par.left = newnode;
                    newnode.parent = par;
                }
                else if(par.size<size){
                    par.right = newnode;
                    newnode.parent = par;
                }
                else return null ;
            }
        }
        updateHeight(newnode);
        update(newnode);
        return newnode;   
    }

    public boolean Delete(Dictionary d) { 
               
        if (d == null) {
            return false;
        }
        AVLTree root = this.getRoot();
        if (root == null) {
            return false;
        }
        AVLTree bsTree2 = null;
        
        while(root!=null){
            if(root.key > d.key){
                root = root.left;
            }
            else if(root.key < d.key){
                root = root.right;
            }
            else{
                if(root.address > d.address){
                    root=root.left;
                }
                else if(root.address < d.address){
                    root=root.right;
                }
                else{
                    if(root.size > d.size ){
                        root=root.left;
                    }
                    else if(root.size < d.size){
                        root = root.right;
                    }
                    else{
                       bsTree2 = root;
                        break;
                    }
                }
            }
        }


        if (bsTree2 == null) {
            return false;
        }
        deletehelp(bsTree2);

        return false;
    }

    private boolean deletehelp(AVLTree bsTree) { 
        AVLTree parnt = bsTree.parent;
        if(parnt == null) return false;;
        if (bsTree.right == null && bsTree.left == null) {
            if (bsTree.parent.left==bsTree) {
                bsTree.parent.left = null;
                bsTree.parent = null;
            }
            else{
                bsTree.parent.right = null;
                bsTree.parent = null;
            }
            bsTree = null;
            updateHeight(parnt);
            update(parnt);
            
        }
        else if (bsTree.right == null && bsTree.left != null) {
            if (bsTree.parent.left==bsTree) {
                bsTree.parent.left = bsTree.left;
                bsTree.left.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.left = null;
                bsTree = null;
                
            }
            else{
                bsTree.parent.right = bsTree.left;
                bsTree.left.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.left = null;
                bsTree = null;
                
            }

            updateHeight(parnt);
            update(parnt);
        }
        else if (bsTree.right != null && bsTree.left == null) {
            if (bsTree.parent.left==bsTree) {
                bsTree.parent.left = bsTree.right;
                bsTree.right.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.right= null;
                bsTree = null;
               
            }
            else{
                bsTree.parent.right = bsTree.right;
                bsTree.right.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.right = null;
                bsTree = null;
               
            }

            updateHeight(parnt);
            update(parnt);
        }


        else{
            AVLTree next = bsTree.getNext();
                     
            if(next!=bsTree.right){
                AVLTree sparnt = next.parent;
                 if(next.right!=null){
                    next.parent.left = next.right;
                    next.right.parent = next.parent;
                 }
                 else{
                    next.parent.left = null;
                 }
                 next.left = bsTree.left;
                 next.right = bsTree.right;
                 next.left.parent = next;
                 next.right.parent = next;
                 next.parent = bsTree.parent;
                 if(bsTree.parent.left==bsTree) next.parent.left = next;
                 else next.parent.right = next;

                updateHeight(sparnt);
                update(sparnt);

                 bsTree.left = null;
                 bsTree.right = null;
                 bsTree.parent = null;
                 bsTree = null;
                 
            }

            else{
                next.left = bsTree.left;
                next.left.parent = next;
                next.parent = bsTree.parent;
                if (bsTree.parent.left == bsTree) next.parent.left = next;
                else next.parent.right = next;

                updateHeight(next);
                update(next);

                bsTree.left = null;
                bsTree.right = null;
                bsTree.parent = null;
                bsTree = null;
               
            }
           
        } 
        return true; 

    }
        
    private AVLTree findHelp(AVLTree root, int key, boolean exact){
        AVLTree buffer = null;
        if(root==null) return null;
        if(exact){
            while(root!= null){ 
                if(root.key > key) root = root.left;
                else if(root.key < key) root = root.right;
                else{
                    buffer = root;
                    root = root.left;
                }
            }
        }
        else{
            while(root!= null){ 
                if(root.key > key || root.key == key){
                    buffer = root;
                    root = root.left;
                }
                else if(root.key < key)
                    root = root.right;
            }
        }
        return buffer;
    }

    public AVLTree Find(int key, boolean exact){
        AVLTree root = this.getRoot();
        AVLTree temp = findHelp(root, key, exact);
        return temp;
    }

    public AVLTree getNext()
    {   
        AVLTree temp = this;
        if(temp.parent==null) return null;
        if(temp.right!=null){
            AVLTree node = temp.right;
            while(node.left!=null){
                node=node.left;
            }
            return node;
        }
        else if(temp.right==null){
                if(temp.parent.left==temp) return temp.parent;
                else{
                    while(temp.parent!=null){
                        if(temp.parent.left == temp) return temp.parent;
                        temp = temp.parent;
                    }
                }
        }
        return null;
    }

    private void update(AVLTree node)
    {
        if(node==null || node.parent == null) return;
        if(node.parent != null && height(node.left) - height(node.right) > 1 || height(node.left) - height(node.right) < -1 ) rebalance(node);
        
        update(node.parent); 
    }

    private void rebalance(AVLTree node) {
        AVLTree temp1 = null;
        AVLTree temp2 = null;

        if(height(node.left)-height(node.right) > 1){
            if(height(node.left.left) > height(node.left.right)){
                temp1 = node.parent;
                int a =0;
                if(node.parent.left == node) a = 1;
                else if(node.parent.right == node) a = -1;
                node = rightRotate(node);
                node.parent  = temp1;
                if(a==1) node.parent.left = node;
                else if(a==-1) node.parent.right = node;

            }
            else{
                temp1 = node;
                temp2 = node.parent;
                int a =0;
                if(node.parent.left == node) a = 1;
                else if(node.parent.right == node) a = -1;
                node.left = leftRotate(node.left);
                node.left.parent = temp1;
                node = rightRotate(node);
                node.parent = temp2;
                if(a==1) node.parent.left = node;
                else if(a==-1) node.parent.right = node;

            }
        }

        else if(height(node.left)-height(node.right) < -1) {
            if(height(node.right.right) > height(node.right.left)) {
                temp1 = node.parent;
                int a = 0;
                if(node.parent.left == node) a = 1;
                else if(node.parent.right == node) a = -1;
                node = leftRotate(node);
                node.parent = temp1;
                if(a==1) node.parent.left = node;
                else if(a==-1) node.parent.right = node;
            }
            else{
                temp1 = node;
                temp2 = node.parent;
                int a =0;
                if(node.parent.left == node) a = 1;
                else if(node.parent.right == node) a = -1;
                node.right = rightRotate(node.right);
                node.right.parent = temp1;
                node = leftRotate(node);
                node.parent = temp2;
                if(a==1) node.parent.left = node;
                else if(a==-1) node.parent.right = node;

            }
        }

        
        if(node.parent == null){
            return;
        }

    }

    private void updateHeight(AVLTree n) {
        if(n.parent == null) return;
        n.height = 1 + max(height(n.left), height(n.right));
        updateHeight(n.parent);
    }

    private AVLTree rightRotate(AVLTree y) { 
        AVLTree x = y.left; 
        AVLTree T2 = x.right; 
  
        
        x.right = y; 
        y.left = T2; 
        if(T2!=null) T2.parent = y;
        y.parent = x;
  
        
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        
        return x; 
    } 

    private AVLTree leftRotate(AVLTree x) { 
        AVLTree y = x.right; 
        AVLTree T2 = y.left; 
  
        y.left = x; 
        x.right = T2; 
        if(T2!=null) T2.parent = x;
        x.parent = y;
  
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        return y; 
    }

    private int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 

    private int getBalance(AVLTree node)
    {
        if (node == null) 
            return 0; 
  
        return height(node.right) - height(node.left);
    }

    private int height(AVLTree node)
    {
        if (node == null) 
            return 0; 
  
        return node.height;
    }
    private AVLTree getsentinal(){
        AVLTree temp = this;
        if(this==null) return null;
        while(temp.parent!=null) temp = temp.parent;
        return temp;
    }

    private AVLTree getRoot(){
        AVLTree temp = this.getsentinal();
        if(temp==null) return null;
        if(temp.right!=null) return temp.right;
        return null;
    }

    public AVLTree getFirst()
    { 
        if(this==null) return null;
        AVLTree temp = this.getRoot();
        if(temp==null) return null;
        while(temp.left!=null) temp = temp.left;
        return temp;

    }

    public boolean sanity()
    { 
        AVLTree temp = this;
        while(temp.parent!=null){
            if(temp.parent.left==temp){
                if(temp.parent.key < temp.key) return false;
                else if(temp.parent.key == temp.key && temp.parent.address<temp.address) return false;
                else if(temp.parent.key == temp.key && temp.parent.address==temp.address && temp.parent.size<temp.size) return false;
            }
            else if(temp.parent.right== temp){
                if(temp.parent.key > temp.key) return false;
                else if(temp.parent.key == temp.key && temp.parent.address>temp.address) return false;
                else if(temp.parent.key == temp.key && temp.parent.address==temp.address && temp.parent.size>temp.size) return false;
            }
            temp = temp.parent;

        } 

        if(temp.left!=null) return false;
        if(temp.address!=-1 || temp.size!=-1|| temp.key!=-1) return false;

        AVLTree temp2 = null;
        temp=temp.getFirst();
        while(temp!=null){
            temp2 = temp.getNext();
            if(temp2 == null) return true;
            if(temp2.key == temp.key){
                if(temp2.address == temp.address){
                    if(temp2.size==temp.size || temp2.size<temp.size){
                        return false;
                    } 
                }
                else if(temp2.address < temp.address) return false;
            }
            else if(temp2.key < temp.key) return false;
            temp = temp2;
            temp2 = temp2.getNext();
        }

        temp = this.getFirst();
        while(temp!=null){
            if(getBalance(temp)>1 || getBalance(temp)<-1) return false;
            temp = temp.getNext();

        } 
        temp = this.getFirst();
        if(temp==null||temp.parent==null) return true;
        while(temp!=null && temp.parent!=null){
            if(temp.parent.left==temp){
                if(temp.parent.key < temp.key) return false;
                else if(temp.parent.key == temp.key && temp.parent.address<temp.address) return false;
                else if(temp.parent.key == temp.key && temp.parent.address==temp.address && temp.parent.size<temp.size) return false;
            }
            else if(temp.parent.right== temp){
                if(temp.parent.key > temp.key) return false;
                else if(temp.parent.key == temp.key && temp.parent.address>temp.address) return false;
                else if(temp.parent.key == temp.key && temp.parent.address==temp.address && temp.parent.size>temp.size) return false;
            }
            temp = temp.getNext();

        }
        return true;
    }

}



