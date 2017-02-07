package com.binarymonks.jj.specs;

public class SpecPropField<VALUE, OWNER> implements FieldPropertyDelegate<VALUE, OWNER> {

    VALUE value;
    OWNER owner;
    String propertyDelegate;

    public SpecPropField(OWNER owner, VALUE value) {
        this.value = value;
        this.owner = owner;
    }

    public SpecPropField(OWNER owner) {
        this.owner = owner;
    }

    @Override
    public OWNER set(VALUE value) {
        this.value = value;
        return owner;
    }

    @Override
    public OWNER delegateToProperty(String propertykey) {
        this.propertyDelegate = propertykey;
        return owner;
    }

}
