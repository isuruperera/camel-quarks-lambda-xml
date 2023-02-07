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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.camel.ProducerTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named("xmlJsonConversionHandler")
public class AWSLambdaXMLJSONConversionHandler implements RequestHandler<Map<String,Object>, String> {

    @Inject
    ProducerTemplate template;

    @Override
    public String handleRequest(Map<String,Object> input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Calling Camel Route with input %s".formatted(input));
        return template.requestBody("direct:convertXMLJson", input, String.class);
    }
}
