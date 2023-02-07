/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.isurutee.aws.lambda.convert;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
class AWSLambdaXMLJSONConversionHandlerTest {

    @Test
    void invokeLambdaForXMLShouldReturnJson() {
        String in = """
                {
                    "XML": "<Test><ValueStr>Hello World!</ValueStr><ValueNum>150</ValueNum></Test>"
                }
                """;

        RestAssured.given()
                .body(in)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(containsString("\\\"ValueStr\\\": \\\"Hello World!\\\""))
                .and()
                .body(containsString("\\\"ValueNum\\\": 150"));
    }
}
