package com.securly.securlyproject.data.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "parent_account_id")
    private Long parentAccountId;

    @Column(name = "root_account_id")
    private Long rootAccountId;

    @Column(name = "default_storage_quota_mb")
    private Long defaultStorageQuotaMb;

    @Column(name = "default_user_storage_quota_mb")
    private Long defaultUserStorageQuotaMb;

    @Column(name = "default_group_storage_quota_mb")
    private Long defaultGroupStorageQuotaMb;

    @Column(name = "default_time_zone")
    private String defaultTimeZone;

    @Column(name = "sis_account_id")
    private String sisAccountId;

    @Column(name = "integration_id")
    private String integrationId;

    @Column(name = "sis_import_id")
    private String sisImportId;

    @Column(name = "lti_guid")
    private String ltiGuid;

    @Column(name = "workflow_state")
    private String workflowState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(uuid, account.uuid) && Objects.equals(parentAccountId, account.parentAccountId) && Objects.equals(rootAccountId, account.rootAccountId) && Objects.equals(defaultStorageQuotaMb, account.defaultStorageQuotaMb) && Objects.equals(defaultUserStorageQuotaMb, account.defaultUserStorageQuotaMb) && Objects.equals(defaultGroupStorageQuotaMb, account.defaultGroupStorageQuotaMb) && Objects.equals(defaultTimeZone, account.defaultTimeZone) && Objects.equals(sisAccountId, account.sisAccountId) && Objects.equals(integrationId, account.integrationId) && Objects.equals(sisImportId, account.sisImportId) && Objects.equals(ltiGuid, account.ltiGuid) && Objects.equals(workflowState, account.workflowState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uuid, parentAccountId, rootAccountId, defaultStorageQuotaMb, defaultUserStorageQuotaMb, defaultGroupStorageQuotaMb, defaultTimeZone, sisAccountId, integrationId, sisImportId, ltiGuid, workflowState);
    }
}
