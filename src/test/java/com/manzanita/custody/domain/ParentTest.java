package com.manzanita.custody.domain;

import org.junit.jupiter.api.Test;

import static com.manzanita.custody.domain.Parent.DAD;
import static com.manzanita.custody.domain.Parent.MOM;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParentTest {

    @Test
    void findsOtherParent() {
        assertEquals(MOM.otherParent(), DAD);
        assertEquals(DAD.otherParent(), MOM);
    }

}