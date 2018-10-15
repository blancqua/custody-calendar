package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.Parent.DAD;
import static com.github.wblancqu.custody.domain.Parent.MOM;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParentTest {

    @Test
    void findsOtherParent() {
        assertEquals(MOM.otherParent(), DAD);
        assertEquals(DAD.otherParent(), MOM);
    }

}