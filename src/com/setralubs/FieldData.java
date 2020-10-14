/**
 * For field data storage
 */
package com.setralubs;
/**
 *
 * @param <T>
 */
public class FieldData<T>{
    T value;

    public FieldData(){}

    public FieldData(T value){
        this.value=value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
