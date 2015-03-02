package org.motechproject.dhis2.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DhisStatusResponse {
    public static enum DhisStatus {
        SUCCESS,
        ERROR
    }

    private DhisStatus status;
    private ImportCount importCount;
    private String reference;

    public DhisStatus getStatus() {
        return status;
    }

    public void setStatus(DhisStatus status) {
        this.status = status;
    }

    public ImportCount getImportCount() {
        return importCount;
    }

    public void setImportCount(ImportCount importCount) {
        this.importCount = importCount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static class ImportCount {
        private int imported;
        private int updated;
        private int ignored;
        private int deleted;

        public int getImported() {
            return imported;
        }

        public void setImported(int imported) {
            this.imported = imported;
        }

        public int getUpdated() {
            return updated;
        }

        public void setUpdated(int updated) {
            this.updated = updated;
        }

        public int getIgnored() {
            return ignored;
        }

        public void setIgnored(int ignored) {
            this.ignored = ignored;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }


}
