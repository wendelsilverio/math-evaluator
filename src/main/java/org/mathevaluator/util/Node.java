package org.mathevaluator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node<T> {

    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private int level = 0;
    private T data = null;

    public Node() {
    }

    public Node(T data) {
	this.data = data;
    }

    public Node(T data, Node<T> parent) {
	this.data = data;
	this.parent = parent;
    }

    public List<Node<T>> getChildren() {
	return children;
    }

    public List<T> getImediateChildrenValues() {
	return children.stream().map(Node::getData).collect(Collectors.toList());
    }

    public void setParent(Node<T> parent) {
	this.parent = parent;
    }

    public void addChild(T data) {
	addChild(new Node<>(data));
    }

    public void addChild(Node<T> child) {
	child.setParent(this);
	child.level = this.level + 1;
	this.children.add(child);
    }

    public T getData() {
	return this.data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public boolean isRoot() {
	return (this.parent == null);
    }

    public boolean isLeaf() {
	if (this.children.size() == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    public void removeParent() {
	this.parent = null;
    }

    public int getLevel() {
	return level;
    }

    @Override
    public String toString() {
	return "Node [" + (parent != null ? "parent=" + parent.getData() + ", " : "") + "level=" + level + ", data="
		+ data + ", children=" + children.size() + "]";
    }

    public Node<T> getParent() {
	return parent;
    }

}
