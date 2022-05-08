package com.securly.securlyproject.security.oauth2.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@ToString
public class CanvasAccountResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("workflow_state")
    private String workflowState;

    @JsonProperty("parent_account_id")
    private Long parentAccountId;

    @JsonProperty("root_account_id")
    private Long rootAccountId;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("default_storage_quota_mb")
    private Long defaultStorageQuotaMb;

    @JsonProperty("default_user_storage_quota_mb")
    private Long defaultUserStorageQuotaMb;

    @JsonProperty("default_group_storage_quota_mb")
    private Long defaultGroupStorageQuotaMb;

    @JsonProperty("default_time_zone")
    private String defaultTimeZone;
}
