package com.github.wblancqu.custody.domain;

public enum Parent {

    MOM,
    DAD;

    public Parent otherParent() {
        return this == MOM ? DAD : MOM;
    }

}
