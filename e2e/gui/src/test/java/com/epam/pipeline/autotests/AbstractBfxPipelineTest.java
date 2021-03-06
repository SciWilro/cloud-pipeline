/*
 * Copyright 2017-2019 EPAM Systems, Inc. (https://www.epam.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.epam.pipeline.autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.pipeline.autotests.ao.AuthenticationPageAO;
import com.epam.pipeline.autotests.utils.C;
import com.epam.pipeline.autotests.utils.TestCase;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.*;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public abstract class AbstractBfxPipelineTest {

    @BeforeClass
    public void setUp() {
        Configuration.timeout = C.DEFAULT_TIMEOUT;
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.startMaximized = true;
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        open(C.ROOT_ADDRESS);
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Something wrong with robot", e);
        }
        robot.keyPress(122);
        robot.keyRelease(122);

        new AuthenticationPageAO()
                .login(C.LOGIN)
                .password(C.PASSWORD)
                .signIn();

        //reset mouse
        $(byId("navigation-button-logo")).shouldBe(visible).click();
        sleep(2000);
    }

    @AfterMethod
    public void logging(ITestResult result) {
        StringBuilder testCasesString = new StringBuilder();

        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestCase.class)) {
            TestCase testCaseAnnotation = method.getAnnotation(TestCase.class);
            for (String testCase : testCaseAnnotation.value()) {
                testCasesString.append(testCase).append(" ");
            }
        }

        System.out.println(String.format("%s::%s [ %s]: %s",
                result.getTestClass().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(),
                testCasesString.toString(),
                result.isSuccess() ? "SUCCESS" : "FAILS")
        );
    }
}