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

import org.apache.camel.builder.RouteBuilder;
import org.isurutee.aws.lambda.convert.util.JSONObjectConverter;
import org.json.JSONObject;
import org.json.XML;
import org.json.XMLParserConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:convertXMLJson").routeId("ConvertXMLJson")
                .log("Camel Route Received Payload ==> ${body}")
                .setProperty("LOWER_CASE", simple("${body[LowerCase]}"))
                .setBody(exchange -> exchange.getIn().getBody(Map.class).get("XML"))
                .log("Camel Route Pre Processed Payload ==> ${body}")
                .process(exchange -> {
                    String xml = exchange.getIn().getBody(String.class);
                    boolean convertLowerCase = exchange.getProperty("LOWER_CASE", Boolean.class);
                    JSONObject jsonObject = XML.toJSONObject(xml, XMLParserConfiguration.ORIGINAL);
                    if (convertLowerCase) {
                        jsonObject = JSONObjectConverter.lowerCaseJSONKeys(jsonObject);
                    }
                    exchange.getIn().setBody(jsonObject.toString(4));
                })
                .log("Camel Route Processed Payload ==> ${body}")
                .end();
    }


}
