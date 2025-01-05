package edu.ithaca.dragon.io;

public class LinkedNode<T> {
    private T item;
    private LinkedNode<T> next;

    public LinkedNode(T item){
        this(item, null);
    }

    public LinkedNode(T item, LinkedNode<T> next){
        this.item = item;
        this.next = next;
    }

    public void setItem(T Newitem){
        this.item = Newitem;
    }

    public void setNext(LinkedNode<T> next){
        this.next = next;
    }

    public T getItem(){
        return item;
    }

    public LinkedNode<T> getNext(){
        return next;
    }

}
