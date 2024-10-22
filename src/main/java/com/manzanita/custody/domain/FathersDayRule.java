package com.manzanita.custody.domain;

import static com.manzanita.custody.domain.Parent.DAD;
import static java.time.Month.JUNE;

public class FathersDayRule extends ParentsDayRule {

    public FathersDayRule() {
        super(JUNE, DAD);
    }

}
