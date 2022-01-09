package io.github.jast90.swt.component.tree;


public class TreeNode<T>{
    private String label;

    private T data;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
