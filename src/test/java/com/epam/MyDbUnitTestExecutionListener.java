package com.epam;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.springframework.test.context.TestContext;

public class MyDbUnitTestExecutionListener extends TransactionDbUnitTestExecutionListener {

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {

        super.beforeTestMethod(testContext);
    }
}
