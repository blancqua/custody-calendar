package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.Parent.DAD;
import static java.time.Month.JUNE;

public class FathersDayRule extends ParentsDayRule {

    public FathersDayRule() {
        super(JUNE, DAD);
    }

}
