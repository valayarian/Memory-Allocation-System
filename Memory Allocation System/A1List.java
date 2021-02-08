    // Implements Dictionary using Doubly Linked List (DLL)
    // Implement the following functions using the specifications provided in the class List

    public class A1List extends List {

        private A1List  next; // Next Node
        private A1List prev;  // Previous Node 

        public A1List(int address, int size, int key) { 
            super(address, size, key);
        }
        
        
        public A1List(){
            super(-1,-1,-1);
            // This acts as a head Sentinel

            A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
            
            this.next = tailSentinel;
            tailSentinel.prev = this;
            
        }

        public A1List Insert(int address, int size, int key)
        {   
            if(this.sanity()==true){
                A1List newnode = new A1List(address,size,key);
                A1List current = this;
                while(current.prev!=null){
                    current=current.prev;
                }
                newnode.prev=current.next.prev;
                current.next.prev=newnode;
                newnode.next=current.next;
                current.next=newnode;

                return newnode;
            }
            return null;

        }

        public boolean Delete(Dictionary d) 
        {  if(this.sanity()==true){
           A1List left=this;
           A1List right=this;
            if(this.Find(d.key,true)==null){
                return false;
            }
            else{
                if(d.key==-1 && d.size== -1 && d.address==-1){
                    A1List n = this;
                    
                    while(n.next!=null){
                        if(n.key==-1 && n.size==-1 && n.address==-1 && n.prev!=null){
                            
                            n.prev.next=n.next;
                            n.next.prev=n.prev;
                            n.next=null;
                            n.prev=null;
                            n=null;
                            return true;
                            
                        }
                        n=n.next;
                     }
                    
                    return false;
                     
                } 

                while(left!=null || right!=null){
                    if (left!=null && left.key==d.key ){
                       if(d.size==left.size && d.address==left.address){
                            left.prev.next=left.next;
                            left.next.prev=left.prev;
                            left.next=null;
                            left.prev=null;
                            left=null;
                        }
                    }
                    else if(right!=null && right.key==d.key){
                        if(d.size==right.size && d.address==right.address){
                            right.prev.next=right.next;
                            right.next.prev=right.prev;
                            right.next=null;
                            right.prev=null;
                            right=null;

                        }
                    
                    }
                    if(left!=null)left=left.next;
                    if(right!=null)right=right.prev;
                }
                return true;
            }
        }
        return false;

        }

        public A1List Find(int k, boolean exact)
            {  if(this.sanity()==true){
                   A1List temp = this.getFirst();
                   if(temp==null) return null;
                   if(exact==true){
                        while(temp.next!=null){
                            if(temp.key==k){
                                return temp;
                            }
                            temp=temp.next;
                        }
                        return null;
                   }
                   else{
                        while(temp.next!=null){
                            if(temp.key==k || temp.key>k){
                                return temp;
                            }
                            temp=temp.next;
                        }
                        return null;
                   }
                   
                }
                return null;
            }

        public A1List getFirst()
        {
            if(this.sanity()==true){
                A1List current = this;
                if(current.prev!=null){
                    while(current.prev.prev!=null){
                       current=current.prev;
                    }
                    
                    return current;
                }
                else if(current.prev==null && current.next.next==null){
                    return null;
                }
                else {
                   
                    return current.next;
                }
            }
            return null;

        }
        
        public A1List getNext() 
        {
            if(this.sanity()==true){
                if(this.next==null || this.next.next==null){
                    return null;
                }
                A1List nex = this.next;
                
                return nex;
            }
        return null;
        }

        public boolean sanity()
        {   if (this.getFirst()==null) return true;
            A1List slow = this.getFirst();
            A1List fast = slow;
            boolean t1 = true;
            while(slow!=null && fast!=null && fast.next!=null){
                fast=fast.next.next;
                slow=slow.next;

                if(slow==fast){
                    t1 = false;
                    break;
                }
            }
            if(t1==false){
                return false;
            }
            else{
                A1List temp=this.getFirst();
                temp=temp.prev;
                if(temp.address!=-1 || temp.size!=-1 || temp.key!=-1) return false;
                while(temp.next!=null){
                    temp=temp.next;
                    if(temp.next.prev!=temp && temp.next!=null) return false;
                }
                if(temp.address!=-1 || temp.size!=-1 || temp.key!=-1) return false;
            }
            return true;
        } 

    }


