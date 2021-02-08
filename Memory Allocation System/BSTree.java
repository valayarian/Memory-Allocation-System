// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    private BSTree getsentinal(){
        BSTree temp = this;
        if(this==null) return null;
        while(temp.parent!=null) temp = temp.parent;
        return temp;
    }

    public BSTree Insert(int address, int size, int key) 
    {   
        BSTree newnode = new BSTree(address,size,key);
        BSTree sentinal = this.getsentinal();
        BSTree root = this.getRoot();
        if(root==null){
            sentinal.right = newnode;
            newnode.parent = sentinal;
            return newnode;
        }
        BSTree temp = root;
        BSTree par = sentinal;
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
        return newnode;
    }
    public boolean Delete(Dictionary d) {
        if (d == null) {
            return false;
        }
        BSTree root = this.getRoot();
        if (root == null) {
            return false;
        }
        BSTree bsTree2 = null;
        
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

        
    

    private boolean deletehelp(BSTree bsTree) {
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
            return true;
        }
        else if (bsTree.right == null && bsTree.left != null) {
            if (bsTree.parent.left==bsTree) {
                bsTree.parent.left = bsTree.left;
                bsTree.left.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.left = null;
                bsTree = null;
                return true;
            }
            else{
                bsTree.parent.right = bsTree.left;
                bsTree.left.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.left = null;
                bsTree = null;
                return true;
            }
        }
        else if (bsTree.right != null && bsTree.left == null) {
            if (bsTree.parent.left==bsTree) {
                bsTree.parent.left = bsTree.right;
                bsTree.right.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.right= null;
                bsTree = null;
                return true;
            }
            else{
                bsTree.parent.right = bsTree.right;
                bsTree.right.parent = bsTree.parent;
                bsTree.parent = null;
                bsTree.right = null;
                bsTree = null;
                return true;
            }
        }
        else{
            BSTree next = bsTree.getNext();
            
            if(next.parent.left==next){
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

                 bsTree.left = null;
                 bsTree.right = null;
                 bsTree.parent = null;
                 bsTree = null;
                 return true;
            }

            else{
                next.left = bsTree.left;
                next.left.parent = next;
                next.parent = bsTree.parent;
                if (bsTree.parent.left == bsTree) next.parent.left = next;
                else next.parent.right = next;
                bsTree.left = null;
                bsTree.right = null;
                bsTree.parent = null;
                bsTree = null;
                return true;
            }

           
        }  
    }


    private BSTree findHelp(BSTree root, int key, boolean exact){
        BSTree buffer = null;
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

    public BSTree Find(int key, boolean exact){
        BSTree root = this.getRoot();
        BSTree temp = findHelp(root, key, exact);
        return temp;
    }


    private BSTree getRoot(){
        BSTree temp = this.getsentinal();
        if(temp==null) return null;
        if(temp.right!=null) return temp.right;
        return null;
    }

    public BSTree getFirst()
    { 
        if(this==null) return null;
        BSTree temp = this.getRoot();
        if(temp==null) return null;
        while(temp.left!=null) temp = temp.left;
        return temp;

    }

    public BSTree getNext()
    {   
        BSTree temp = this;
        if(temp.parent==null) return null;
        if(temp.right!=null){
            BSTree node = temp.right;
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

    public boolean sanity()
    { 
        BSTree temp = this;
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

        BSTree temp2 = null;
        while(temp!=null){
             temp2 = temp.getNext();
             if(temp2 == null) return true;
             if(temp2.key < temp.key) return false;
             temp = temp2;
        }
        return true;
    }    

}