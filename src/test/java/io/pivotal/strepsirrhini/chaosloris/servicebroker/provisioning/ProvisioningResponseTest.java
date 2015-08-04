/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.strepsirrhini.chaosloris.servicebroker.provisioning;

import io.pivotal.strepsirrhini.chaosloris.servicebroker.AbstractSerializationTest;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public final class ProvisioningResponseTest extends AbstractSerializationTest<ProvisioningResponse> {

    @Override
    protected void assertContents(Map m) throws IOException {
        assertEquals("https://test.dashboard.url", m.get("dashboard_url"));
    }

    @Override
    protected ProvisioningResponse getInstance() {
        return new ProvisioningResponse(URI.create("https://test.dashboard.url"));
    }

}
