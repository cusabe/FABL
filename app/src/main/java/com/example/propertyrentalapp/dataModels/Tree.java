package com.example.propertyrentalapp.dataModels;

import android.util.Log;

import java.util.ArrayList;

public class Tree {

    private Node root;

    public Tree() {root = null;}

    // Using overloaded methods to handle root and non-root cases

    public Node getRoot() {
        return this.root;
    }

    public void insert(Node x) {
        // Insert node x into this tree starting at the root
        if (this.root == null) {
            this.root = x;
            //Log.d("Tree", "Added root node");
        } else
            this.insert(x, this.root);
    }

    public void insert(Node x, Node y) {
        // Insert node x into this tree starting at node y
        if (x.getKey() < y.getKey()) {
            //Log.d("Tree","left ");
            if (y.left == null) {
                y.left = x;
                //Log.d("Tree","node");
            } else insert(x, y.left);
        } else if (x.getKey() > y.getKey()) {
            //Log.d("Tree","right ");
            if (y.right == null) {
                y.right = x;
                //Log.d("Tree","node");
            } else insert(x, y.right);
        } else {
            Log.d("Tree","Duplicate key - used incrementKey method");
            x.incrementKey();
            //Log.d("Tree","right ");
            if (y.right == null) {
                y.right = x;
                //Log.d("Tree","node");
            } else insert(x, y.right);
        }
    }

    public int size() {
        // Calculate size ie count of nodes of the whole tree
        if (root == null) return 0;
        else return size(root);
    }

    public int size(Node x) {
        // Calculate size of the subtree whose root is node x
        if (x == null) return 0;
        else return (1 + size(x.left) + size(x.right));
    }

    public int height() {
        // Calculate height of the whole tree ie maximum number of edges top to bottom
        // Empty tree has height -1, single node tree has height 0 etc
        if (root == null) return -1;
        else return (1 + Math.max(height(root.left), height(root.right)));
    }

    public int height(Node x) {
        // Calculate height of the subtree whose root is node x
        if (x == null) return -1;
        else return (1 + Math.max(height(x.left), height(x.right)));
    }

    public ArrayList<ApartmentUnit> preOrderList() {
        // Return a list of the apartmentunit objects in the tree in their preOrder
        if (root == null) return new ArrayList<ApartmentUnit>();
        else return preOrderList(root);
    }

    public ArrayList<ApartmentUnit> preOrderList(Node x) {
        // Return a list of the apartmentunit objects in the tree in their preOrder
        if (x == null) return new ArrayList<ApartmentUnit>();
        else {
            ArrayList<ApartmentUnit> pOL = new ArrayList<ApartmentUnit>();
            pOL.add(x.getApartmentUnit());
            pOL.addAll(preOrderList(x.left));
            pOL.addAll(preOrderList(x.right));
            return pOL;
        }
    }

    public ArrayList<ApartmentUnit> inOrderList() {
        // Return a list of the apartmentunit objects in the tree in their inOrder (ie sorted by key)
        if (root == null) return new ArrayList<ApartmentUnit>();
        else return inOrderList(root);
    }

    public ArrayList<ApartmentUnit> inOrderList(Node x) {
        // Return a list of the apartmentunit objects in the tree inOrder starting with Node x
        if (x == null) return new ArrayList<ApartmentUnit>();
        else {
            ArrayList<ApartmentUnit> iOL = new ArrayList<ApartmentUnit>();
            iOL.addAll(inOrderList(x.left));
            iOL.add(x.getApartmentUnit());
            iOL.addAll(inOrderList(x.right));
            return iOL;
        }
    }

    public ArrayList<ApartmentUnit> search(SearchQuery sq) {
        // Search for the lowest or highest ApartmentUnits in the tree that match the SearchQuery sq
        if (sq.getSortOrder() == SortOrder.LOWEST) {
            return searchLowest(sq);
        } else if (sq.getSortOrder() == SortOrder.HIGHEST) {
            return searchHighest(sq);
        } else return null;
    }

    public ArrayList<ApartmentUnit> searchLowest(SearchQuery sq) {
        // Search for the lowest ApartmentUnits in the tree that match the SearchQuery sq
        if (root == null) return new ArrayList<ApartmentUnit>();
        else return searchLowest(sq, root);

    }

    public ArrayList<ApartmentUnit> searchLowest(SearchQuery sq, Node x) {
        // Search for the lowest ApartmentUnits in the tree that match the SearchQuery sq
        if (x == null) return new ArrayList<ApartmentUnit>();
        else {
            ArrayList<ApartmentUnit> sLP = new ArrayList<ApartmentUnit>();
            sLP.addAll(searchLowest(sq,x.left));
            if (x.getApartmentUnit().queryMatch(sq))
                {sLP.add(x.getApartmentUnit());}
            sLP.addAll(searchLowest(sq,x.right));
            return sLP;
        }
    }

    public ArrayList<ApartmentUnit> searchHighest(SearchQuery sq) {
        // Search for the highest ApartmentUnits in the tree that match the SearchQuery sq
        if (root == null) return new ArrayList<ApartmentUnit>();
        else return searchHighest(sq, root);

    }

    public ArrayList<ApartmentUnit> searchHighest(SearchQuery sq, Node x) {
        // Search for the highest ApartmentUnits in the tree that match the SearchQuery sq
        if (x == null) return new ArrayList<ApartmentUnit>();
        else {
            ArrayList<ApartmentUnit> sHP = new ArrayList<ApartmentUnit>();
            sHP.addAll(searchHighest(sq,x.right));
            if (x.getApartmentUnit().queryMatch(sq))
                {sHP.add(x.getApartmentUnit());}
            sHP.addAll(searchHighest(sq,x.left));
            return sHP;
        }
    }
}
