// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        Dictionary d = freeBlk.Find(blockSize,false);
        if(d!=null){
            if(d.size!=blockSize){
                allocBlk = allocBlk.Insert(d.address, blockSize, d.address);
                freeBlk.Delete(d);
                freeBlk = freeBlk.Insert(d.address+blockSize, d.size-blockSize, d.size-blockSize);

            }
            else{
                 allocBlk = allocBlk.Insert(d.address, blockSize, d.address);
                 freeBlk.Delete(d);
            }
            
            return allocBlk.address;

        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        Dictionary f = allocBlk.Find(startAddr, true);
        if(f!=null){
            freeBlk = freeBlk.Insert(startAddr, f.size, f.size);
            allocBlk.Delete(f);
            return 0;
        }
        return -1;
    }

}