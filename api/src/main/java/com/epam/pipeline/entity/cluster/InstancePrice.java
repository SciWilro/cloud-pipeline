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

package com.epam.pipeline.entity.cluster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstancePrice {
    private String instanceType;
    private int instanceDisk;
    private double pricePerHour;
    private double minimumTimePrice;
    private double averageTimePrice;
    private double maximumTimePrice;

    public InstancePrice(String instanceType, int instanceDisk, double pricePerHour) {
        this.instanceType = instanceType;
        this.instanceDisk = instanceDisk;
        this.pricePerHour = pricePerHour;
    }
}
