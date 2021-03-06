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

package com.epam.pipeline.mapper;

import com.epam.pipeline.controller.vo.CloudRegionVO;
import com.epam.pipeline.entity.region.AbstractCloudRegion;
import com.epam.pipeline.entity.region.AbstractCloudRegionCredentials;
import com.epam.pipeline.entity.region.AwsRegion;
import com.epam.pipeline.entity.region.AzureRegion;
import com.epam.pipeline.entity.region.AzureRegionCredentials;
import com.epam.pipeline.entity.region.CloudProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CloudRegionMapper {

    default CloudRegionVO toCloudRegionVO(AbstractCloudRegion cloudRegion) {
        if (cloudRegion instanceof AwsRegion) {
            return toAwsRegionVO((AwsRegion) cloudRegion);
        } else if (cloudRegion instanceof AzureRegion) {
            return toAzureRegionVO((AzureRegion) cloudRegion);
        }
        throw new IllegalArgumentException(
                "Unsupported cloud getProvider: " + cloudRegion.getProvider());
    }

    default AbstractCloudRegion toEntity(CloudRegionVO dto) {
        if (CloudProvider.AWS == dto.getProvider()) {
            return toAwsRegion(dto);
        } else if (CloudProvider.AZURE == dto.getProvider()) {
            return toAzureRegion(dto);
        }
        throw new IllegalArgumentException(
                "Unsupported cloud getProvider: " + dto.getProvider());
    }

    default AbstractCloudRegionCredentials toCredentialsEntity(CloudRegionVO dto) {
        if (CloudProvider.AWS == dto.getProvider()) {
            return null;
        } else if (CloudProvider.AZURE == dto.getProvider()) {
            return toAzureRegionCredentials(dto);
        }
        throw new IllegalArgumentException(
                "Unsupported cloud getProvider: " + dto.getProvider());
    }

    @Mapping(target = "storageAccount", ignore = true)
    @Mapping(target = "storageAccountKey", ignore = true)
    @Mapping(target = "resourceGroup", ignore = true)
    @Mapping(target = "azurePolicy", ignore = true)
    @Mapping(target = "subscription", ignore = true)
    @Mapping(target = "authFile", ignore = true)
    @Mapping(target = "sshPublicKeyPath", ignore = true)
    @Mapping(target = "meterRegionName", ignore = true)
    @Mapping(target = "azureApiUrl", ignore = true)
    @Mapping(target = "priceOfferId", ignore = true)
    CloudRegionVO toAwsRegionVO(AwsRegion awsRegion);

    @Mapping(target = "corsRules", ignore = true)
    @Mapping(target = "policy", ignore = true)
    @Mapping(target = "kmsKeyId", ignore = true)
    @Mapping(target = "kmsKeyArn", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "sshKeyName", ignore = true)
    @Mapping(target = "tempCredentialsRole", ignore = true)
    @Mapping(target = "backupDuration", ignore = true)
    @Mapping(target = "versioningEnabled", ignore = true)
    @Mapping(target = "storageAccountKey", ignore = true)
    CloudRegionVO toAzureRegionVO(AzureRegion azureRegion);

    @Mapping(target = "aclClass", ignore = true)
    @Mapping(target = "mask", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "fileShareMounts", ignore = true)
    AwsRegion toAwsRegion(CloudRegionVO awsRegion);

    @Mapping(target = "aclClass", ignore = true)
    @Mapping(target = "mask", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "fileShareMounts", ignore = true)
    AzureRegion toAzureRegion(CloudRegionVO azureRegion);

    @Mapping(target = "provider", ignore = true)
    AzureRegionCredentials toAzureRegionCredentials(CloudRegionVO azureRegion);

}
