package ui.gui;

public enum PanelSizes {
    ENTRY(150, 100),
    LOG_LIST(300, 150),
    LOG_LIST_BUTTON(150, 150),
    DAILY_LOG(250, 400),
    RECOVERY_APP(300, 450);

    private final int width;
    private final int height;

    PanelSizes(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
