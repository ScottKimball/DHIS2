package org.motechproject.dhis2.http;

/**
 * Created by scott on 9/25/14.
 * Model for DHIS2 Json Response:
 * {"status":"SUCCESS","importCount":{"imported":1,"updated":0,"ignored":0,"deleted":0},"reference":"GmHEBGJtymq"}
 */
public class Response {

    private String status;
    private String reference;
    private ImportCount importCount;


    private static final class ImportCount {
        private int imported;
        private int updated;
        private int deleted;
        private int ignored;


        private ImportCount(int imported, int updated, int deleted, int ignored) {
            this.imported = imported;
            this.updated = updated;
            this.deleted = deleted;
            this.ignored = ignored;
        }

        private ImportCount(int imported, int updated, int ignored) {
            this.imported = imported;
            this.updated = updated;
            this.ignored = ignored;
        }

        private ImportCount() {
        }

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

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }


        public int getIgnored() {
            return ignored;
        }

        public void setIgnored(int ignored) {
            this.ignored = ignored;
        }

        public String toString() {
            return "ImportCount: imported: " + imported + ", updated: " + updated + ", deleted: " + deleted
                    + ", ignored: " + ignored;
        }
    }

    public Response(String status, String reference, ImportCount importCount) {
        this.status = status;
        this.reference = reference;
        this.importCount = importCount;
    }

    public Response(String status, String reference) {
        this.status = status;
        this.reference = reference;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ImportCount getImportCount() {
        return importCount;
    }

    public void setImportCount(ImportCount importCount) {
        this.importCount = importCount;
    }

    public String toString() {
        return "status: " + status + ", reference:" + reference + ", importCount:" + importCount.toString();
    }


}


