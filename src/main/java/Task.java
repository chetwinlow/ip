class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String fileFormat() {
        return String.format(" | %s | %s", this.isDone ? "1" : "0", this.description);
    }
}
