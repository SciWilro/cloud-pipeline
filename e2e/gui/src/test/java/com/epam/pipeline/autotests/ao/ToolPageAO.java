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
package com.epam.pipeline.autotests.ao;

import com.codeborne.selenide.SelenideElement;
import com.epam.pipeline.autotests.utils.C;

import java.util.Collections;
import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ToolPageAO implements AccessObject<ToolPageAO> {

    public ToolPageAO assertPageTitleIs(String expectedTitle) {
        sleep(3, SECONDS);
        if (!title().contains(expectedTitle)) {
            screenshot("page-title");
            throw new RuntimeException("Page title is not the same as expected");
        }
        return this;
    }

    public ToolPageAO validateEndpointPage() {
        $(byId("owner")).should(appear).shouldHave(text(C.LOGIN));
        return this;
    }

    @Override
    public Map<Primitive, SelenideElement> elements() {
        return Collections.emptyMap();
    }
}
