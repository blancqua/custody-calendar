package com.manzanita.custody.domain;

import static com.manzanita.custody.domain.Parent.MOM;
import static java.time.Month.MAY;

public class MothersDayRule extends ParentsDayRule {

    public MothersDayRule() {
        super(MAY, MOM);
    }

}
