class Trie {
    class Node
    {
       boolean WordEnd = false;
        Node[] childs;
        //public static root = null;
        Node()
        {
            this.childs= new Node[26];
        }
    }
    Node root = null;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word)
    {
        Node curr = root;
        for(int i=0; i<word.length(); i++)
        {
            int idx = word.charAt(i) - 'a';
            if(curr.childs[idx] == null)    curr.childs[idx] = new Node();

            curr = curr.childs[idx];
        }
        curr.WordEnd = true;
    }

    public boolean search(String word)
    {
        Node curr = root;
        for(int i=0; i<word.length(); i++)
        {
            int idx = word.charAt(i) - 'a';
            if(curr.childs[idx] == null)    return false;

            curr = curr.childs[idx];
        }
        return curr.WordEnd;
    }

    public boolean startsWith(String word)
    {
        Node curr = root;
        for(int i=0; i<word.length(); i++)
        {
            int idx = word.charAt(i) - 'a';
            if(curr.childs[idx] == null)    return false;

            curr = curr.childs[idx];
        }
        return true;
    }
}
