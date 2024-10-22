package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.Parent.MOM;
import static java.time.Month.MAY;

public class MothersDayRule extends ParentsDayRule {

    public MothersDayRule() {
        super(MAY, MOM);
    }

}
