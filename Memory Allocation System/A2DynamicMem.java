// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    
    public void Defragment() {
      if (this.type == 2){
        BSTree t = new BSTree();
        
        Dictionary root = freeBlk.getFirst(); 
        while(root!=null){
            t.Insert(root.address, root.size, root.address);
            root=root.getNext();
        }
        Dictionary node = t.getFirst();    
        if(node==null) return;
        Dictionary next = node.getNext();
        if(next==null) return;
        while(next!=null){

            if(node.address+node.size==next.address){
                Dictionary d1o = new BSTree(node.address,node.size,node.size);
                Dictionary d2o = new BSTree(next.address,next.size,next.size);
                Dictionary d1n = new BSTree(node.address,node.size,node.address);
                Dictionary d2n = new BSTree(next.address,next.size,next.address);
                freeBlk.Delete(d1o);
                freeBlk.Delete(d2o);
                // t.Delete(d1n);
                // t.Delete(d2n);
                freeBlk.Insert(node.address, node.size+next.size, node.size+next.size);
                node = new BSTree(node.address, node.size+next.size, node.address);
                next = next.getNext();
            }
            else{
                node = next;
                next=next.getNext();
            }
        }
        return;
      }
      else if (this.type == 3){
        AVLTree t = new AVLTree();
        
        Dictionary root = freeBlk.getFirst(); 
        while(root!=null){
            t.Insert(root.address, root.size, root.address);
            root=root.getNext();
        }
        Dictionary node = t.getFirst();    
        if(node==null) return;
        Dictionary next = node.getNext();
        if(next==null) return;
        while(next!=null){

            if(node.address+node.size==next.address){
                Dictionary d1o = new AVLTree(node.address,node.size,node.size);
                Dictionary d2o = new AVLTree(next.address,next.size,next.size);
                Dictionary d1n = new AVLTree(node.address,node.size,node.address);
                Dictionary d2n = new AVLTree(next.address,next.size,next.address);
                freeBlk.Delete(d1o);
                freeBlk.Delete(d2o);
                // t.Delete(d1n);
                // t.Delete(d2n);
                freeBlk.Insert(node.address, node.size+next.size, node.size+next.size);
                node = new AVLTree(node.address, node.size+next.size, node.address);
                next = next.getNext();
            }
            else{
                node = next;
                next=next.getNext();
            }
        }
        return;
      }
      return;
      }
      }

