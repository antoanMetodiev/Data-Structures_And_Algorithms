package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... subTrees) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
        initialAdd(subTrees);
    }

    private void initialAdd(Tree<E>... subTrees) {
        for (Tree<E> subTree : subTrees) {
            subTree.parent = this;
            this.children.add(subTree);
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();

        // 7,
        Deque<Tree<E>> childrenQueue = new ArrayDeque<>();
        childrenQueue.offer(this);

        while (!childrenQueue.isEmpty()) {
            Tree<E> current = childrenQueue.poll();

            result.add(current.value);

            // Отново го пълним:
            for (Tree<E> child : current.children) {
                childrenQueue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        Deque<Tree<E>> childrenStack = new ArrayDeque<>();
        childrenStack.push(this);

        while (!childrenStack.isEmpty()) {
            Tree<E> currentTree = childrenStack.pop();
            result.add(0, currentTree.value);

            for (Tree<E> child : currentTree.children) {
                childrenStack.push(child);
            }
        }

        return result;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> searchedTree = findSearchedTree(parentKey);
        if (searchedTree == null) throw new IllegalStateException("Parent key is not found!");

        child.parent = searchedTree;
        searchedTree.children.add(child);
    }

    private Tree<E> findSearchedTree(E parentKey) {
        Deque<Tree<E>> childrenTrees = new ArrayDeque<>();
        childrenTrees.offer(this);

        while (!childrenTrees.isEmpty()) {
            Tree<E> currentTree = childrenTrees.poll();

            if (currentTree.value.equals(parentKey)) {
                return currentTree;
            }

            for (Tree<E> children : currentTree.children) {
                childrenTrees.offer(children);
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> treeForRemove = findSearchedTree(nodeKey);
        if (treeForRemove == null) throw new IllegalStateException("Tree is not found!");
        if (treeForRemove.parent == null) {
            treeForRemove = null;
            return;
        }

        treeForRemove.parent.children.remove(treeForRemove);
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> tree1 = this.findSearchedTree(firstKey);
        Tree<E> tree2 = this.findSearchedTree(secondKey);
        if (tree1 == null || tree2 == null) throw new IllegalArgumentException();

        // Запазваме старите родители
        Tree<E> parent1 = tree1.parent;
        Tree<E> parent2 = tree2.parent;

        // Ако има родител, сменяме децата в родителите

        int indexForInsertNextEl1 = -1;
        int indexForInsertNextEl2 = -1;

        if (parent1 != null) {
            indexForInsertNextEl1 = parent1.children.indexOf(tree1);
        }
        if (parent2 != null) {
            indexForInsertNextEl2 = parent2.children.indexOf(tree2);
        }

        if (parent1 != null && indexForInsertNextEl1 != -1) {
            parent1.children.set(indexForInsertNextEl1, tree2);
        }
        if (parent2 != null) {
            parent2.children.set(indexForInsertNextEl2, tree1);
        }

        // Разменяме родителите
        tree1.parent = parent2;
        tree2.parent = parent1;

        // Обновяваме parent на всички деца на tree1
        for (Tree<E> child : tree1.children) {
            child.parent = tree2;
        }

        // Обновяваме parent на всички деца на tree2
        for (Tree<E> child : tree2.children) {
            child.parent = tree1;
        }
    }
}



