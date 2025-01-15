package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    //17
    //  9
    //    3
    //    11
    //  25
    //    20
    //    31

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.key);


        return " ".repeat(indent);
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> allTrees = new ArrayList<>();
        allTrees.add(this);

        if (this.getLeft() != null) {
            List<AbstractBinaryTree<E>> result = this.getLeft().preOrder();
            for (AbstractBinaryTree<E> eAbstractBinaryTree : result) {
                allTrees.add(eAbstractBinaryTree);
            }
        }

        if (this.getRight() != null) {
            List<AbstractBinaryTree<E>> abstractBinaryTrees = this.getRight().preOrder();
            for (AbstractBinaryTree<E> abstractBinaryTree : abstractBinaryTrees) {
                allTrees.add(abstractBinaryTree);
            }
        }

        return allTrees;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> allTrees = new ArrayList<>();

        if (this.getLeft() != null) {
            List<AbstractBinaryTree<E>> result = this.getLeft().inOrder();
            for (AbstractBinaryTree<E> eAbstractBinaryTree : result) {
                allTrees.add(eAbstractBinaryTree);
            }
        }

        allTrees.add(this);

        if (this.getRight() != null) {
            List<AbstractBinaryTree<E>> abstractBinaryTrees = this.getRight().inOrder();
            for (AbstractBinaryTree<E> abstractBinaryTree : abstractBinaryTrees) {
                allTrees.add(abstractBinaryTree);
            }
        }

        return allTrees;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        if (this.left != null) {
            result.addAll(this.left.postOrder());
        }
        if (this.right != null) {
            result.addAll(this.right.postOrder());
        }
        result.add(this);

        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {

    }
}
