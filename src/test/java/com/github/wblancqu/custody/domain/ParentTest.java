package com.github.wblancqu.custody.domain;

import org.junit.jupiter.api.Test;

import static com.github.wblancqu.custody.domain.Parent.DAD;
import static com.github.wblancqu.custody.domain.Parent.MOM;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParentTest {

    @Test
    void findsOtherParent() {
        assertEquals(MOM.otherParent(), DAD);
        assertEquals(DAD.otherParent(), MOM);
    }

}