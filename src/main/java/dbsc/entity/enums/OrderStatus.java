package dbsc.entity.enums;

public enum OrderStatus {
    NEW("NEW"),
    DIAGNOSTIC("DIAGNOSTIC"),
    AGREEMENT("AGREEMENT"),
    READY_TO_ISSUE("READY TO ISSUE"),
    ISSUED_BY("ISSUED_BY");
    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
