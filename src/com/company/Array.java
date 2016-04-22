package com.company;

/**
 * Created by Legat on 22.04.2016.
 */
public class Array {
    Object[] array;
    int capability=1000;
    int size=0;
    public Array(){
        array=new Object[capability];
    }
    public Array(int capability){
        this.capability=capability;
        array=new Object[capability];
    }
    public int size(){
        return size;
    }
    /**
     * adding new object at a specific index
     * if index < 0 new object will be added at index 0
     * if index >= size new object will be added after the last object
     * @param index - position of new object
     * @param obj - new object to add
     */
    public void add(int index,Object obj){
        if(size==capability)
            createNewArray();
        if(index>size)
            index=size;
        else if(index<0)
            index=0;
        shiftRight(index);
        array[index]=obj;
        size++;

    }
    private void shiftRight(int index) {
        for(int i=size;i>index;i--)
            array[i]=array[i-1];

    }
    public void add(Object obj){
        if(size<capability){
            array[size]=obj;
        }
        else {
            createNewArray();
            array[size]=obj;
        }
        size++;
    }
    private void createNewArray() {
        capability=capability+capability;
        Object[] tmpArray=new Object[capability];
        //copying old array to the new one
        for(int i=0;i<size;i++){
            tmpArray[i]=array[i];
        }
        array=tmpArray;
    }
    /**
     *
     * @param index - position of element to remove
     * @return true if element has been removed otherwise false
     */
    public boolean remove(int index){
        if(index<0 || index>=size)
            return false;
        shiftLeft(index);
        size--;
        return true;
    }
    private void shiftLeft(int index) {
        for(int i=index+1;i<size;i++)
            array[i-1]=array[i];

    }
    public int indexOf(Object pattern){
        for(int i=0;i<size;i++){
            if(array[i].equals(pattern))
                return i;
        }
        return -1;
    }
    public int indexOfLast(Object pattern){
        for(int i=size-1;i>=0;i--){
            if(array[i].equals(pattern))
                return i;
        }
        return -1;
    }

    public Object getMax(Comparator comparator){
        Object max=array[0];
        for(int i=1;i<size;i++){
            if(comparator.compare(array[i], max)>0)
                max=array[i];
        }
        return max;
    }
    public void sort(Comparator comparator){
        int sizeForMoving=size-1;
        int swapCount;
        do{
            swapCount=maxMoveEnd(comparator,sizeForMoving);
            sizeForMoving--;
        }while(swapCount>0);
    }
    private int maxMoveEnd(Comparator comparator, int sizeForMoving) {
        int swapCount=0;
        for(int i=0;i<sizeForMoving;i++){
            if(comparator.compare(array[i], array[i+1])>0){
                swap(i,i+1);
                swapCount++;
            }
        }
        return swapCount;
    }
    private void swap(int i, int j) {
        Object tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;

    }
    public Object get(int index){
        if(index<0) index=0;
        else if (index>=size) index=size-1;
        return array[index];
    }
    public Array find(Predicate predicate){
        Array result=new Array();
        for(int i=0;i<size;i++){
            if(predicate.matches(array[i]))
                result.add(array[i]);
        }
        return result;

    }
}
